package com.example.main.dto.response;

import com.example.main.dto.external.AccountDTO;
import com.example.main.dto.external.CustomerDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerAccountDTO {

    @Valid
    private CustomerDTO customer;

    @Valid
    private AccountDTO account;

}
