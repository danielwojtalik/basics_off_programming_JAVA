package com.app.custom_exceptions;

// this class provides me information about Exceptions which are throwing, my Code of this exception, Description (for example getMessage() method), and accurate time, when an Exception was thrown.

import java.time.LocalDateTime;
public class ExceptionInfo {

    private ExceptionCode code;
    private String description;
    private LocalDateTime dateTime;

    public ExceptionInfo() {
    }

    public ExceptionInfo(ExceptionCode code, String description) {
        this.code = code;
        this.description = description;
        this.dateTime = LocalDateTime.now();
    }

    public ExceptionCode getCode() {
        return code;
    }

    public void setCode(ExceptionCode code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
