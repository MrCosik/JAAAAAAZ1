package pl.edu.pjwstk.jaz.zad2.exception;

public class AlreadyLoggedException extends RuntimeException{

    public AlreadyLoggedException(String message) {
        super(message);
    }

    public AlreadyLoggedException(String message, Throwable cause) {
        super(message, cause);
    }
}
