package com.example.main.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(
    name = "Cards", description = "Card information for a customer")
@Data
public class CardDTO {

    @NotEmpty(message = "Mobile number must not be null or empty.")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must consist of exactly 10 digits.")
    @Schema(
        description = "Mobile number of the customer.", example = "4354437687")
    @JsonProperty("mobile_number")
    private String mobileNumber;

    @NotEmpty(message = "Card number must not be null or empty.")
    @Pattern(regexp = "(^$|[0-9]{12})", message = "Card number must consist of exactly 12 digits.")
    @Schema(
        description = "Card number of the customer.", example = "100646930341")
    @JsonProperty("card_number")
    private String cardNumber;

    @NotEmpty(message = "Card type must not be null or empty.")
    @Schema(
        description = "Type of the card.", example = "Credit Card")
    @JsonProperty("card_type")
    private String cardType;

    @Positive(message = "Total limit must be greater than zero.")
    @Schema(
        description = "Total credit limit available on the card.", example = "100000")
    @JsonProperty("total_limit")
    private int totalLimit;

    @PositiveOrZero(message = "Amount used must be zero or a positive number.")
    @Schema(
        description = "Total amount used by the customer.", example = "1000")
    @JsonProperty("amount_used")
    private int amountUsed;

    @PositiveOrZero(message = "Available amount must be zero or a positive number.")
    @Schema(
        description = "Total available amount on the card.", example = "90000")
    @JsonProperty("available_amount")
    private int availableAmount;

}
