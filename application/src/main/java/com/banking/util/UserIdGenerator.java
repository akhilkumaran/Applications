package com.banking.util;
/**
 * 
 * @author Akhil
 *
 */
public class UserIdGenerator {
	private static UserIdGenerator instance;
	private volatile long next = 111111111111l;
	private UserIdGenerator() {
	}
	
	public static synchronized UserIdGenerator getInstance(){
        if(instance == null){
            instance = new UserIdGenerator();
        }
        return instance;
	}
	
	public synchronized long getNextSequence() {
        return next++;
    }
}
