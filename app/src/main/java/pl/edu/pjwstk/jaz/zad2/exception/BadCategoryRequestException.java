package pl.edu.pjwstk.jaz.zad2.exception;

public class BadCategoryRequestException extends RuntimeException{
    public BadCategoryRequestException() {
    }

    public BadCategoryRequestException(String message) {
        super(message);
    }
}
