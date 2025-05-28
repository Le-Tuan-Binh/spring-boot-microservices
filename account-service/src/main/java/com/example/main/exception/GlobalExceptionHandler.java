package com.example.main.exception;

import com.example.main.dto.common.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.of(request.getDescription(false).replace("uri=", ""),
                                                       HttpStatus.BAD_REQUEST.value(),
                                                       "CUSTOMER_ALREADY_EXISTS",
                                                       ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.of(request.getDescription(false).replace("uri=", ""),
                                                       HttpStatus.NOT_FOUND.value(),
                                                       "NOT_FOUND",
                                                       ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.of(request.getDescription(false).replace("uri=", ""),
                                                       HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                                       "INTERNAL_SERVER_ERROR",
                                                       ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
