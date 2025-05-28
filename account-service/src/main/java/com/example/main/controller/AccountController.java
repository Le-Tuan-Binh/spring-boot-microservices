package com.example.main.controller;

import com.example.main.constants.AccountConstants;
import com.example.main.dto.common.ErrorResponse;
import com.example.main.dto.common.RestResponse;
import com.example.main.dto.external.AccountDTO;
import com.example.main.dto.external.CustomerDTO;
import com.example.main.dto.response.CustomerAccountDTO;
import com.example.main.service.account.IAccountService;
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
@RequestMapping(path = "/api/v1/accounts", produces = "application/json")
@AllArgsConstructor
@Validated
@Tag(
        name = "CRUD REST APIs for Accounts in NexBank",
        description = "CRUD REST APIs in NexBank to CREATE, UPDATE, FETCH AND DELETE account details"
)
public class AccountController {

    private IAccountService accountService;

    @Operation(summary = "Create a new account")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Account created successfully"),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponse.class)
            )
        )
    })
    @PostMapping
    public ResponseEntity<RestResponse<AccountDTO>> createAccount(@Valid @RequestBody CustomerDTO customer) {

        accountService.createAccount(customer);

        RestResponse<AccountDTO> response = RestResponse.success(AccountConstants.MESSAGE_ACCOUNT_CREATED_SUCCESS,
                                                                 null);

        return ResponseEntity.status(AccountConstants.STATUS_CREATED).body(response);
    }

    @Operation(summary = "Get account details by mobile number")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Account details retrieved"),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponse.class)
            )
        )
    })
    @GetMapping
    public ResponseEntity<RestResponse<CustomerAccountDTO>> getAccountDetailsByMobileNumber(@Pattern(regexp = "^$|[0-9]{10}",
                                                                                                     message = "Mobile number must be empty or exactly 10 digits") @RequestParam String mobileNumber) {
        CustomerAccountDTO customerAccountDTO = accountService.getAccountDetailsByMobileNumber(mobileNumber);
        RestResponse<CustomerAccountDTO> response = RestResponse.success("Get account details successfully",
                                                                         customerAccountDTO);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update an account")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Account updated successfully"),
        @ApiResponse(
            responseCode = "417",
            description = "Expectation Failed"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponse.class)
            )
        )
    })
    @PutMapping
    public ResponseEntity<RestResponse> updateAccount(@Valid @RequestBody CustomerAccountDTO customerAccountDTO) {
        boolean isUpdated = accountService.updateAccount(customerAccountDTO);
        if (isUpdated) {
            return ResponseEntity.ok(RestResponse.success(AccountConstants.MESSAGE_ACCOUNT_UPDATE_SUCCESS, null));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                                 .body(RestResponse.error(AccountConstants.STATUS_EXPECTATION_FAILED,
                                                          AccountConstants.MESSAGE_UPDATE_FAILED));
        }
    }

    @Operation(summary = "Delete an account by mobile number")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Account deleted successfully"),
        @ApiResponse(
            responseCode = "417",
            description = "Expectation Failed"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error",
            content = @Content(
                    schema = @Schema(implementation = ErrorResponse.class)
            )
        )
    })
    @DeleteMapping
    public ResponseEntity<RestResponse> deleteAccount(@Pattern(regexp = "^$|[0-9]{10}",
                                                               message = "Mobile number must be empty or exactly 10 digits") @RequestParam String mobileNumber) {
        boolean isDeleted = accountService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.ok(RestResponse.success("Account deleted successfully", null));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(RestResponse.error(500, "Delete failed"));
        }
    }


}

