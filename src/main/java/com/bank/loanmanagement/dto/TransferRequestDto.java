package com.bank.loanmanagement.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class TransferRequestDto {

	@NotBlank(message = "Receiver account number is required")
	private String toAccountNumber;

	@NotNull(message = "Amount is required")
	@DecimalMin(value = "1.0", message = "Minimum transfer amount is 1")
	private BigDecimal amount;

	public TransferRequestDto() {
	}

	public String getToAccountNumber() {
		return toAccountNumber;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setToAccountNumber(String toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}