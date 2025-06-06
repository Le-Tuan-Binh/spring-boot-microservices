package com.example.main.controller;

import com.example.main.constants.CardConstants;
import com.example.main.dto.common.ErrorResponse;
import com.example.main.dto.common.RestResponse;
import com.example.main.dto.external.CardDTO;
import com.example.main.service.card.ICardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/cards", produces = "application/json")
@AllArgsConstructor
@Validated
@Tag(
    name = "CRUD REST APIs for Card in NexBank",
    description = "CRUD REST APIs in NexBank to CREATE, UPDATE, FETCH AND DELETE card details")
public class CardController {

    private ICardService cardService;

    @Operation(summary = "Create a new card")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "201", description = "Card created successfully"), @ApiResponse(
            responseCode = "500", description = "HTTP Status Internal Server Error",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        })
    @PostMapping
    public ResponseEntity<RestResponse<CardDTO>> createAccount(
        @Valid
        @RequestParam
        @Pattern(
            regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
        String mobileNumber) {

        cardService.createCard(mobileNumber);

        RestResponse<CardDTO> response = RestResponse.success(CardConstants.STATUS_CREATED,
            CardConstants.MESSAGE_CARD_CREATED_SUCCESS, null);

        return ResponseEntity.status(CardConstants.STATUS_CREATED).body(response);
    }

    @Operation(summary = "Get card details by mobile number")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "Card details retrieved"), @ApiResponse(
            responseCode = "500", description = "HTTP Status Internal Server Error",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        })
    @GetMapping
    public ResponseEntity<RestResponse<CardDTO>> getCardDetailsByMobileNumber(
        @Pattern(
            regexp = "^$|[0-9]{10}", message = "Mobile number must be empty or exactly 10 digits")
        @RequestParam
        String mobileNumber) {
        CardDTO cardDTO = cardService.getCardDetailByMobileNumber(mobileNumber);
        RestResponse<CardDTO> response = RestResponse.success(CardConstants.STATUS_OK, "Get card details successfully",
            cardDTO);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update an card")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "Card updated successfully"), @ApiResponse(
            responseCode = "417", description = "Expectation Failed",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))), @ApiResponse(
            responseCode = "500", description = "HTTP Status Internal Server Error",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        })
    @PutMapping
    public ResponseEntity<RestResponse> updateCard(
        @Valid
        @RequestBody
        CardDTO cardDTO) {
        boolean isUpdated = cardService.updateCard(cardDTO);
        if (isUpdated) {
            return ResponseEntity.ok(
                RestResponse.success(CardConstants.STATUS_OK, CardConstants.MESSAGE_CARD_UPDATE_SUCCESS, null));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(RestResponse.error(CardConstants.STATUS_EXPECTATION_FAILED,
                    CardConstants.MESSAGE_CARD_UPDATE_FAILED));
        }
    }

    @Operation(summary = "Delete an card by mobile number")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "Card deleted successfully"),
            @ApiResponse(responseCode = "417", description = "Expectation Failed"),
            @ApiResponse(
                responseCode = "500", description = "HTTP Status Internal Server Error",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        })
    @DeleteMapping
    public ResponseEntity<RestResponse> deleteCardByMobileNumber(
        @Pattern(
            regexp = "^$|[0-9]{10}", message = "Mobile number must be empty or exactly 10 digits")
        @RequestParam
        String mobileNumber) {
        boolean isDeleted = cardService.deleteCard(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.ok(RestResponse.success(CardConstants.STATUS_OK, "Card deleted successfully", null));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(RestResponse.error(500, "Delete failed"));
        }
    }


}
