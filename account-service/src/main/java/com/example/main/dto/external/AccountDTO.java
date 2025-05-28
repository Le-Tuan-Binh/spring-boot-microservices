package com.example.main.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountDTO {

    @NotNull(message = "Account number must not be null")
    @JsonProperty("account_number")
    @Min(value = 1_000_000_000L, message = "Account number must have exactly 10 digits")
    @Max(value = 9_999_999_999L, message = "Account number must have exactly 10 digits")
    private Long accountNumber;


    @JsonProperty("account_type")
    @NotEmpty(message = "Account type must not be empty")
    private String accountType;

    @JsonProperty("branch_address")
    @NotEmpty(message = "Branch address must not be empty")
    private String branchAddress;

}
