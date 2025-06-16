package mrkotyaka.springbootrest.advice;

import mrkotyaka.springbootrest.exception.InvalidCredentials;
import mrkotyaka.springbootrest.exception.UnauthorizedUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<String> icHandler(InvalidCredentials ex) {
        return new ResponseEntity<>("ExceptionHandlerAdvice (InvalidCredentials): " + ex.getMessage(),  HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedUser.class)
    public ResponseEntity<String> uuHandler(RuntimeException ex) {
        return new ResponseEntity<>("ExceptionHandlerAdvice (UnauthorizedUser): " + ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
