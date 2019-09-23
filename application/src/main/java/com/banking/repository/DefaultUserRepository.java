package com.banking.repository;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.banking.exceptions.InsufficientBalanceException;
import com.banking.exceptions.InvalidUserOrAccountException;
import com.banking.model.Account;
import com.banking.model.UserDetails;
import com.banking.util.AccountNumberGenerator;
import com.banking.util.UserIdGenerator;
/**
 * 
 * @author Akhil
 *
 */

@Service
public class DefaultUserRepository implements UserRepository {

	private static final Map<Long, UserDetails> USERID_USER_MAP = new ConcurrentHashMap<>();
	private static final Map<Long, Long> ACC_NUM_USERID_MAP = new ConcurrentHashMap<>();
	
	/**
	 * Adds new account to the existing user
	 */
	@Override
	public UserDetails openAccount(Long userID, Account newAccount) {
		UserDetails user = USERID_USER_MAP.get(userID);
		newAccount.setAccountNumber(AccountNumberGenerator.getInstance().getNextSequence());
		user.addAccount(newAccount);
		ACC_NUM_USERID_MAP.put(newAccount.getAccountNumber(), userID);
		return user;
	}
	
	/**
	 * Add a new amount to user's own account by providing user ID and Account number
	 */
	@Override
	public Account depositToOwnAccount(Long userID, Long accNumber, BigDecimal amount) 
			throws InvalidUserOrAccountException {
		
		UserDetails userDetails = getUserDetails(userID);
		
		Account account = getAccount(userID, accNumber);
		
		account.addAmount(amount);
		
		userDetails.getAccounts().remove(account);
		userDetails.getAccounts().add(account);
		
		return account;
	}
	
	/**
	 * Add new amount will be added to the account by checking account number only
	 */
	@Override
	public Account depositToAccount(Long accNumber, BigDecimal amount) 
			throws InvalidUserOrAccountException {
		
		Long userID = ACC_NUM_USERID_MAP.get(accNumber);
		
		return depositToOwnAccount(userID, accNumber, amount);
		
	}
	
	/**
	 * Deducting amount from the account by checking user ID and account number
	 */
	@Override
	public Account withDrawAmount(Long userID, Long accNumber, BigDecimal amount) 
			throws InsufficientBalanceException, InvalidUserOrAccountException {
		
		UserDetails userDetails = getUserDetails(userID);
		
		Account account = getAccount(userID, accNumber);
		
		if(account.getBalance().compareTo(amount) < 0) {
			throw new InsufficientBalanceException();
		}
			
		account.deductAmount(amount);
		
		userDetails.getAccounts().remove(account);
		userDetails.getAccounts().add(account);
		
		return account;
	}
	
	/**
	 * Transfers amount from the account by checking user ID and account number to the account by checking to account number
	 */
	@Override
	public Account transferAmount(Long userId, Long fromAccNum, Long toAccNum, BigDecimal amountToBeTransfered) 
			throws InsufficientBalanceException, InvalidUserOrAccountException {
		
		Account fromAccount = withDrawAmount(userId, fromAccNum, amountToBeTransfered);
		depositToAccount(toAccNum, amountToBeTransfered);    // As of now, deposit can be done in to same bank accounts
		
		return fromAccount;
	}
	
	/**
	 * Check the user exists in the database
	 */
	@Override
	public boolean isUserExists(UserDetails userDetails) {
		
		return USERID_USER_MAP.containsKey(userDetails.getUserId());
	}
	
	/**
	 * Get the account details by userId and account number
	 */
	@Override
	public Account getAccount(Long userId, Long accNumber) throws InvalidUserOrAccountException {
		
		UserDetails userDetails = getUserDetails(userId);
		
		Account account = userDetails.getAccounts().stream().filter(x -> x.getAccountNumber().equals(accNumber))
				.findFirst().orElseThrow(InvalidUserOrAccountException::new);
		
		return account;
	}
	
	/**
	 * Add a new user to the system
	 */
	@Override
	public UserDetails addUser(UserDetails userDetails) {
		
		userDetails.setUserId(UserIdGenerator.getInstance().getNextSequence());
		USERID_USER_MAP.put(userDetails.getUserId(), userDetails);
		
		return userDetails;
	}
	
	/**
	 * Get the user details by userID
	 */
	@Override
	public UserDetails getUserDetails(Long userID) throws InvalidUserOrAccountException {
		UserDetails userDetails = USERID_USER_MAP.get(userID);
		if(userDetails == null)
			throw new InvalidUserOrAccountException();
		
		return userDetails;
	}
}
