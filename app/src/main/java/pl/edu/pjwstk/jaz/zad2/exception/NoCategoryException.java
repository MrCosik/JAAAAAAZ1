package pl.edu.pjwstk.jaz.zad2.exception;

public class NoCategoryException extends Throwable {
    public NoCategoryException(String message) {
        super(message);
    }

    public NoCategoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
