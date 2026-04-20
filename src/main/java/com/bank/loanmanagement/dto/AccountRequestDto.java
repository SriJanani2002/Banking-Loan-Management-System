package com.bank.loanmanagement.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Data
public class AccountRequestDto {

	@NotBlank(message = "Account type is required")
	@Pattern(regexp = "SAVINGS|CURRENT", message = "Type must be SAVINGS or CURRENT")
	private String type; // SAVINGS or CURRENT

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
