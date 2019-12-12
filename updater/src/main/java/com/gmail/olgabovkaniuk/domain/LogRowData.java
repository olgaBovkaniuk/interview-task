package com.gmail.olgabovkaniuk.domain;

import com.gmail.olgabovkaniuk.dto.MessageType;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity(name = "log_row_data")
public class LogRowData implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private Timestamp logRowDate;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "message_type")
    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    @Column(name = "message")
    private String message;

    public LogRowData() {
    }

    public LogRowData(Timestamp logRowDate, MessageType messageType, String firstName, String lastName, String message) {
        this.logRowDate = logRowDate;
        this.messageType = messageType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.message = message;
    }

    public Timestamp getLogRowDate() {
        return logRowDate;
    }

    public void setLogRowDate(Timestamp logRowDate) {
        this.logRowDate = logRowDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogRowData that = (LogRowData) o;

        if (!Objects.equals(logRowDate, that.logRowDate)) return false;
        if (!Objects.equals(firstName, that.firstName)) return false;
        if (!Objects.equals(lastName, that.lastName)) return false;
        if (messageType != that.messageType) return false;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        int result = logRowDate != null ? logRowDate.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (messageType != null ? messageType.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}
