package com.bank.loanmanagement.controller;

import com.bank.loanmanagement.dto.*;
import com.bank.loanmanagement.service.AdminService;
import com.bank.loanmanagement.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	private final AdminService adminService;
	private final LoanService loanService;

	public AdminController(AdminService adminService, LoanService loanService) {
		this.adminService = adminService;
		this.loanService = loanService;
	}

	// View all users
	@GetMapping("/users")
	public ResponseEntity<List<UserResponseDto>> getAllUsers() {
		return ResponseEntity.ok(adminService.getAllUsers());
	}

	// View all accounts
	@GetMapping("/accounts")
	public ResponseEntity<List<AccountResponseDto>> getAllAccounts() {
		return ResponseEntity.ok(adminService.getAllAccounts());
	}

	// View all transactions
	@GetMapping("/transactions")
	public ResponseEntity<List<TransactionResponseDto>> getAllTransactions() {
		return ResponseEntity.ok(adminService.getAllTransactions());
	}

	// View all loans
	@GetMapping("/loans")
	public ResponseEntity<List<LoanResponseDto>> getAllLoans() {
		return ResponseEntity.ok(adminService.getAllLoans());
	}

	// Approve or Reject loan
	@PutMapping("/loans/{loanId}/status")
	public ResponseEntity<LoanResponseDto> updateLoanStatus(@PathVariable Long loanId, @RequestParam String status) {
		return ResponseEntity.ok(loanService.updateLoanStatus(loanId, status));
	}
}