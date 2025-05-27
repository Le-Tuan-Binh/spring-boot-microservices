package com.example.main.service.account;

import com.example.main.dto.external.CustomerDTO;

public interface IAccountService {

    /**
     * Creates a new account based on the provided customer information.
     *
     * @param customer
     */
    void createAccount(CustomerDTO customer);

}
