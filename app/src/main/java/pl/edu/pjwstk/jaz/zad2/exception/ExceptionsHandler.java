package pl.edu.pjwstk.jaz.zad2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = {AlreadyLoggedException.class})
    public ResponseEntity<Object> handleAlreadyLoggedException(AlreadyLoggedException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UserAlreadyExistsException.class})
    public ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UserDoesntExistException.class})
    public ResponseEntity<Object> handleUserDoesntExistException(UserDoesntExistException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NoCategoryException.class})
    public ResponseEntity<Object> handleNoCategoryException(NoCategoryException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {BadCategoryRequestException.class})
    public ResponseEntity<Object> handleBadCategoryRequestException(BadCategoryRequestException e){
        return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NoAuctionException.class})
    public ResponseEntity<Object> handleNoAuctionExceptionException(NoAuctionException e){
        return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {WrongSectionRequestException.class})
    public ResponseEntity<Object> handleWrongSectionRequestException(WrongSectionRequestException e){
        return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NoSectionException.class})
    public ResponseEntity<Object> handleNoSectionException(NoSectionException e){
        return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {BadAuctionRequestException.class})
    public ResponseEntity<Object> handleBadAuctionRequestException(BadAuctionRequestException e){
        return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
