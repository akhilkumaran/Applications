package com.banking.exceptions;
/**
 * 
 * @author Akhil
 *
 */
public class InsufficientBalanceException extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Insufficient Balance";
	}
}
