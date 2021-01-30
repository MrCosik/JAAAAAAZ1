package pl.edu.pjwstk.jaz.zad2.exception;

public class NoAuctionException extends RuntimeException {
    public NoAuctionException() {
    }

    public NoAuctionException(String message) {
        super(message);
    }
}
