package com.app.exceptions;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExceptionInfo {
    private ExceptionCode exceptionCode;
    private String exceptionMessage;
    private LocalDateTime localDateTime;


    ExceptionInfo(ExceptionCode exceptionCode, String exceptionMessage){
        this.exceptionCode = exceptionCode;
        this.exceptionMessage = exceptionMessage;
        this.localDateTime = LocalDateTime.now();
    }
}
