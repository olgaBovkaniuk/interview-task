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
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class LogFileParser {

    Logger log = Logger.getLogger(LogFileParser.class.getName());

    public List<LogRowDataDto> readLogFile(String fileName) {
        List<String> logRows = new ArrayList<>();
        List<LogRowDataDto> logRowDataDtos = new ArrayList<>();
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fileName))) {
            logRows = bufferedReader.lines().collect(Collectors.toList());
            logRowDataDtos = mapLogRowToDto(logRows);
        } catch (IOException | ParseException e) {
            log.severe(e.getMessage());
        }
        return logRowDataDtos;
    }

    public List<LogRowDataDto> mapLogRowToDto(List<String> logRows) throws ParseException {
        List<LogRowDataDto> logRowDataDtos = new ArrayList<>();
        LogRowDataDto logRowDataDto;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        for (String logRow : logRows) {
            List<String> parsedLogRow = parseLogRow(logRow);
            logRowDataDto = new LogRowDataDto(
                    new Timestamp(simpleDateFormat.parse(parsedLogRow.get(0)).getTime()),
                    MessageType.getMessageType(parsedLogRow.get(1)),
                    parsedLogRow.get(2),
                    parsedLogRow.get(3),
                    parsedLogRow.get(4)
            );
            logRowDataDtos.add(logRowDataDto);
        }
        return logRowDataDtos;
    }

    private List<String> parseLogRow(String logRow) {
        List<String> parsedRow = new LinkedList<>();
        parsedRow.add(logRow.substring(0, "XXXX-XX-XX XX:XX:XX,XXX".length()));
        String[] parsedRowArray = logRow.split(" ", 6);
        for (int index = 0; index < parsedRowArray.length; index++) {
            if (index < 2) {
                continue;
            }
            parsedRow.add(parsedRowArray[index]);
        }
        return parsedRow;
    }

}
