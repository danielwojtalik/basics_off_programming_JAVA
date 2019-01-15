package com.app.my_exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data

public class ExceptionInfo {

    private ExceptionCode exceptionCode;
    private String description;
    private LocalDateTime exceptionTime = LocalDateTime.now();

    public ExceptionInfo (ExceptionCode exceptionCode, String description){
        this.exceptionCode = exceptionCode;
        this.description = description;
    }
}


