package exceptions;

public class NotAvailableException extends Exception {
    public NotAvailableException(String message) {
        super("[error] " + message);
    }
}
