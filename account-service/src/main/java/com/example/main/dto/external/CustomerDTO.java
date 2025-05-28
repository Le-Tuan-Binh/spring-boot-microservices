package com.example.main.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Customer information")
public class CustomerDTO {

    @Schema(description = "Full name of the customer", example = "Le Tuan Binh")
    @NotEmpty(message = "Name must not be empty")
    @Size(min = 5, max = 30, message = "Name must be between 5 and 30 characters")
    private String name;

    @Schema(description = "Email address of the customer", example = "ltbinh@gmail.com")
    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(description = "Mobile number (exactly 10 digits or empty)", example = "0987654321")
    @Pattern(regexp = "^$|[0-9]{10}", message = "Mobile number must be empty or exactly 10 digits")
    @JsonProperty("mobile_number")
    private String mobileNumber;

}
