package com.bank.loanmanagement.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionResponseDto {

	private Long id;
	private String fromAccount;
	private String toAccount;
	private BigDecimal amount;
	private String type;
	private LocalDateTime createdAt;

	public TransactionResponseDto() {
	}

	public TransactionResponseDto(Long id, String fromAccount, String toAccount, BigDecimal amount, String type,
			LocalDateTime createdAt) {
		this.id = id;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
		this.type = type;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public String getFromAccount() {
		return fromAccount;
	}

	public String getToAccount() {
		return toAccount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public String getType() {
		return type;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}