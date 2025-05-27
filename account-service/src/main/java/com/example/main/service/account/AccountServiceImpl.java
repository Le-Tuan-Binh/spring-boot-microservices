package com.example.main.service.account;

import com.example.main.constants.AccountConstants;
import com.example.main.dto.external.CustomerDTO;
import com.example.main.entity.Account;
import com.example.main.entity.Customer;
import com.example.main.exception.CustomerAlreadyExistsException;
import com.example.main.mapper.AccountMapper;
import com.example.main.mapper.CustomerMapper;
import com.example.main.repository.AccountRepository;
import com.example.main.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class AccountServiceImpl implements IAccountService {

    private final AccountRepository accountRepository;

    private final CustomerRepository customerRepository;

    private final AccountMapper accountMapper;

    private final CustomerMapper customerMapper;

    private final Random random = new Random();

    public AccountServiceImpl(AccountMapper accountMapper, AccountRepository accountRepository, CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.accountMapper = accountMapper;
        this.customerMapper = customerMapper;
    }

    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        Optional<Customer> customerOptional = customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
        if (customerOptional.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer with this email already exists.");
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Le Tuan Binh");
        Customer savedCustomer = customerRepository.save(customer);
        Account account = createNewAccount(savedCustomer);
        accountRepository.save(account);
    }

    private Account createNewAccount(Customer customer) {
        Account account = new Account();
        account.setCustomerID(customer.getCustomerID());
        account.setAccountNumber(generateUniqueAccountNumber());
        account.setAccountType(AccountConstants.ACCOUNT_TYPE_SAVINGS);
        account.setBranchAddress(AccountConstants.ACCOUNT_ADDRESS);
        account.setCreatedAt(LocalDateTime.now());
        account.setCreatedBy("Le Tuan Binh");
        return account;

    }

    private Long generateUniqueAccountNumber() {
        Long accountNumber;
        do {
            accountNumber = generateRandomAccountNumber();
        } while (accountRepository.existsByAccountNumber(accountNumber));
        return accountNumber;
    }

    private Long generateRandomAccountNumber() {
        long min = 1_000_000_000L;
        long max = 9_999_999_999L;
        return min + (long) (random.nextDouble() * (max - min));
    }

}
