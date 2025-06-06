package com.example.main.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "Account", description = "Account information for customer")
public class AccountDTO {

    @Schema(
        description = "Unique 10-digit account number", example = "1234567890", minimum = "1000000000",
        maximum = "9999999999")
    @NotNull(message = "Account number must not be null")
    @JsonProperty("account_number")
    @Min(value = 1_000_000_000L, message = "Account number must have exactly 10 digits")
    @Max(value = 9_999_999_999L, message = "Account number must have exactly 10 digits")
    private Long accountNumber;

    @Schema(description = "Type of the account (e.g., Savings, Checking)", example = "Savings")
    @JsonProperty("account_type")
    @NotEmpty(message = "Account type must not be empty")
    private String accountType;

    @Schema(description = "Branch address where the account is registered", example = "123 Main St, Hanoi")
    @JsonProperty("branch_address")
    @NotEmpty(message = "Branch address must not be empty")
    private String branchAddress;

}
