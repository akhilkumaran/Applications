package com.banking.repository;

import java.math.BigDecimal;

import com.banking.exceptions.InsufficientBalanceException;
import com.banking.exceptions.InvalidUserOrAccountException;
import com.banking.model.Account;
import com.banking.model.UserDetails;
/**
 * 
 * @author Akhil
 *
 */
public interface UserRepository {

	UserDetails openAccount(Long userID, Account newAccount);

	Account depositToOwnAccount(Long userID, Long accNumber, BigDecimal amount) 
			throws InvalidUserOrAccountException;

	Account depositToAccount(Long accNumber, BigDecimal amount) 
			throws InvalidUserOrAccountException;

	Account withDrawAmount(Long userID, Long accNumber, BigDecimal amount)
			throws InsufficientBalanceException, InvalidUserOrAccountException;

	Account transferAmount(Long userId, Long fromAccNum, Long toAccNum, BigDecimal amountToBeTransfered)
			throws InsufficientBalanceException, InvalidUserOrAccountException;

	boolean isUserExists(UserDetails userDetails);

	UserDetails addUser(UserDetails userDetails);

	UserDetails getUserDetails(Long userId) throws InvalidUserOrAccountException;

	Account getAccount(Long userId, Long accNumber) throws InvalidUserOrAccountException;

}
