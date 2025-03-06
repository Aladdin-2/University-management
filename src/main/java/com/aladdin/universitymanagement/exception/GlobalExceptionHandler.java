package com.aladdin.universitymanagement.exception;

import com.aladdin.universitymanagement.model.dto.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.rmi.AlreadyBoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class GlobalExceptionHandler {

    private LocalDateTime now = LocalDateTime.now();

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> responseHandler(RuntimeException ex, WebRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");
        String formattedDate = now.format(formatter);
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false),
                formattedDate
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyBoundException.class)
    public ResponseEntity<ErrorResponse> responseHandler(AlreadyBoundException ex, WebRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");
        String formattedDate = now.format(formatter);
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                request.getDescription(false),
                formattedDate
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
