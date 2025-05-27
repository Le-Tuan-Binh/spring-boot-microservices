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
                                                       HttpStatus.BAD_REQUEST.getReasonPhrase(),
                                                       ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.of(request.getDescription(false).replace("uri=", ""),
                                                       HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                                       HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                                                       ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
