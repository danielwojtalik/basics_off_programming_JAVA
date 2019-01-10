package com.app.cutom_exception;

import lombok.Getter;

@Getter
public class MyException extends RuntimeException { // I want to my exception are not checked

    private ExceptionInfo exceptionInfo;

    public MyException(ExceptionCode exceptionCode, String exceptionMassage) {
        this.exceptionInfo = new ExceptionInfo(exceptionCode, exceptionMassage);
    }
}
