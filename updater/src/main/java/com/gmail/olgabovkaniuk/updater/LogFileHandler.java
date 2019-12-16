package com.gmail.olgabovkaniuk.updater;

import com.gmail.olgabovkaniuk.AppProperties;
import com.gmail.olgabovkaniuk.domain.LogRowData;
import com.gmail.olgabovkaniuk.domain.ProcessedFile;
import com.gmail.olgabovkaniuk.dto.LogRowDataDto;
import com.gmail.olgabovkaniuk.repository.LogRowDataRepository;
import com.gmail.olgabovkaniuk.repository.ProcessedFileRepository;
import com.gmail.olgabovkaniuk.service.LogFileParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class LogFileHandler {

    private static Logger log = Logger.getLogger(LogFileHandler.class.getName());

    @Autowired
    LogRowDataRepository logRowDataRepository;

    @Autowired
    ProcessedFileRepository processedFileRepository;

    @Autowired
    AppProperties appProperties;

    private final LogFileParser logFileParser = new LogFileParser();

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean handleFile(String logFileName) {
        List<LogRowDataDto> logRowDataDtos = logFileParser.readLogFile(appProperties.getLogFilesDirPath() + "/" + logFileName);
        List<LogRowData> records = logRowDataDtos.stream()
                .map(logRowDataDto ->
                        new LogRowData(
                                logRowDataDto.getLogRowDate(),
                                logRowDataDto.getMessageType(),
                                logRowDataDto.getFirstName(),
                                logRowDataDto.getLastName(),
                                logRowDataDto.getMessage()
                        )
                )
                .collect(Collectors.toList());

        logRowDataRepository.saveAll(records);
        processedFileRepository.save(new ProcessedFile(logFileName));

        return true;
    }
}
