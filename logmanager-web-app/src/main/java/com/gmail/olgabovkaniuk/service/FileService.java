package com.gmail.olgabovkaniuk.service;

import com.gmail.olgabovkaniuk.controller.QueryParam;
import com.gmail.olgabovkaniuk.domain.LogRowData;
import com.gmail.olgabovkaniuk.repository.LogRowDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static com.gmail.olgabovkaniuk.service.StatisticService.DATE_TIME_FORMATTER;

@Service
public class FileService {

    public static final DateTimeFormatter FILE_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());

    @Autowired
    LogRowDataRepository repository;

    public List<String> getFilteredLogRows(QueryParam queryParam) {
        Timestamp from = Timestamp.valueOf(LocalDateTime.parse(queryParam.getFromDate(), DATE_TIME_FORMATTER));
        Timestamp to = Timestamp.valueOf(LocalDateTime.parse(queryParam.getToDate(), DATE_TIME_FORMATTER));
        List<LogRowData> filteredByDate = repository.getFilteredByDate(from, to);
        return filteredByDate.stream()
                .map(this::formatRow)
                .collect(Collectors.toList());
    }

    private String formatRow(LogRowData row) {
        return FILE_DATE_TIME_FORMATTER.format(row.getLogRowDate().toLocalDateTime()) + " " +
                row.getMessageType() + " " +
                row.getFirstName() + " " +
                row.getLastName() + " " +
                row.getMessage() + "\n";
    }
}
