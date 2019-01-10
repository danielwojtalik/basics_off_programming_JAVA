package com.app.cutom_exception;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class ExceptionInfo {

    private ExceptionCode exceptionCode;
    private String exceptionMassage;
    private LocalDateTime localDateTime;


    public ExceptionInfo(ExceptionCode exceptionCode, String exceptionMassage) {
        this.exceptionCode = exceptionCode;
        this.exceptionMassage = exceptionMassage;
        this.localDateTime = LocalDateTime.now();
    }
}
