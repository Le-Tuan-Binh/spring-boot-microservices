package com.example.main.dto.external;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "Loan information for a customer")
public class LoanDTO {

    @Schema(description = "Mobile number of the customer", example = "0987654321")
    @JsonProperty("mobile_number")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
    @NotEmpty(message = "Mobile number must not be empty")
    private String mobileNumber;

    @Schema(description = "Unique loan number", example = "LN123456789")
    @JsonProperty("loan_number")
    @Pattern(regexp="(^$|[0-9]{12})",message = "Loan Number must be 12 digits")
    @NotEmpty(message = "Loan number must not be empty")
    private String loanNumber;

    @Schema(description = "Type of loan (e.g., Home Loan, Personal Loan)", example = "Home Loan")
    @JsonProperty("loan_type")
    @NotEmpty(message = "Loan type must not be empty")
    private String loanType;

    @Schema(description = "Total loan amount", example = "1000000")
    @JsonProperty("total_loan")
    @Positive(message = "Total loan amount must be greater than 0")
    private Integer totalLoan;

    @Schema(description = "Amount paid so far", example = "250000")
    @JsonProperty("amount_paid")
    @Min(value = 0, message = "Amount paid must be zero or positive")
    private Integer amountPaid;

    @Schema(description = "Outstanding loan amount", example = "750000")
    @JsonProperty("outstanding_amount")
    @Min(value = 0, message = "Outstanding amount must be zero or positive")
    private Integer outstandingAmount;


}
