package Exception;

public class DictionaryException extends RuntimeException {
    private String exception;

    public DictionaryException(String exception) {
        super();
        this.exception = "[Dictionary error] " + exception;
    }

    public String toString() {
        return this.exception;
    }
}
