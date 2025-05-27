package com.example.main.repository;

import com.example.main.entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByAccountNumber(Long accountNumber);

    Optional<Account> findByCustomerID(Long customerID);

    @Transactional
    @Modifying
    void deleteByCustomerID(Long customerID);


}
