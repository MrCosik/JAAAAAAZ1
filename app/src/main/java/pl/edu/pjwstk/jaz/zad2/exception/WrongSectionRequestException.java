package pl.edu.pjwstk.jaz.zad2.exception;

public class WrongSectionRequestException extends RuntimeException {
    public WrongSectionRequestException(String message) {
        super(message);
    }

    public WrongSectionRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
