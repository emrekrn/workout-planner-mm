package com.emrecan.workoutplanner.util;

import com.emrecan.workoutplanner.exceptions.UserConflictException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(value = {UserConflictException.class})
    public ResponseEntity<String> handleUserAlreadyExistsException(UserConflictException e) {
        log.info("Restcontrolleradvice has been triggered");
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
}
