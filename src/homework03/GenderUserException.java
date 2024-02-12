package homework03;

import java.util.Date;

public class GenderUserException extends RuntimeException{
    public GenderUserException(String message, Throwable cause) {
        super(message, cause);
    }
    public GenderUserException(String message) {
        super(message);
    }
}
