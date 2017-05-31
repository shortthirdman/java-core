package com.shortthirdman.core.challenges;

/**
 * @(#)IntHashSet.java
 *
 *
 * @author Swetank Mohanty (shortthirdman)
 * @version 1.00
 */

import java.util.HashSet;

public class IntHashSet {

	HashSet<Integer> ints;

    public IntHashSet() {
    	ints = new HashSet<Integer>();
    }

    public void loadInts(int[] arrayOfInts){
    	for(int i = 0; i < arrayOfInts.length; i++){
    		ints.add(arrayOfInts[i]);
    	}
    }

    public boolean checkDuplicate(int[] arrayOfInts){
    	if(arrayOfInts.length > ints.size()){
    		return true;
    	}else{
    		return false;
    	}
    }

    public static void main(String[] args){
    	IntHashSet test1 = new IntHashSet();
    	int[] distinctNums = {1,2,3,4,5,6};
    	test1.loadInts(distinctNums);
    	System.out.println("Test 1 should return false: " + test1.checkDuplicate(distinctNums));

    	IntHashSet test2 = new IntHashSet();
    	int[] duplicateNums = {1,1,3,4,4,6};
    	test2.loadInts(duplicateNums);
    	System.out.println("Test 2 should return true: " + test2.checkDuplicate(duplicateNums));
    }

}