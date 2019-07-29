package com.alan.interview.sunlife.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.alan.interview.sunlife.PrimeNumberGenerater;

/**
 * Test method for {@link com.alan.interview.sunlife.PrimeNumberGenerater#GenerateNPrime()}.
 */
public class PrimeNumberGeneraterTest {
	
    @Test
    public void test_n_0() {
    	int n = 0;   	
    	long prime = PrimeNumberGenerater.INSTANCE.GenerateNPrime(n);
        assertEquals(0, prime);
    }
    
    @Test
    public void test_n_1() {
    	int n = 1;
    	long prime = PrimeNumberGenerater.INSTANCE.GenerateNPrime(n);
        assertEquals(2, prime);

    }
    
    @Test
    public void test_n_2() {
    	int n = 2;  	
    	long prime = PrimeNumberGenerater.INSTANCE.GenerateNPrime(n);
        assertEquals(3, prime);
    }
    
    @Test
    public void test_n_10() {
    	int n = 10;  	
    	long prime = PrimeNumberGenerater.INSTANCE.GenerateNPrime(n);
        assertEquals(29, prime);
    }
    
    @Test
    public void test_n_100() {
    	int n = 100;  	
    	long prime = PrimeNumberGenerater.INSTANCE.GenerateNPrime(n);
        assertEquals(541, prime);
    }
    
    /*
    @Test
    public void test_n_1000() {
    	int n = 1000;  	
    	long prime = PrimeNumberGenerater.INSTANCE.GenerateNPrime(n);
        assertEquals(7919, prime);
    }
    
    @Test
    public void test_n_10000() {
    	int n = 10000;  	
    	long prime = PrimeNumberGenerater.INSTANCE.GenerateNPrime(n);
        assertEquals(104729, prime);
    }
    
    @Test
    public void test_n_100000() {
    	int n = 100000;  	
    	long prime = PrimeNumberGenerater.INSTANCE.GenerateNPrime(n);
        assertEquals(1299709, prime);
    }
    
    @Test
    public void test_n_1000000() {
    	int n = 1000000;  	
    	long prime = PrimeNumberGenerater.INSTANCE.GenerateNPrime(n);
        assertEquals(15485863, prime);
    }
    */
    /*
    @Test
    public void test_n_10000000() {
    	int n = 10000000;  	
    	long prime = PrimeNumberGenerater.INSTANCE.GenerateNPrime(n);
        assertEquals(179424673, prime);
    }
    */
    
    /*   
    @Test
    public void test_n_max() {
    	int n = Integer.MAX_VALUE;  //2147483647	
    	long prime = PrimeNumberGenerater.INSTANCE.GenerateNPrime(n);
    	System.out.println(prime);
        //assertEquals(?, prime);

    }
    */
    
    //singleton test
	@Test
	public void testSingelton(){		
		PrimeNumberGenerater obj1 = PrimeNumberGenerater.INSTANCE;
		PrimeNumberGenerater obj2 = PrimeNumberGenerater.INSTANCE;
		assertEquals(true, obj1.hashCode() == obj2.hashCode()); 
	}
	
    //singleton test - refactor
	@Test(expected = NoSuchMethodException.class)
	public void testTest2() throws Exception {
		
		Class<?> classPrimeNumberGenerater = Class.forName("com.alan.interview.sunlife.PrimeNumberGenerater"); 
		Constructor<?> constructor = classPrimeNumberGenerater.getDeclaredConstructor(null);
		constructor.setAccessible(true);
		PrimeNumberGenerater obj1 = (PrimeNumberGenerater) constructor.newInstance();
		PrimeNumberGenerater obj2 = (PrimeNumberGenerater) constructor.newInstance();
		assertEquals(true, obj1.hashCode() == obj2.hashCode());
	}

	//singleton test - mutithreading
	@Test
	public void testMutiThreadSingelton() throws InterruptedException {
	    int threadTotal = 10;
	    Set<Integer> hashcodes = new HashSet<>();

        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < threadTotal; i++) {
            executorService.execute(() -> {
                hashcodes.add(PrimeNumberGenerater.INSTANCE.hashCode());
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        assertEquals(true, 1 == hashcodes.size()); 
    }
	
	//singleton test - deserializing
	@Test
	public void testDeserializing() throws IOException, ClassNotFoundException {
		PrimeNumberGenerater obj1 = PrimeNumberGenerater.INSTANCE;

	    @SuppressWarnings("resource")
		ObjectOutputStream oosObj = new ObjectOutputStream(new FileOutputStream("EnumInstance"));
	    oosObj.writeObject(obj1);

	    @SuppressWarnings("resource")
	    ObjectInputStream oisObj = new ObjectInputStream(new FileInputStream("EnumInstance"));
	    PrimeNumberGenerater obj2 = (PrimeNumberGenerater) oisObj.readObject();

	    assertEquals(true, obj1.hashCode() == obj2.hashCode()); 
	}
}
