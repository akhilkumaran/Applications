package com.banking.model;

import java.math.BigDecimal;
/**
 * 
 * @author Akhil
 *
 */
public abstract class CashAccount extends Account {

	private BigDecimal balance = new BigDecimal(0);

	@Override
	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
}
