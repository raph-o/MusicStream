package fr.raph.musicstream.exceptions;

import fr.raph.musicstream.models.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<Object> handleObjectNotFound(ObjectNotFoundException exception) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, exception.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler(ObjectAlreadyExistException.class)
    public ResponseEntity<Object> handleObjectAlreadyExist(ObjectAlreadyExistException exception) {
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, exception.getMessage());
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
