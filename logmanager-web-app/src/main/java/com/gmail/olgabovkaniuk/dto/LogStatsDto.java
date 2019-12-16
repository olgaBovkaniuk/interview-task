package com.gmail.olgabovkaniuk.dto;

public class LogStatsDto {
    private String date;
    private String groupedByField;
    private Integer rowCount;

    public LogStatsDto(String date, Integer rowCount) {
        this.date = date;
        this.rowCount = rowCount;
    }

    public LogStatsDto(String date, String groupedByField, Integer rowCount) {
        this.date = date;
        this.groupedByField = groupedByField;
        this.rowCount = rowCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGroupedByField() {
        return groupedByField;
    }

    public void setGroupedByField(String groupedByField) {
        this.groupedByField = groupedByField;
    }

    public Integer getRowCount() {
        return rowCount;
    }

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }
}
