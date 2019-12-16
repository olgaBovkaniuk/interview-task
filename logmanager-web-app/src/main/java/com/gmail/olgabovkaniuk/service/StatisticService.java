package com.gmail.olgabovkaniuk.service;

import com.gmail.olgabovkaniuk.controller.GroupingParameter;
import com.gmail.olgabovkaniuk.controller.QueryParam;
import com.gmail.olgabovkaniuk.domain.GroupedStats;
import com.gmail.olgabovkaniuk.dto.LogStatsDto;
import com.gmail.olgabovkaniuk.repository.LogRowDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticService {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.systemDefault());
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    LogRowDataRepository logRowDataRepository;

    @Transactional
    public List<LogStatsDto> getStatistic(QueryParam queryParam) {
        Timestamp from = Timestamp.valueOf(LocalDateTime.parse(queryParam.getFromDate(), DATE_TIME_FORMATTER));
        Timestamp to = Timestamp.valueOf(LocalDateTime.parse(queryParam.getToDate(), DATE_TIME_FORMATTER));

        List<GroupedStats> groupedStatsArrayList = new ArrayList<>();
        if (queryParam.getGroupingParameter() == GroupingParameter.USER_NAME) {
            groupedStatsArrayList = logRowDataRepository.getGroupedByUserNameAndDateStats(from, to);

        } else if (queryParam.getGroupingParameter() == GroupingParameter.MESSAGE_TYPE) {
            groupedStatsArrayList = logRowDataRepository.getGroupedByMessageTypeAndDateStats(from, to);
        }
        return groupedStatsArrayList.stream()
                .map(this::convertQueryResultToDto)
                .collect(Collectors.toList());
    }

    private LogStatsDto convertQueryResultToDto(GroupedStats groupedStats) {

        return new LogStatsDto(
                SIMPLE_DATE_FORMAT.format(groupedStats.getDate()),
                groupedStats.getMessageType() == null ? groupedStats.getFullName() : groupedStats.getMessageType().name(),
                groupedStats.getTotalRowCount()
        );
    }
}
