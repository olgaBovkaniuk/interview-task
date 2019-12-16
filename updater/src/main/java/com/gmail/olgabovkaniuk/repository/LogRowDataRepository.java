package com.gmail.olgabovkaniuk.repository;

import com.gmail.olgabovkaniuk.domain.GroupedStats;
import com.gmail.olgabovkaniuk.domain.LogRowData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface LogRowDataRepository extends CrudRepository<LogRowData, Long> {

    @Query(nativeQuery = true, value = "select " +
                "concat(lrd.first_name, ' ', lrd.last_name) as fullName, " +
                "CAST(date AS DATE) as date, " +
                "count(1) as totalRowCount "+
            "from log_row_data lrd " +
            "where date between ?1 and ?2 " +
            "group by lrd.first_name, lrd.last_name, CAST(date AS DATE) " +
            "order by CAST(date AS DATE) desc")
    public List<GroupedStats> getGroupedByUserNameAndDateStats(Timestamp from, Timestamp to);

    @Query(nativeQuery = true, value = "select " +
                "lrd.message_type as messageType, " +
                "CAST(date AS DATE) as date, " +
                "count(1) as totalRowCount " +
            "from log_row_data lrd " +
            "where date between ?1 and ?2 " +
            "group by lrd.message_type, CAST(date AS DATE) " +
            "order by CAST(date AS DATE) desc")
    public List<GroupedStats> getGroupedByMessageTypeAndDateStats(Timestamp from, Timestamp to);

    @Query(nativeQuery = true, value = "select * " +
            "from log_row_data " +
            "where date between ?1 and ?2 " +
            "order by date desc")
    public List<LogRowData> getFilteredByDate(Timestamp from, Timestamp to);
}
