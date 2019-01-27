package com.app.exceptions;

import com.app.exceptions.ExceptionCode;
import com.app.exceptions.ExceptionInfo;
import lombok.Data;

@Data

public class MyException extends RuntimeException {
    private ExceptionInfo exceptionInfo;

    public MyException(ExceptionCode exceptionCode, String exceptionMessage){
        this.exceptionInfo = new ExceptionInfo(exceptionCode, exceptionMessage);
    }
}
