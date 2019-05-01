package com.dfsp.dashboard.model;

public enum MessageModel {

    FILE_SUCCES_DELETED("File has been deleted"),
    FILE_NOT_EXIST("File does not exist"),
    FILE_NOT_FOUND("File not found");

    private String message;

    MessageModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
