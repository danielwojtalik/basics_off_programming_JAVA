package com.app.my_exceptions;

import lombok.Data;

@Data
public class MyException extends RuntimeException {

    private ExceptionInfo exceptionInfo;

    public MyException(ExceptionCode exceptionCode, String description) {
        this.exceptionInfo = new ExceptionInfo(exceptionCode, description);
    }

}
