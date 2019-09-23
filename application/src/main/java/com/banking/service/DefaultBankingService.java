package com.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.dto.DepositWithdrawDTO;
import com.banking.dto.OpenAccountDTO;
import com.banking.dto.TransferDTO;
import com.banking.exceptions.InsufficientBalanceException;
import com.banking.exceptions.InvalidUserOrAccountException;
import com.banking.model.Account;
import com.banking.model.SavingsAccount;
import com.banking.model.UserDetails;
import com.banking.repository.UserRepository;
/**
 * 
 * @author AKki
 *
 */
@Service
public class DefaultBankingService implements BankingService {

	/**
	 * Creates new user and account if the user ID given is null or will create 
	 * new account and maps to the user
	 */
	@Override
	public UserDetails openAccount(OpenAccountDTO newAccountDTO) 
			throws InvalidUserOrAccountException {
		
		UserDetails userDetails = getUserDetailsFromDTO(newAccountDTO);
		
		if(userDetails.getUserId() == null) {
			
			userDetails = userRepository.addUser(userDetails);
			
		} else {
			
			userDetails = userRepository.getUserDetails(userDetails.getUserId());
			
			if(userDetails == null) {
				throw new InvalidUserOrAccountException();
			}
		}
		
		Account newAccount = accountFactory(newAccountDTO.getAccountType());
		return userRepository.openAccount(userDetails.getUserId(), newAccount);
	}

	
	/**
	 * Deposits amount to user's account by using user ID and account number
	 */
	@Override
	public Account depositFunds(DepositWithdrawDTO depositData) throws InvalidUserOrAccountException {
		
		return userRepository.depositToOwnAccount(depositData.getUserId(), 
				depositData.getAccountNumber(), depositData.getAmount());
	}

	/**
	 * Subtracts balance from account by checking userId and Account number
	 */
	@Override
	public Account withdrawFunds(DepositWithdrawDTO withdrawData) throws InsufficientBalanceException, 
		InvalidUserOrAccountException {
		
		return userRepository.withDrawAmount(withdrawData.getUserId(), 
				withdrawData.getAccountNumber(), withdrawData.getAmount());
	}

	/**
	 * Transfer the fund from one account to another account
	 */
	@Override
	public Account transferFunds(TransferDTO transferData) throws InsufficientBalanceException, 
		InvalidUserOrAccountException {
		
		return userRepository.transferAmount(transferData.getUserId(), transferData.getFromAccNum(),
				transferData.getToAccNum(), transferData.getAmount());
	}
	
	/**
	 * Check the balance from the account by user ID and Account Number
	 */
	@Override
	public Account getBalance(DepositWithdrawDTO dto) throws InvalidUserOrAccountException  {
		
		return userRepository.getAccount(dto.getUserId(), dto.getAccountNumber());
	}
	
	//Can be moved out of business logic as an individual factory class
	private Account accountFactory(Integer accountType) 
			throws InvalidUserOrAccountException {
		
		switch (accountType) {
		case 1:
			return new SavingsAccount();
			
		case 2:
			return new SavingsAccount();						//Will be current account and so on

		default:
			throw new InvalidUserOrAccountException();			//Need to change this with meaningfull exception
		}
	}
	
	private UserDetails getUserDetailsFromDTO(OpenAccountDTO newAccountDTO) {
		UserDetails userDetails = new UserDetails();
		userDetails.setUserId(newAccountDTO.getUserId());
		userDetails.setName(newAccountDTO.getName());
		userDetails.setEmail(newAccountDTO.getEmail());
		userDetails.setMobile(newAccountDTO.getMobile());
		return userDetails;
	}

	@Autowired
	UserRepository userRepository;

}
