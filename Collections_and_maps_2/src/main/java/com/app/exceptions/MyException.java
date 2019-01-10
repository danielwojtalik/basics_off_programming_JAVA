package com.app.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class MyException extends RuntimeException {

    private ExceptionInfo exceptionInfo;

    public MyException(ExceptionCode exceptionCode, String exceptionMessage) {
        this.exceptionInfo = new ExceptionInfo(exceptionCode, exceptionMessage);
    }
}
