package com.gmail.olgabovkaniuk.service;

import com.gmail.olgabovkaniuk.dto.LogRowDataDto;
import com.gmail.olgabovkaniuk.dto.MessageType;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class LogFileParser {

    private static final int DATE_STR_LENGTH = "XXXX-XX-XX XX:XX:XX".length();
    private static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final Logger log = Logger.getLogger(LogFileParser.class.getName());

    public List<LogRowDataDto> readLogFile(String fileName) {
        List<String> logRows = new ArrayList<>();
        List<LogRowDataDto> logRowDataDtos = new ArrayList<>();
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fileName))) {
            logRows = bufferedReader.lines().collect(Collectors.toList());
            logRowDataDtos = mapLogRowToDto(logRows);
        } catch (Exception e) {
            log.severe(e.getMessage() + " File name: " + fileName);
        }
        return logRowDataDtos;
    }

    public List<LogRowDataDto> mapLogRowToDto(List<String> logRows) {

        return logRows.stream()
                .map(this::parseRow)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    LogRowDataDto parseRow(String logRow) {

        String dateAsString = logRow.substring(0, DATE_STR_LENGTH);

        logRow = logRow.substring(DATE_STR_LENGTH + 1);
        String[] parsedRowArray = logRow.split(" ", 4);

        try {
            return new LogRowDataDto(
                    new Timestamp(SIMPLE_DATE_FORMAT.parse(dateAsString).getTime()),
                    MessageType.getMessageType(parsedRowArray[0]),
                    parsedRowArray[1],
                    parsedRowArray[2],
                    parsedRowArray[3]
            );
        } catch (ParseException e) {
            log.severe("Cannot parse date: " + dateAsString);
        }
        return null;
    }
}
