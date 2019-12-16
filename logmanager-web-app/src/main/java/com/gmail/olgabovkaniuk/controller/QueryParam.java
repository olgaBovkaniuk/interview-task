package com.gmail.olgabovkaniuk.controller;

public class QueryParam {
    private String fromDate;
    private String toDate;
    private GroupingParameter groupingParameter;

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public GroupingParameter getGroupingParameter() {
        return groupingParameter;
    }

    public void setGroupingParameter(GroupingParameter groupingParameter) {
        this.groupingParameter = groupingParameter;
    }
}
