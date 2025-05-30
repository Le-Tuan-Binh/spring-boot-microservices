package com.example.main.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Validation error detail for a specific field")
public class ValidationError {

    @Schema(description = "The name of the field that has the validation error", example = "email")
    private String field;

    @Schema(description = "Validation error message associated with the field", example = "Email should be valid")
    private String message;

}
