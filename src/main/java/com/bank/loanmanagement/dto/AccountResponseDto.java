package com.bank.loanmanagement.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
public class AccountResponseDto {

	private Long id;
	private String accountNumber;
	private BigDecimal balance;
	private String type;
	private String status;
	private String ownerName;

	public AccountResponseDto(Long id, String accountNumber, BigDecimal balance, String type, String status,
			String ownerName) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.type = type;
		this.status = status;
		this.ownerName = ownerName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

}