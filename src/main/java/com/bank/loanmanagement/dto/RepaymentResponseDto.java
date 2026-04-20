package com.bank.loanmanagement.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RepaymentResponseDto {

	private Long id;
	private Long loanId;
	private BigDecimal amountPaid;
	private LocalDateTime paidAt;

	public RepaymentResponseDto() {
	}

	public RepaymentResponseDto(Long id, Long loanId, BigDecimal amountPaid, LocalDateTime paidAt) {
		this.id = id;
		this.loanId = loanId;
		this.amountPaid = amountPaid;
		this.paidAt = paidAt;
	}

	public Long getId() {
		return id;
	}

	public Long getLoanId() {
		return loanId;
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

	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}

	public void setAmountPaid(BigDecimal amountPaid) {
		this.amountPaid = amountPaid;
	}

	public void setPaidAt(LocalDateTime paidAt) {
		this.paidAt = paidAt;
	}
}