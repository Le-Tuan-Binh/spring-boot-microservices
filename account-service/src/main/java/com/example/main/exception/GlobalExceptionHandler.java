package com.example.main.exception;

import com.example.main.dto.common.ErrorResponse;
import com.example.main.dto.common.ValidationError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.of(request.getDescription(false)
                                                               .replace("uri=", ""),
                                                       HttpStatus.BAD_REQUEST.value(),
                                                       "CUSTOMER_ALREADY_EXISTS",
                                                       ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.of(request.getDescription(false)
                                                               .replace("uri=", ""),
                                                       HttpStatus.NOT_FOUND.value(),
                                                       "NOT_FOUND",
                                                       ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        String fullMessage = ex.getMessage();
        String message = extractSimpleMessage(fullMessage);

        ErrorResponse errorResponse = ErrorResponse.of(request.getDescription(false)
                                                               .replace("uri=", ""),
                                                       HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                                       "INTERNAL_SERVER_ERROR",
                                                       message,
                                                       null);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String extractSimpleMessage(String fullMessage) {
        if (fullMessage == null) return "Unexpected error occurred";
        int lastColon = fullMessage.lastIndexOf(':');
        if (lastColon != -1 && lastColon < fullMessage.length() - 1) {
            return fullMessage.substring(lastColon + 1).trim();
        }
        return fullMessage;
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        System.out.println(ex);
        List<ValidationError> validationErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ValidationError(toSnakeCase(error.getField()),
                                                  error.getDefaultMessage()))
                .collect(Collectors.toList());

        String combinedMessage = "Validation failed";

        ErrorResponse errorResponse = ErrorResponse.of(request.getDescription(false)
                                                               .replace("uri=", ""),
                                                       HttpStatus.BAD_REQUEST.value(),
                                                       "VALIDATION_FAILED",
                                                       combinedMessage,
                                                       validationErrors);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private String toSnakeCase(String camelCase) {
        return camelCase.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
    }


}
