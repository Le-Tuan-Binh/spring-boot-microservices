package com.example.main.controller;

import com.example.main.constants.LoanConstants;
import com.example.main.dto.common.RestResponse;
import com.example.main.dto.external.LoanDTO;
import com.example.main.service.loan.ILoanService;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CRUD REST APIs for Loans in NexBank",
     description = "CRUD REST APIs in NexBank to CREATE, UPDATE, get AND DELETE loan details")
@RestController
@RequestMapping(path = "/api/v1/loans", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class LoanController {

    private ILoanService iLoanService;

    @Operation(summary = "Create Loan")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error",
                         content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<RestResponse<LoanDTO>> createLoan(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})",
                                   message = "Mobile number must be empty or exactly 10 digits") String mobileNumber) {
        iLoanService.createLoan(mobileNumber);
        RestResponse<LoanDTO> response = RestResponse.success(LoanConstants.STATUS_CREATED,
                                                              LoanConstants.MESSAGE_LOAN_CREATED_SUCCESS, null);
        return ResponseEntity.status(LoanConstants.STATUS_CREATED).body(response);
    }

    @Operation(summary = "Get loan details by mobile number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error",
                         content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping
    public ResponseEntity<RestResponse<LoanDTO>> getLoanDetails(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})",
                                   message = "Mobile number must be empty or exactly 10 digits") String mobileNumber) {
        LoanDTO loanDTO = iLoanService.getLoan(mobileNumber);
        RestResponse<LoanDTO> response = RestResponse.success(LoanConstants.STATUS_OK, "Get loan details successfully",
                                                              loanDTO);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update loan details")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "417", description = "Expectation Failed"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error",
                         content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping
    public ResponseEntity<RestResponse> updateLoanDetails(@Valid @RequestBody LoanDTO loansDto) {
        boolean isUpdated = iLoanService.updateLoan(loansDto);
        if (isUpdated) {
            return ResponseEntity.ok(
                    RestResponse.success(LoanConstants.STATUS_OK, LoanConstants.MESSAGE_LOAN_UPDATE_SUCCESS, null));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(RestResponse.error(LoanConstants.STATUS_EXPECTATION_FAILED,
                                             LoanConstants.MESSAGE_UPDATE_FAILED));
        }
    }

    @Operation(summary = "Delete loan details")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
            @ApiResponse(responseCode = "417", description = "Expectation Failed"),
            @ApiResponse(responseCode = "500", description = "HTTP Status Internal Server Error",
                         content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping
    public ResponseEntity<RestResponse> deleteLoanDetails(
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})",
                                   message = "Mobile number must be 10 digits") String mobileNumber) {
        boolean isDeleted = iLoanService.deleteLoan(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.ok(RestResponse.success(LoanConstants.STATUS_OK, "Loan deleted successfully", null));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(RestResponse.error(500, "Delete failed"));
        }
    }

}