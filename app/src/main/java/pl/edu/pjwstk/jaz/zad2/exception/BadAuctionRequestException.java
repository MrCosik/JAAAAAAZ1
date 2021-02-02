package pl.edu.pjwstk.jaz.zad2.exception;

public class BadAuctionRequestException extends RuntimeException {
    public BadAuctionRequestException(String message) {
        super(message);
    }

    public BadAuctionRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
