package com.bank.loanmanagement.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "loan_repayments")
public class LoanRepayment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loan_id", nullable = false)
	private Loan loan;

	@Column(nullable = false)
	private BigDecimal amountPaid;

	private LocalDateTime paidAt;

	// No-arg constructor
	public LoanRepayment() {
	}

	// All-arg constructor
	public LoanRepayment(Long id, Loan loan, BigDecimal amountPaid, LocalDateTime paidAt) {
		this.id = id;
		this.loan = loan;
		this.amountPaid = amountPaid;
		this.paidAt = paidAt;
	}

	public Long getId() {
		return id;
	}

	public Loan getLoan() {
		return loan;
	}

	public BigDecimal getAmountPaid() {
		return amountPaid;
	}

	public LocalDateTime getPaidAt() {
		return paidAt;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	public void setAmountPaid(BigDecimal amountPaid) {
		this.amountPaid = amountPaid;
	}

	public void setPaidAt(LocalDateTime paidAt) {
		this.paidAt = paidAt;
	}
}