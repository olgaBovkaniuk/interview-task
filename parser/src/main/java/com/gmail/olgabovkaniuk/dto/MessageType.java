package com.gmail.olgabovkaniuk.dto;

import java.util.logging.Logger;

public enum MessageType {
    INFO,
    WARNING,
    ERROR;

    private static final Logger log = Logger.getLogger(MessageType.class.getName());

    public static MessageType getMessageType(String messageType) {
        try {
            return MessageType.valueOf(messageType.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("messageType has not been found: " + messageType);
            log.severe(e.getMessage());
        }
        return null;
    }

}
