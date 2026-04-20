package com.bank.loanmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.bank.loanmanagement.dto.AccountRequestDto;
import com.bank.loanmanagement.dto.AccountResponseDto;
import com.bank.loanmanagement.service.AccountService;

import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	private final AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	// POST /api/accounts/create
	@PostMapping("/create")
	public ResponseEntity<AccountResponseDto> createAccount(Principal principal,
			@Valid @RequestBody AccountRequestDto dto) {
		return ResponseEntity.ok(accountService.createAccount(principal.getName(), dto));
	}

	// GET /api/accounts/my
	@GetMapping("/my")
	public ResponseEntity<List<AccountResponseDto>> getMyAccounts(Principal principal) {
		return ResponseEntity.ok(accountService.getMyAccounts(principal.getName()));

	}

	// PUT /api/accounts/{id}/deposit?amount=500
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountResponseDto> deposit(@PathVariable Long id, @RequestParam BigDecimal amount,
			Principal principal) {
		return ResponseEntity.ok(accountService.deposit(id, amount, principal.getName()));
	}

	// PUT /api/accounts/{id}/withdraw?amount=200
	@PutMapping("/{id}/withdraw")
	public ResponseEntity<AccountResponseDto> withdraw(@PathVariable Long id, @RequestParam BigDecimal amount,
			Principal principal) {
		return ResponseEntity.ok(accountService.withdraw(id, amount, principal.getName()));
	}
}