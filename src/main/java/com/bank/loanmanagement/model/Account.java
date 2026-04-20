package com.bank.loanmanagement.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

import com.bank.loanmanagement.enums.AccountStatus;
import com.bank.loanmanagement.enums.AccountType;

@Entity
@Table(name = "accounts")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(unique = true, nullable = false)
	private String accountNumber;

	@Column(nullable = false)
	private BigDecimal balance;

	@Enumerated(EnumType.STRING)
	private AccountType type; // SAVINGS, CURRENT

	@Enumerated(EnumType.STRING)
	private AccountStatus status; // ACTIVE, INACTIVE

	public Account() {
	}

	public Account(Long id, User user, String accountNumber, BigDecimal balance, AccountType type,
			AccountStatus status) {
		this.id = id;
		this.user = user;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.type = type;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public AccountType getType() {
		return type;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}
}
