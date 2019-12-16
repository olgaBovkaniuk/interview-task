package com.gmail.olgabovkaniuk.domain;

import com.gmail.olgabovkaniuk.dto.MessageType;

import java.sql.Date;

public interface GroupedStats {

    String getFullName();
    MessageType getMessageType();
    Date getDate();
    Integer getTotalRowCount();
}
