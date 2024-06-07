package CON.CON.controller;

import CON.CON.exception.DuplicatedError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler(DuplicatedError.class)
    public ResponseEntity<String> duplicated(DuplicatedError error) {
        return ResponseEntity.badRequest().body(error.getMessage());
    }
}
