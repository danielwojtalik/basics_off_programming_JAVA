package com.app.custom_exceptions;

// this class is need to provide customized exception - thus need implements RuntimeException (I can throw only this object which extends class Throwable, in addition RuntimeException
// are unchecked - compiler will not yell at me if I don't handle it.

public class MyException extends RuntimeException {

    private ExceptionInfo exceptionInfo;

    public MyException(ExceptionInfo exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }

    public ExceptionInfo getExceptionInfo() {
        return exceptionInfo;
    }
}
