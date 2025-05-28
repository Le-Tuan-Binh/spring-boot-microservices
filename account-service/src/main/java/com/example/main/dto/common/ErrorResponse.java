package com.example.main.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String path;

    private int statusCode;

    private String error;

    private String message;

    private LocalDateTime timestamp;

    private List<ValidationError> errors;

    public static ErrorResponse of(String path, int statusCode, String error, String message, List<ValidationError> errors) {
        return new ErrorResponse(path, statusCode, error, message, LocalDateTime.now(), errors);
    }

    public static ErrorResponse of(String path, int statusCode, String error, String message) {
        return new ErrorResponse(path, statusCode, error, message, LocalDateTime.now(), null);
    }


}
