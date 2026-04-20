package com.bank.loanmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.loanmanagement.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
	List<Account> findByUserId(Long userId);

	Optional<Account> findByAccountNumber(String accountNumber);
}