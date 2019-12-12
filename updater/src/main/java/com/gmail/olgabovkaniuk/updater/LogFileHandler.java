package com.gmail.olgabovkaniuk.updater;

import com.gmail.olgabovkaniuk.domain.LogRowData;
import com.gmail.olgabovkaniuk.dto.LogRowDataDto;
import com.gmail.olgabovkaniuk.repository.LogRowDataRepository;
import com.gmail.olgabovkaniuk.service.LogFileParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;
import java.util.logging.Logger;

import static com.gmail.olgabovkaniuk.updater.UpdaterAsync.DIRECTORY_PATH;

@Component
public class LogFileHandler {

    private static Logger log = Logger.getLogger(LogFileHandler.class.getName());

    @Autowired
    LogRowDataRepository logRowDataRepository;

    private final LogFileParser logFileParser = new LogFileParser();

    @Transactional
    public boolean handleFile(String logFileName) {

        List<LogRowDataDto> logRowDataDtos = logFileParser.readLogFile(DIRECTORY_PATH + "/" + logFileName);
        for (LogRowDataDto logRowDataDto : logRowDataDtos) {
            LogRowData logRowData = new LogRowData(
                    logRowDataDto.getLogRowDate(),
                    logRowDataDto.getMessageType(),
                    logRowDataDto.getFirstName(),
                    logRowDataDto.getLastName(),
                    logRowDataDto.getMessage()
            );
            logRowDataRepository.save(logRowData);
        }
        return true;
    }
}
