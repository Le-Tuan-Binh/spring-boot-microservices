package com.example.main.service.loan;


import com.example.main.dto.external.LoanDTO;

public interface ILoanService {


    void createLoan(String mobileNumber);

    LoanDTO getLoan(String mobileNumber);

    boolean updateLoan(LoanDTO loanDto);

    boolean deleteLoan(String mobileNumber);

}