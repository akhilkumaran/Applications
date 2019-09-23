package com.banking.exceptions;
/**
 * 
 * @author Akhil
 *
 */
public class InvalidUserOrAccountException extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Unknown user or invalid account";
	}
}
