package com.example.main.service.card;

import com.example.main.dto.external.CustomerDTO;
import com.example.main.dto.response.CustomerAccountDTO;

public interface IAccountService {

    /**
     * Creates a new account based on the provided customer information.
     *
     * @param customer
     */
    void createAccount(CustomerDTO customer);

    /**
     * Get an account detail based on the provided mobile number.
     *
     * @param mobileNumber
     */
    CustomerAccountDTO getAccountDetailsByMobileNumber(String mobileNumber);

    boolean updateAccount(CustomerAccountDTO customerAccountDTO);

    boolean deleteAccount(String mobileNumber);

}
