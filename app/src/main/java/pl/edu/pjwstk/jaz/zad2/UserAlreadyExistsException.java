package pl.edu.pjwstk.jaz.zad2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class UserAlreadyExistsException extends Throwable {
}
