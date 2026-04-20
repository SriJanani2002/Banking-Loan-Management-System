package com.bank.loanmanagement.service;

import com.bank.loanmanagement.dto.*;
import com.bank.loanmanagement.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

	private final UserRepository userRepository;
	private final AccountRepository accountRepository;
	private final TransactionRepository transactionRepository;
	private final LoanRepository loanRepository;

	public AdminService(UserRepository userRepository, AccountRepository accountRepository,
			TransactionRepository transactionRepository, LoanRepository loanRepository) {
		this.userRepository = userRepository;
		this.accountRepository = accountRepository;
		this.transactionRepository = transactionRepository;
		this.loanRepository = loanRepository;
	}

	// Get all users
	public List<UserResponseDto> getAllUsers() {
		return userRepository.findAll().stream()
				.map(user -> new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getRole().name()))
				.collect(Collectors.toList());
	}

	// Get all accounts
	public List<AccountResponseDto> getAllAccounts() {
		return accountRepository.findAll().stream()
				.map(account -> new AccountResponseDto(account.getId(), account.getAccountNumber(),
						account.getBalance(), account.getType().name(), account.getStatus().name(),
						account.getUser().getName()))
				.collect(Collectors.toList());
	}

	// Get all transactions
	public List<TransactionResponseDto> getAllTransactions() {
		return transactionRepository.findAll().stream()
				.map(t -> new TransactionResponseDto(t.getId(),
						t.getFromAccount() != null ? t.getFromAccount().getAccountNumber() : "N/A",
						t.getToAccount() != null ? t.getToAccount().getAccountNumber() : "N/A", t.getAmount(),
						t.getType().name(), t.getCreatedAt()))
				.collect(Collectors.toList());
	}

	// Get all loans
	public List<LoanResponseDto> getAllLoans() {
		return loanRepository.findAll().stream()
				.map(loan -> new LoanResponseDto(loan.getId(), loan.getUser().getName(), loan.getAmount(),
						loan.getInterestRate(), loan.getTenureMonths(), loan.getEmiAmount(), loan.getStatus().name(),
						loan.getAppliedAt()))
				.collect(Collectors.toList());
	}
}