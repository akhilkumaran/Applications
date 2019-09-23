package com.banking.model;
/**
 * 
 * @author Akhil
 *
 */
public enum AccountType {

	SAVING(1,"Savings"),
	CURRENT(2,"Current"),
	CREDIT(3, "Credit"),
	LOAN(4,"Loan");
	
	private int code;
	private String type;
	private AccountType(int code, String type) {
		this.code = code;
		this.type = type;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
