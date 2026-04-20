package com.bank.loanmanagement.controller;

import com.bank.loanmanagement.dto.*;
import com.bank.loanmanagement.service.LoanService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

	private final LoanService loanService;

	public LoanController(LoanService loanService) {
		this.loanService = loanService;
	}

	// Customer — Apply for loan
	@PostMapping("/apply")
	public ResponseEntity<LoanResponseDto> applyLoan(@Valid @RequestBody LoanRequestDto dto, Principal principal) {
		return ResponseEntity.ok(loanService.applyLoan(principal.getName(), dto));
	}

	// Customer — View my loans
	@GetMapping("/my")
	public ResponseEntity<List<LoanResponseDto>> getMyLoans(Principal principal) {
		return ResponseEntity.ok(loanService.getMyLoans(principal.getName()));
	}

	// Customer — Repay loan
	@PostMapping("/{loanId}/repay")
	public ResponseEntity<RepaymentResponseDto> repayLoan(@PathVariable Long loanId, @RequestParam BigDecimal amount,
			Principal principal) {
		return ResponseEntity.ok(loanService.repayLoan(principal.getName(), loanId, amount));
	}

	// Customer — Repayment history
	@GetMapping("/{loanId}/repayments")
	public ResponseEntity<List<RepaymentResponseDto>> getRepayments(@PathVariable Long loanId, Principal principal) {
		return ResponseEntity.ok(loanService.getRepaymentHistory(principal.getName(), loanId));
	}

	// Admin — View all loans
	@GetMapping("/admin/all")
	public ResponseEntity<List<LoanResponseDto>> getAllLoans() {
		return ResponseEntity.ok(loanService.getAllLoans());
	}

	// Admin — Approve or Reject loan
	@PutMapping("/admin/{loanId}/status")
	public ResponseEntity<LoanResponseDto> updateStatus(@PathVariable Long loanId, @RequestParam String status) {
		return ResponseEntity.ok(loanService.updateLoanStatus(loanId, status));
	}
}