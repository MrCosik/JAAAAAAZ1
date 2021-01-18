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

//    @ExceptionHandler(value = {NoEmptyCategoryException.class})
//    public ResponseEntity<Object> handleNoEmptyCategoryException(NoEmptyCategoryException e){
//        return new ResponseEntity<Object>(e.getMessage())
//    }
}
