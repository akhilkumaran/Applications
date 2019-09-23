package com.banking.model;

import java.util.HashSet;
import java.util.Set;
/**
 * 
 * @author Akhil
 *
 */
public class UserDetails {

	private Long userId;
	
	private String name;
	private String email;
	private Long mobile;
	
	private Set<Account> accounts = new HashSet<>();
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getMobile() {
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	public Set<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}
	public void addAccount(Account account) {
		accounts.add(account);
	}
}
