package com.banking.dto;

import java.math.BigDecimal;
/**
 * 
 * @author Akhil
 *
 */
public class DepositWithdrawDTO {            // need to rename and make it as generic DTO to use it multiple places
									
	private Long userId;
	private Long accountNumber;
	private BigDecimal amount;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
}
