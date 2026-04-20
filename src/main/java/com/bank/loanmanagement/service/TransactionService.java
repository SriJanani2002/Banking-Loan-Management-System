package com.bank.loanmanagement.service;

import com.bank.loanmanagement.dto.*;
import com.bank.loanmanagement.model.*;
import com.bank.loanmanagement.enums.*;
import com.bank.loanmanagement.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

	private final TransactionRepository transactionRepository;
	private final AccountRepository accountRepository;
	private final UserRepository userRepository;

	public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository,
			UserRepository userRepository) {
		this.transactionRepository = transactionRepository;
		this.accountRepository = accountRepository;
		this.userRepository = userRepository;
	}

	// Fund Transfer
	@Transactional
	public TransactionResponseDto transfer(String email, Long fromAccountId, TransferRequestDto dto) {

		// Get sender account and verify ownership
		Account fromAccount = accountRepository.findById(fromAccountId)
				.orElseThrow(() -> new RuntimeException("Sender account not found"));

		if (!fromAccount.getUser().getEmail().equals(email))
			throw new RuntimeException("Unauthorized access");

		if (fromAccount.getStatus() != AccountStatus.ACTIVE)
			throw new RuntimeException("Sender account is inactive");

		if (fromAccount.getBalance().compareTo(dto.getAmount()) < 0)
			throw new RuntimeException("Insufficient balance");

		// Get receiver account
		Account toAccount = accountRepository.findByAccountNumber(dto.getToAccountNumber())
				.orElseThrow(() -> new RuntimeException("Receiver account not found"));

		if (toAccount.getStatus() != AccountStatus.ACTIVE)
			throw new RuntimeException("Receiver account is inactive");

		// Deduct from sender
		fromAccount.setBalance(fromAccount.getBalance().subtract(dto.getAmount()));

		// Add to receiver
		toAccount.setBalance(toAccount.getBalance().add(dto.getAmount()));

		accountRepository.save(fromAccount);
		accountRepository.save(toAccount);

		// Save transaction record
		Transaction transaction = new Transaction(null, fromAccount, toAccount, dto.getAmount(),
				TransactionType.TRANSFER, LocalDateTime.now());
		transactionRepository.save(transaction);

		return mapToDto(transaction);
	}

	// Transaction History
	public List<TransactionResponseDto> getHistory(String email, Long accountId) {
		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new RuntimeException("Account not found"));

		if (!account.getUser().getEmail().equals(email))
			throw new RuntimeException("Unauthorized access");

		return transactionRepository.findByAccountId(accountId).stream().map(this::mapToDto)
				.collect(Collectors.toList());
	}

	// Map to DTO
	private TransactionResponseDto mapToDto(Transaction t) {
		String from = t.getFromAccount() != null ? t.getFromAccount().getAccountNumber() : "N/A";
		String to = t.getToAccount() != null ? t.getToAccount().getAccountNumber() : "N/A";
		return new TransactionResponseDto(t.getId(), from, to, t.getAmount(), t.getType().name(), t.getCreatedAt());
	}
}