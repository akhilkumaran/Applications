package com.banking.model;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * 
 * @author Akhil
 *
 */
public abstract class Account {

	private Long accountNumber;

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	protected abstract AccountType getAccountType();
	
	public abstract boolean addAmount(BigDecimal amount);
	
	public abstract BigDecimal getBalance();
	
	public abstract boolean deductAmount(BigDecimal amount);
	
	@Override
	public int hashCode() {
		HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
		hashCodeBuilder.append(accountNumber);
		return hashCodeBuilder.build();
	}
}
