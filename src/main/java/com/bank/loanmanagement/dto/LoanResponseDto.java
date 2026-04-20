package com.bank.loanmanagement.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class LoanResponseDto {

	private Long id;
	private String ownerName;
	private BigDecimal amount;
	private Double interestRate;
	private Integer tenureMonths;
	private BigDecimal emiAmount;
	private String status;
	private LocalDateTime appliedAt;

	public LoanResponseDto() {
	}

	public LoanResponseDto(Long id, String ownerName, BigDecimal amount, Double interestRate, Integer tenureMonths,
			BigDecimal emiAmount, String status, LocalDateTime appliedAt) {
		this.id = id;
		this.ownerName = ownerName;
		this.amount = amount;
		this.interestRate = interestRate;
		this.tenureMonths = tenureMonths;
		this.emiAmount = emiAmount;
		this.status = status;
		this.appliedAt = appliedAt;
	}

	public Long getId() {
		return id;
	}

	public String getOwnerName() {
		return ownerName;
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

	public String getStatus() {
		return status;
	}

	public LocalDateTime getAppliedAt() {
		return appliedAt;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
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

	public void setStatus(String status) {
		this.status = status;
	}

	public void setAppliedAt(LocalDateTime appliedAt) {
		this.appliedAt = appliedAt;
	}
}