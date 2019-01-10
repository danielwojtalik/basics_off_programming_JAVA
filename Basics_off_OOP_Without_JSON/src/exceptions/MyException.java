package exceptions;

public class MyException extends RuntimeException {
    private ExceptionInfo exceptionInfo;

    public MyException(ExceptionInfo exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }
    public ExceptionInfo getExceptionInfo() {
        return exceptionInfo;
    }
}
