package com.shortthirdman.core.challenges;

/**
 * @(#)Fibonacci.java
 *
 *
 * @author Swetank Mohanty (shortthirdman)
 * @version 1.00
 */


public class Fibonacci {

    public Fibonacci() {
    }

    public int fib(int i){
    	if(i < 0){
    		return -1;
    	}else if(i == 0){
    		return 0;
    	}else if(i == 1){
    		return 1;
    	}else{
    		return fib(i-1)+fib(i-2);
    	}
    }

    public int fibIter(int i){
    	if(i < 0){
    		return -1;
    	}else if(i == 0){
    		return 0;
    	}else if(i == 1){
    		return 1;
    	}else{
    		int x = 1;
    		int y = 1;
    		for(int z = 2; z < i; z++){
    			int temp = y;
    			y = x + y;
    			x = temp;
    		}
    		return y;
    	}
    }

    public static void main(String[] args){
    	Fibonacci test = new Fibonacci();
    	System.out.println("Recursive Method");
    	System.out.println("Fib0 = " + test.fib(0));
    	System.out.println("Fib1 = " + test.fib(1));
    	System.out.println("Fib2 = " + test.fib(2));
    	System.out.println("Fib3 = " + test.fib(3));
    	System.out.println("Fib4 = " + test.fib(4));
    	System.out.println("Fib5 = " + test.fib(5));
    	System.out.println("Fib6 = " + test.fib(6));
    	System.out.println("Fib7 = " + test.fib(7));
    	System.out.println("Iterative Method");
    	System.out.println("Fib0 = " + test.fibIter(0));
    	System.out.println("Fib1 = " + test.fibIter(1));
    	System.out.println("Fib2 = " + test.fibIter(2));
    	System.out.println("Fib3 = " + test.fibIter(3));
    	System.out.println("Fib4 = " + test.fibIter(4));
    	System.out.println("Fib5 = " + test.fibIter(5));
    	System.out.println("Fib6 = " + test.fibIter(6));
    	System.out.println("Fib7 = " + test.fibIter(7));

    }


}