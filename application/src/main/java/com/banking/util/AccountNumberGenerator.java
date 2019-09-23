package com.banking.util;
/**
 * 
 * @author Akhil
 *
 */
public final class AccountNumberGenerator {

	private static AccountNumberGenerator instance;
	private volatile long next = 111111111111l;
	private AccountNumberGenerator() {
	}
	
	public static synchronized AccountNumberGenerator getInstance(){
        if(instance == null){
            instance = new AccountNumberGenerator();
        }
        return instance;
	}
	
	public synchronized long getNextSequence() {
        return next++;
    }
}
