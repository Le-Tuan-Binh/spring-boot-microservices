package com.example.main.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private String path;

    private int statusCode;

    private String errorCode;

    private String message;

    private LocalDateTime timestamp;

    private List<ValidationError> errors;

    public static ErrorResponse of(String path, int statusCode, String errorCode, String message) {
        return new ErrorResponse(path, statusCode, errorCode, message, LocalDateTime.now(), null);
    }

    public static ErrorResponse ofValidation(String path, int statusCode, String errorCode, String message, List<ValidationError> errors) {
        return new ErrorResponse(path, statusCode, "VALIDATION_ERROR", message, LocalDateTime.now(), errors);
    }

}
