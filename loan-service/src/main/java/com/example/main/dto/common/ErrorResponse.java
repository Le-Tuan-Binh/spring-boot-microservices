package com.example.main.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Standard error response structure")
public class ErrorResponse {

    @Schema(description = "Request path that caused the error")
    private String path;

    @Schema(description = "HTTP status code", type = "integer")
    private int statusCode;

    @Schema(description = "Error code")
    private String errorCode;

    @Schema(description = "Error message")
    private String message;

    @Schema(description = "Timestamp when the error occurred")
    private LocalDateTime timestamp;

    @Schema(description = "List of validation errors, if any")
    private List<ValidationError> errors;

    public static ErrorResponse of(String path, int statusCode, String errorCode, String message) {
        return new ErrorResponse(path, statusCode, errorCode, message, LocalDateTime.now(), null);
    }

    public static ErrorResponse ofValidation(
        String path,
        int statusCode,
        String errorCode,
        String message,
        List<ValidationError> errors) {
        return new ErrorResponse(path, statusCode, "VALIDATION_ERROR", message, LocalDateTime.now(), errors);
    }

}
