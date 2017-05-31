package com.shortthirdman.core.challenges;

/**
 * @(#)Factorial.java
 *
 *
 * @author Swetank Mohanty (shortthirdman)
 * @version 1.00
 */


public class Factorial {

    public Factorial() {
    }

    public int fact(int i){
    	int result = 1;
    	for(int x = i; x > 0; x--){
    		result = result*x;
    	}
    	return result;
    }

    public static void main(String[] args){
    	Factorial test = new Factorial();
    	int fact1 = test.fact(1);
    	System.out.println("1 Factorial = " + fact1);
    	int fact2 = test.fact(2);
    	System.out.println("2 Factorial = " + fact2);
    	int fact3 = test.fact(3);
    	System.out.println("3 Factorial = " + fact3);
    	int fact4 = test.fact(4);
    	System.out.println("4 Factorial = " + fact4);
    }


}