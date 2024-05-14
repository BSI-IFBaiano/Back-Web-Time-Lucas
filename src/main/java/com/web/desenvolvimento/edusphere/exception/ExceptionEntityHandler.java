package com.web.desenvolvimento.edusphere.exception;

import com.web.desenvolvimento.edusphere.domain.user.exceptions.UserInvalidUpdateException;
import com.web.desenvolvimento.edusphere.domain.user.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionEntityHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(UserInvalidUpdateException.class)
    public ResponseEntity<String> handleInvalidUpdate(UserInvalidUpdateException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }
}
