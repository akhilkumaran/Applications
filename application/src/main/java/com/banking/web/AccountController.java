package com.banking.web;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.banking.dto.DepositWithdrawDTO;
import com.banking.dto.OpenAccountDTO;
import com.banking.dto.TransferDTO;
import com.banking.exceptions.InsufficientBalanceException;
import com.banking.exceptions.InvalidUserOrAccountException;
import com.banking.model.Account;
import com.banking.model.UserDetails;
import com.banking.service.BankingService;
/**
 * 
 * @author Akhil
 *
 */

@Controller
public class AccountController {             //Need to rename to a meaningfull name. Need to do validations for incoming inputs

	@RequestMapping(value="/create-account.do", method=RequestMethod.POST)
	public String openAccount(@ModelAttribute("accountOpen")OpenAccountDTO data, 
		      BindingResult result, ModelMap model) {

		UserDetails userDetails = null;
		try {  												
			userDetails = bankingService.openAccount(data);
			model.addAttribute("name", userDetails.getName());
			model.addAttribute("userId", userDetails.getUserId());
			model.addAttribute("accountNums", userDetails.getAccounts().stream()
					.map(x -> x.getAccountNumber()).collect(Collectors.toList()).toString());
		} catch (InvalidUserOrAccountException e) {
			model.addAttribute("error", e.getMessage());
			return "error";
		} 
																				//Need to display saved details
		return "account-details";
	}
	
	@RequestMapping(value="/deposit-funds.do", method=RequestMethod.POST)
	public String depositAmount(@ModelAttribute("depositAmount")DepositWithdrawDTO depositData, 
		      BindingResult result, ModelMap model) {
		
		Account account;
		try {
			account = bankingService.depositFunds(depositData);
			model.addAttribute("accountNumber", account.getAccountNumber());
			model.addAttribute("balance", account.getBalance());
		} catch (InvalidUserOrAccountException e) {
			model.addAttribute("error", e.getMessage());
			return "error";
		} 
		
		return "balance";
	}
	
	@RequestMapping(value="/withdraw-funds.do", method=RequestMethod.POST)
	public String withdrawAmount(@ModelAttribute("withdrawAmount")DepositWithdrawDTO withdrawData, 
		      BindingResult result, ModelMap model) {
		Account account;
		try {
			account = bankingService.withdrawFunds(withdrawData);
			model.addAttribute("accountNumber", account.getAccountNumber());
			model.addAttribute("balance", account.getBalance());
		} catch (InvalidUserOrAccountException | InsufficientBalanceException e) {
			model.addAttribute("error", e.getMessage());
			return "error";
		} 
		
		return "balance";
	}
	
	@RequestMapping(value="/transfer-funds.do", method=RequestMethod.POST)
	public String transferAmount(@ModelAttribute("transferAmount")TransferDTO transferData, 
		      BindingResult result, ModelMap model) {
		
		Account account;
		try {
			account = bankingService.transferFunds(transferData);
			model.addAttribute("accountNumber", account.getAccountNumber());
			model.addAttribute("balance", account.getBalance());
		} catch (InvalidUserOrAccountException | InsufficientBalanceException e) {
			model.addAttribute("error", e.getMessage());
			return "error";
		} 

		return "balance";
	}
	
	@RequestMapping(value="/get-balance.do", method=RequestMethod.POST)
	public String balanceAmount(@ModelAttribute("balanceDto")DepositWithdrawDTO dto, 
		      BindingResult result, ModelMap model) {
		
		Account account;
		try {
			account = bankingService.getBalance(dto);
			model.addAttribute("accountNumber", account.getAccountNumber());
			model.addAttribute("balance", account.getBalance());
		} catch (InvalidUserOrAccountException e) {
			model.addAttribute("error", e.getMessage());
			return "error";
		} 

		return "balance";
	}
	
	@Autowired
	BankingService bankingService;

}
