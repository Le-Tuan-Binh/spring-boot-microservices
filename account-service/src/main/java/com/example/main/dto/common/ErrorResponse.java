package com.example.main.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String path;

    private int statusCode;

    private String error;

    private String message;

    private LocalDateTime timestamp;

    public static ErrorResponse of(String path, int statusCode, String error, String message) {
        return new ErrorResponse(path, statusCode, error, message, LocalDateTime.now());
    }

}
