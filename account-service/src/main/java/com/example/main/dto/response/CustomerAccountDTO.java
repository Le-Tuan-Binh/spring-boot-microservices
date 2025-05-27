package com.example.main.dto.response;

import com.example.main.dto.external.AccountDTO;
import com.example.main.dto.external.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerAccountDTO {

    private CustomerDTO customer;

    private AccountDTO account;

}
