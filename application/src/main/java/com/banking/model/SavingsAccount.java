package com.banking.model;

import java.math.BigDecimal;
/**
 * 
 * @author Akhil
 *
 */
public class SavingsAccount extends CashAccount {

	@Override
	protected AccountType getAccountType() {
		return AccountType.SAVING;
	}

	@Override
	public boolean addAmount(BigDecimal amount) {
		setBalance(getBalance().add(amount));
		return true;
	}

	@Override
	public boolean deductAmount(BigDecimal amount) {
		setBalance(getBalance().subtract(amount));
		return true;
	}

}
