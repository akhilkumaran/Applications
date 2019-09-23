package com.banking.service;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.stereotype.Component;

import com.banking.dto.OpenAccountDTO;
import com.banking.exceptions.InvalidUserOrAccountException;
import com.banking.model.UserDetails;
import com.banking.repository.DefaultUserRepository;
import com.banking.repository.UserRepository;
/**
 * 
 * @author Akhil
 *
 */
@Component
public class DefaultBankingServiceTest {

	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	/*
	 * 3 scenario test cases for open account. Need to do more test cases and for other APIs
	 */
	@Test
	public void openAccount_userDetails_createNewUserAndAccount() {
		OpenAccountDTO newAccountDto = Mockito.mock(OpenAccountDTO.class);
		Mockito.when(newAccountDto.getAccountNumber()).thenReturn(null);
		Mockito.when(newAccountDto.getUserId()).thenReturn(null);
		Mockito.when(newAccountDto.getName()).thenReturn("Akhil");
		Mockito.when(newAccountDto.getEmail()).thenReturn("akhilkumaran@gmail.com");
		Mockito.when(newAccountDto.getMobile()).thenReturn(8907670610L);
		Mockito.when(newAccountDto.getAccountType()).thenReturn(1);
		UserDetails userDetails = null;
		try {
			userDetails = bankingService.openAccount(newAccountDto);
		} catch (InvalidUserOrAccountException e) {
			fail("Recieved wrong UserId for creating secondary account");
		}
		
		Assert.assertTrue("Un expected result", userDetails.getUserId() == 111111111111L);
	}
	
	@Test
	public void openAccount_UserDetails_createAccountForExistingUserId() {
		OpenAccountDTO newAccountDto = Mockito.mock(OpenAccountDTO.class);
		Mockito.when(newAccountDto.getAccountNumber()).thenReturn(null);
		Mockito.when(newAccountDto.getUserId()).thenReturn(111111111111L);
		Mockito.when(newAccountDto.getName()).thenReturn("Akhil");
		Mockito.when(newAccountDto.getEmail()).thenReturn("akhilkumaran@gmail.com");
		Mockito.when(newAccountDto.getMobile()).thenReturn(8907670610L);
		Mockito.when(newAccountDto.getAccountType()).thenReturn(1);
		UserDetails userDetails = null;
		try {
			userDetails = bankingService.openAccount(newAccountDto);
		} catch (InvalidUserOrAccountException e) {
			fail("Recieved wrong UserId for creating secondary account");
		}
		Assert.assertTrue("Un expected result", userDetails.getAccounts().size() > 1);
	}
	
	@Test(expected = InvalidUserOrAccountException.class)
	public void openAccount_InvalidUserOrAccountException_createAccountForWrongUserId() throws InvalidUserOrAccountException {
		OpenAccountDTO newAccountDto = Mockito.mock(OpenAccountDTO.class);
		Mockito.when(newAccountDto.getAccountNumber()).thenReturn(null);
		Mockito.when(newAccountDto.getUserId()).thenReturn(111111L);
		Mockito.when(newAccountDto.getName()).thenReturn("Akhil");
		Mockito.when(newAccountDto.getEmail()).thenReturn("akhilkumaran@gmail.com");
		Mockito.when(newAccountDto.getMobile()).thenReturn(8907670610L);
		Mockito.when(newAccountDto.getAccountType()).thenReturn(1);
		bankingService.openAccount(newAccountDto);
		fail("Unexpected result");
	}

	@Spy
    private UserRepository userRepository = new DefaultUserRepository();
	
	@InjectMocks
	DefaultBankingService bankingService;
}
