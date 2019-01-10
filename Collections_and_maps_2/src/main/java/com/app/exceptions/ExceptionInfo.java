package com.app.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor

public class ExceptionInfo {


    private ExceptionCode exceptionCode;
    private String exceptionMessage;
    private LocalDateTime localDateTime;

    public ExceptionInfo(ExceptionCode exceptionCode, String exceptionMessage) {
        this.exceptionCode = exceptionCode;
        this.exceptionMessage = exceptionMessage;
        this.localDateTime = LocalDateTime.now();
    }
}
