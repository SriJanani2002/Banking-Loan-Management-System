package com.bank.loanmanagement.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class LoanRequestDto {

	@NotNull(message = "Amount is required")
	@DecimalMin(value = "1000.0", message = "Minimum loan amount is 1000")
	private BigDecimal amount;

	@NotNull(message = "Interest rate is required")
	@DecimalMin(value = "1.0", message = "Minimum interest rate is 1%")
	@DecimalMax(value = "30.0", message = "Maximum interest rate is 30%")
	private Double interestRate;

	@NotNull(message = "Tenure is required")
	@Min(value = 3, message = "Minimum tenure is 3 months")
	@Max(value = 360, message = "Maximum tenure is 360 months")
	private Integer tenureMonths;

	public LoanRequestDto() {
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

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	public void setTenureMonths(Integer tenureMonths) {
		this.tenureMonths = tenureMonths;
	}
}