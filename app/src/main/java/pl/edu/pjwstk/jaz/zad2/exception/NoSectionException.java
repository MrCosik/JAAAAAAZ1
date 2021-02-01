package pl.edu.pjwstk.jaz.zad2.exception;

public class NoSectionException extends RuntimeException {
    public NoSectionException(String message) {
        super(message);
    }

    public NoSectionException(String message, Throwable cause) {
        super(message, cause);
    }
}

