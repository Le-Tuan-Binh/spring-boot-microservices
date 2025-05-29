package com.example.main.service.loan;

import com.example.main.constants.LoanConstants;
import com.example.main.dto.external.LoanDTO;
import com.example.main.entity.Loan;
import com.example.main.exception.LoanAlreadyExistsException;
import com.example.main.exception.ResourceNotFoundException;
import com.example.main.mapper.LoanMapper;
import com.example.main.repository.LoanRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
@Transactional
public class LoanServiceImpl implements ILoanService {

    private final LoanRepository loanRepository;

    private final LoanMapper loanMapper;

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loan> optionalLoans = loanRepository.findByMobileNumber(mobileNumber);
        if (optionalLoans.isPresent()) {
            throw new LoanAlreadyExistsException("A loan record already exists for the provided mobile number");
        }
        loanRepository.save(createNewLoan(mobileNumber));
    }

    private Loan createNewLoan(String mobileNumber) {
        Loan newLoan = new Loan();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoanConstants.LOAN_TYPE_HOME);
        newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    @Override
    public LoanDTO getLoan(String mobileNumber) {
        Loan loans = loanRepository
                .findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobile number", mobileNumber));
        return loanMapper.toDto(loans);
    }

    @Override
    public boolean updateLoan(LoanDTO loanDTO) {
        Loan loans = loanRepository
                .findByLoanNumber(loanDTO.getLoanNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "loan number", loanDTO.getLoanNumber()));
        loanMapper.updateEntityFromDto(loanDTO, loans);
        loanRepository.save(loans);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loan loans = loanRepository
                .findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobile number", mobileNumber));
        loanRepository.deleteById(loans.getLoanId());
        return true;
    }

}