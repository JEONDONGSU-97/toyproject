package My.toyproject.exception;

public class OverStockException extends RuntimeException {

    public OverStockException() {
    }

    public OverStockException(String message) {
        super(message);
    }

    public OverStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public OverStockException(Throwable cause) {
        super(cause);
    }

    public OverStockException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
