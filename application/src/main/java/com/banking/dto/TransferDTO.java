package com.banking.dto;

import java.math.BigDecimal;
/**
 * 
 * @author Akhil
 *
 */
public class TransferDTO {

	private Long userId;
	private Long fromAccNum;
	private Long toAccNum;
	private BigDecimal amount;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Long getFromAccNum() {
		return fromAccNum;
	}
	public void setFromAccNum(Long fromAccNum) {
		this.fromAccNum = fromAccNum;
	}
	public Long getToAccNum() {
		return toAccNum;
	}
	public void setToAccNum(Long toAccNum) {
		this.toAccNum = toAccNum;
	}
	
}
