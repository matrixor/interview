package com.alan.interview.sunlife;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement singlton by use enum INSTANCE 
 * to prevent problem of new instances are created when deserializing class in big data environment. 
 */
public enum PrimeNumberGenerater {
	INSTANCE;

    private boolean IsPrime(long number, List<Long> preivousPrimeNumberList){
    	
    	if (number == 1){
    		return false;
    	}
    	
    	//To determine whether a number is a prime number, just determine whether the number can be divisible by a prime number smaller than the square root of the number.
        long maxProbPrimeNumber = (long)Math.sqrt(number); 
        
        for(Long primeNumber : preivousPrimeNumberList){
        	if (number % primeNumber == 0) {
        		return false;
        	}
        		
        	if (primeNumber > maxProbPrimeNumber) {
        		break;
        	}       	
        }

        return true;
    }
    
    // ignore handle input check,  count > 0
    public long GenerateNPrime(int count){ 
    	
    	List<Long> list = new ArrayList<Long>();
        
        long startNumber = 1;
        
        while (list.size() < count){
            if (IsPrime(startNumber, list)) {
            	list.add(startNumber);
            }
            startNumber++;
        }
        
        return --startNumber;
    }


}
