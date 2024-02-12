package homework03;

public class NameUserException extends RuntimeException {
    public NameUserException(String message) {
        super(message);
    }
    public NameUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
