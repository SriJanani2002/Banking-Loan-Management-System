package com.bank.loanmanagement.service;

import org.springframework.stereotype.Service;

import com.bank.loanmanagement.dto.AccountRequestDto;
import com.bank.loanmanagement.dto.AccountResponseDto;
import com.bank.loanmanagement.enums.AccountStatus;
import com.bank.loanmanagement.enums.AccountType;
import com.bank.loanmanagement.model.Account;
import com.bank.loanmanagement.model.User;
import com.bank.loanmanagement.repository.AccountRepository;
import com.bank.loanmanagement.repository.UserRepository;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountService {

	private final AccountRepository accountRepository;
	private final UserRepository userRepository;

	public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
		this.accountRepository = accountRepository;
		this.userRepository = userRepository;
	}

	// Create account
	public AccountResponseDto createAccount(String email, AccountRequestDto dto) {
		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		Account account = new Account(null, user, generateAccountNumber(), BigDecimal.ZERO,
				AccountType.valueOf(dto.getType()), AccountStatus.ACTIVE);

		accountRepository.save(account);
		return mapToDto(account);
	}

	// Get all accounts of logged-in user
	public List<AccountResponseDto> getMyAccounts(String email) {
		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		return accountRepository.findByUserId(user.getId()).stream().map(this::mapToDto).collect(Collectors.toList());
	}

	// Deposit
	public AccountResponseDto deposit(Long accountId, BigDecimal amount, String email) {
		Account account = getAccountOwnedByUser(accountId, email);
		account.setBalance(account.getBalance().add(amount));
		accountRepository.save(account);
		return mapToDto(account);
	}

	// Withdraw
	public AccountResponseDto withdraw(Long accountId, BigDecimal amount, String email) {
		Account account = getAccountOwnedByUser(accountId, email);
		if (account.getBalance().compareTo(amount) < 0)
			throw new RuntimeException("Insufficient balance");
		account.setBalance(account.getBalance().subtract(amount));
		accountRepository.save(account);
		return mapToDto(account);
	}

	// Helper: verify ownership
	private Account getAccountOwnedByUser(Long accountId, String email) {
		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new RuntimeException("Account not found"));
		if (!account.getUser().getEmail().equals(email))
			throw new RuntimeException("Unauthorized access");
		if (account.getStatus() != AccountStatus.ACTIVE)
			throw new RuntimeException("Account is inactive");
		return account;
	}

	// Generate unique 10-digit account number
	private String generateAccountNumber() {
		return "ACC" + (System.currentTimeMillis() % 1_000_000_000L);
	}

	// Map entity to DTO
	private AccountResponseDto mapToDto(Account account) {
		return new AccountResponseDto(account.getId(), account.getAccountNumber(), account.getBalance(),
				account.getType().name(), account.getStatus().name(), account.getUser().getName());
	}
}