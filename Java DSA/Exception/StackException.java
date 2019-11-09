package Exception;

public class StackException extends RuntimeException {
    private String exception;

    public StackException(String exception) {
        super();
        this.exception = "[Stack error] " + exception;
    }

    public String toString() {
        return this.exception;
    }
}
