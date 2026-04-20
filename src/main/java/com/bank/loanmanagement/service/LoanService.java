package com.bank.loanmanagement.service;

import com.bank.loanmanagement.dto.*;
import com.bank.loanmanagement.model.*;
import com.bank.loanmanagement.enums.LoanStatus;
import com.bank.loanmanagement.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

	private final LoanRepository loanRepository;
	private final LoanRepaymentRepository loanRepaymentRepository;
	private final UserRepository userRepository;

	public LoanService(LoanRepository loanRepository, LoanRepaymentRepository loanRepaymentRepository,
			UserRepository userRepository) {
		this.loanRepository = loanRepository;
		this.loanRepaymentRepository = loanRepaymentRepository;
		this.userRepository = userRepository;
	}

	// Apply for loan (Customer)
	public LoanResponseDto applyLoan(String email, LoanRequestDto dto) {
		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

		BigDecimal emi = calculateEmi(dto.getAmount(), dto.getInterestRate(), dto.getTenureMonths());

		Loan loan = new Loan(null, user, dto.getAmount(), dto.getInterestRate(), dto.getTenureMonths(), emi,
				LoanStatus.PENDING, LocalDateTime.now(), LocalDateTime.now());

		loanRepository.save(loan);
		return mapToDto(loan);
	}

	// Get my loans (Customer)
	public List<LoanResponseDto> getMyLoans(String email) {
		User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
		return loanRepository.findByUserId(user.getId()).stream().map(this::mapToDto).collect(Collectors.toList());
	}

	// Approve or Reject loan (Admin)
	public LoanResponseDto updateLoanStatus(Long loanId, String status) {
		Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new RuntimeException("Loan not found"));
		loan.setStatus(LoanStatus.valueOf(status.toUpperCase()));
		loan.setUpdatedAt(LocalDateTime.now());
		loanRepository.save(loan);
		return mapToDto(loan);
	}

	// Get all loans (Admin)
	public List<LoanResponseDto> getAllLoans() {
		return loanRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
	}

	// Repay loan (Customer)
	public RepaymentResponseDto repayLoan(String email, Long loanId, BigDecimal amount) {
		Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new RuntimeException("Loan not found"));

		if (!loan.getUser().getEmail().equals(email))
			throw new RuntimeException("Unauthorized access");

		if (loan.getStatus() != LoanStatus.APPROVED)
			throw new RuntimeException("Loan is not approved yet");

		LoanRepayment repayment = new LoanRepayment(null, loan, amount, LocalDateTime.now());
		loanRepaymentRepository.save(repayment);

		return new RepaymentResponseDto(repayment.getId(), loan.getId(), repayment.getAmountPaid(),
				repayment.getPaidAt());
	}

	// Get repayment history
	public List<RepaymentResponseDto> getRepaymentHistory(String email, Long loanId) {
		Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new RuntimeException("Loan not found"));

		if (!loan.getUser().getEmail().equals(email))
			throw new RuntimeException("Unauthorized access");

		return loanRepaymentRepository.findByLoanId(loanId).stream()
				.map(r -> new RepaymentResponseDto(r.getId(), loan.getId(), r.getAmountPaid(), r.getPaidAt()))
				.collect(Collectors.toList());
	}

	// EMI Formula: EMI = P * r * (1+r)^n / ((1+r)^n - 1)
	private BigDecimal calculateEmi(BigDecimal principal, Double annualRate, Integer months) {
		double r = annualRate / (12 * 100);
		double pow = Math.pow(1 + r, months);
		double emi = principal.doubleValue() * r * pow / (pow - 1);
		return BigDecimal.valueOf(emi).setScale(2, RoundingMode.HALF_UP);
	}

	// Map to DTO
	private LoanResponseDto mapToDto(Loan loan) {
		return new LoanResponseDto(loan.getId(), loan.getUser().getName(), loan.getAmount(), loan.getInterestRate(),
				loan.getTenureMonths(), loan.getEmiAmount(), loan.getStatus().name(), loan.getAppliedAt());
	}
}