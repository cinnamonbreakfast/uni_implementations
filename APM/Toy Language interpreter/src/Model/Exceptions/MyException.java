package Model.Exceptions;

public class MyException extends RuntimeException {
    private String exception;

    public MyException(String exception) {
        super();
        this.exception = exception;
    }



    public String toString() {
        return this.exception;
    }
}
