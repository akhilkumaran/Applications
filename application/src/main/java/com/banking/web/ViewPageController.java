package com.banking.web;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.banking.dto.DepositWithdrawDTO;
import com.banking.dto.OpenAccountDTO;
import com.banking.dto.TransferDTO;
import com.banking.model.AccountType;
/**
 * 
 * @author Akhil
 *
 */

@Controller
public class ViewPageController {

	@RequestMapping(value="/banking-home.html")
	public String viewHome() {
		return "home";
	}
	
	@RequestMapping(value="/open-account.html")
	public ModelAndView openAccount(ModelMap model) {
		Map<Integer, String> accountTypes = new LinkedHashMap<>();
		accountTypes.put(AccountType.SAVING.getCode(), AccountType.SAVING.getType());
		accountTypes.put(AccountType.CURRENT.getCode(), AccountType.CURRENT.getType());
		accountTypes.put(AccountType.CREDIT.getCode(), AccountType.CREDIT.getType());
		accountTypes.put(AccountType.LOAN.getCode(), AccountType.LOAN.getType());
		ModelAndView modelView = new ModelAndView("open-account", "accountOpen", new OpenAccountDTO());
		modelView.addObject("accountTypes", accountTypes);
		return modelView;
	}
	
	@RequestMapping(value="/deposit-amount.html")
	public ModelAndView depositAmount() {
		ModelAndView modelView = new ModelAndView("deposit-amount", "depositAmount", new DepositWithdrawDTO());
		return modelView;
	}
	
	@RequestMapping(value="/withdraw-amount.html")
	public ModelAndView withdrawAmount() {
		ModelAndView modelView = new ModelAndView("withdraw-amount", "withdrawAmount", new DepositWithdrawDTO());
		return modelView;
	}
	
	@RequestMapping(value="/transfer-amount.html")
	public ModelAndView transferAmount() {
		ModelAndView modelView = new ModelAndView("transfer-amount", "transferAmount", new TransferDTO());
		return modelView;
	}
	
	@RequestMapping(value="/view-balance.html")
	public ModelAndView balaceView() {
		ModelAndView modelView = new ModelAndView("balance-form", "balanceDto", new DepositWithdrawDTO());
		return modelView;
	}
}
