package My.toyproject.exception;

public class CancelOrderException extends RuntimeException {

    public CancelOrderException() {
    }

    public CancelOrderException(String message) {
        super(message);
    }

    public CancelOrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public CancelOrderException(Throwable cause) {
        super(cause);
    }

    public CancelOrderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
