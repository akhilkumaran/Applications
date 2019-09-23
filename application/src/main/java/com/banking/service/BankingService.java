package com.banking.service;

import com.banking.dto.DepositWithdrawDTO;
import com.banking.dto.OpenAccountDTO;
import com.banking.dto.TransferDTO;
import com.banking.exceptions.InsufficientBalanceException;
import com.banking.exceptions.InvalidUserOrAccountException;
import com.banking.model.Account;
import com.banking.model.UserDetails;
/**
 * 
 * @author Akhil
 *
 */
public interface BankingService {

	UserDetails openAccount(OpenAccountDTO newAccountDTO) 
			throws InvalidUserOrAccountException;
	
	Account depositFunds(DepositWithdrawDTO depositData) throws InvalidUserOrAccountException;
	
	Account withdrawFunds(DepositWithdrawDTO withdrawData) throws InsufficientBalanceException, 
		InvalidUserOrAccountException;
	
	Account transferFunds(TransferDTO withdrawData) throws InsufficientBalanceException, 
		InvalidUserOrAccountException;

	Account getBalance(DepositWithdrawDTO dto) throws InvalidUserOrAccountException;
}
