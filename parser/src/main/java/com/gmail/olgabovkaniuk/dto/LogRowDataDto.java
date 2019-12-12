package com.gmail.olgabovkaniuk.dto;

import java.sql.Timestamp;

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
}
