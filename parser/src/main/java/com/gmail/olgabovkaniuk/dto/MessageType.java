package com.gmail.olgabovkaniuk.dto;

public enum MessageType {
    INFO,
    WARNING,
    ERROR;

    public static MessageType getMessageType(String messageType) {
        try {
            return MessageType.valueOf(messageType.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("messageType has not been found!");
            e.printStackTrace();
        }
        return null;
    }

}
