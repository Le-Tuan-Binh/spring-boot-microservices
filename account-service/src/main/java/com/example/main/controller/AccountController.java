package com.example.main.controller;

import com.example.main.constants.AccountConstants;
import com.example.main.dto.common.RestResponse;
import com.example.main.dto.external.AccountDTO;
import com.example.main.dto.external.CustomerDTO;
import com.example.main.service.account.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/accounts", produces = "application/json")
@AllArgsConstructor
public class AccountController {

    private IAccountService accountService;

    @PostMapping
    public ResponseEntity<RestResponse<AccountDTO>> createAccount(@RequestBody CustomerDTO customer) {

        accountService.createAccount(customer);

        RestResponse<AccountDTO> response = RestResponse.success(AccountConstants.MESSAGE_ACCOUNT_CREATED_SUCCESS,
                                                                 null);

        return ResponseEntity.status(AccountConstants.STATUS_CREATED).body(response);
    }

}

