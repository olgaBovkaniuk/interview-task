package com.gmail.olgabovkaniuk.dto;

import java.sql.Timestamp;
import java.util.Objects;

public class LogRowDataDto {
    private Timestamp logRowDate;
    private MessageType messageType;
    private String firstName;
    private String lastName;
    private String message;

    public LogRowDataDto(Timestamp logRowDate, MessageType messageType, String firstName, String lastName, String message) {
        this.logRowDate = logRowDate;
        this.messageType = messageType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.message = message;
    }

    public Timestamp getLogRowDate() {
        return logRowDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogRowDataDto that = (LogRowDataDto) o;
        return Objects.equals(logRowDate, that.logRowDate) &&
                messageType == that.messageType &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logRowDate, messageType, firstName, lastName, message);
    }
}
