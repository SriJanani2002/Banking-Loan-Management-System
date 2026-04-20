package com.bank.loanmanagement.model;

import com.bank.loanmanagement.enums.LoanStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "loans")
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(nullable = false)
	private BigDecimal amount;

	@Column(nullable = false)
	private Double interestRate;

	@Column(nullable = false)
	private Integer tenureMonths;

	private BigDecimal emiAmount;

	@Enumerated(EnumType.STRING)
	private LoanStatus status; // PENDING, APPROVED, REJECTED

	private LocalDateTime appliedAt;
	private LocalDateTime updatedAt;

	public Loan() {
	}

	public Loan(Long id, User user, BigDecimal amount, Double interestRate, Integer tenureMonths, BigDecimal emiAmount,
			LoanStatus status, LocalDateTime appliedAt, LocalDateTime updatedAt) {
		this.id = id;
		this.user = user;
		this.amount = amount;
		this.interestRate = interestRate;
		this.tenureMonths = tenureMonths;
		this.emiAmount = emiAmount;
		this.status = status;
		this.appliedAt = appliedAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public Double getInterestRate() {
		return interestRate;
	}

	public Integer getTenureMonths() {
		return tenureMonths;
	}

	public BigDecimal getEmiAmount() {
		return emiAmount;
	}

	public LoanStatus getStatus() {
		return status;
	}

	public LocalDateTime getAppliedAt() {
		return appliedAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	public void setTenureMonths(Integer tenureMonths) {
		this.tenureMonths = tenureMonths;
	}

	public void setEmiAmount(BigDecimal emiAmount) {
		this.emiAmount = emiAmount;
	}

	public void setStatus(LoanStatus status) {
		this.status = status;
	}

	public void setAppliedAt(LocalDateTime appliedAt) {
		this.appliedAt = appliedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}