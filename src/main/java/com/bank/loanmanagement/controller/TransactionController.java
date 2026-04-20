package com.bank.loanmanagement.controller;

import com.bank.loanmanagement.dto.*;
import com.bank.loanmanagement.service.TransactionService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

	private final TransactionService transactionService;

	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	// POST /api/transactions/transfer/{fromAccountId}
	@PostMapping("/transfer/{fromAccountId}")
	public ResponseEntity<TransactionResponseDto> transfer(@PathVariable Long fromAccountId,
			@Valid @RequestBody TransferRequestDto dto, Principal principal) {
		return ResponseEntity.ok(transactionService.transfer(principal.getName(), fromAccountId, dto));
	}

	// GET /api/transactions/history/{accountId}
	@GetMapping("/history/{accountId}")
	public ResponseEntity<List<TransactionResponseDto>> getHistory(@PathVariable Long accountId, Principal principal) {
		return ResponseEntity.ok(transactionService.getHistory(principal.getName(), accountId));
	}
}