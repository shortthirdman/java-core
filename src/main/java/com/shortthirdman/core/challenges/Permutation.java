package com.shortthirdman.core.challenges;

/**
 * @Permutation.java
 * @version 1.00 2012/12/23
Description:

Prints out all permutation of a word.
 */

import java.util.ArrayList;

public class Permutation {

    public Permutation() {
    }

	//method 1
    public String[] generatePermutations(String word){

		int numPerm = 1;
		String[] currentPerm = new String[1];
		currentPerm[0] = word.substring(0,1);

    	//generate permutations
    	for(int i = 1; i < word.length(); i++){
    		char currentChar = word.charAt(i);

    		//String[] newPerm = new String[numPerm];
    		ArrayList<String> newPerm = new ArrayList<String>();

    		for(int j = 0; j < currentPerm.length; j++){
    			ArrayList<String> tempWord = new ArrayList<String>();

    			//convert sub-permutation into arraylist for new char insertion
    			for(int x = 0; x < currentPerm[j].length(); x++){
    				tempWord.add(Character.toString(currentPerm[j].charAt(x)));
    			}

    			for(int y = 0; y < i + 1; y++){
    				tempWord.add(y, Character.toString(currentChar));
    				String newWord = ArrayListToString(tempWord);
    				newPerm.add(newWord);
    				tempWord.remove(y);
    			}
    		}

    		numPerm = numPerm*(i+1);
    		currentPerm = new String[numPerm];
    		for(int k = 0; k < currentPerm.length; k++){
    			currentPerm[k] = newPerm.get(k);
    		}
    	}
		return currentPerm;
    }

    public String ArrayListToString(ArrayList<String> subword){
    	StringBuffer result = new StringBuffer();
    	for(String s : subword){
    		result.append(s);
    	}
    	return result.toString();
    }

    //method 2: recurrsion
    public ArrayList<String> createPermutations(String word){
    	if(word.length() == 1){
    		ArrayList<String> first = new ArrayList<String>();
    		first.add(word);
    		return first;
    	}
    	ArrayList<String> prevPerm = createPermutations(word.substring(0, word.length()-1));
    	ArrayList<String> results = new ArrayList<String>();
    	String lastChar = Character.toString(word.charAt(word.length()-1));
    	for(int i = 0; i < prevPerm.size(); i++){
    		mergePermutations(results, addCharacter(lastChar, prevPerm.get(i)));
    	}
    	return results;

    }

    public ArrayList<String> addCharacter(String c, String word){
    	ArrayList<String> perm = new ArrayList<String>();
    	ArrayList<String> tempWord = new ArrayList<String>();
    	for(int x = 0; x < word.length(); x++){
			tempWord.add(Character.toString(word.charAt(x)));
		}
    	for(int y = 0; y < word.length() + 1; y++){
			tempWord.add(y, c);
			String newWord = ArrayListToString(tempWord);
			perm.add(newWord);
			tempWord.remove(y);
		}
    	return perm;
    }

    public void mergePermutations(ArrayList<String> perm1, ArrayList<String> perm2){
    	perm1.addAll(perm2);
    }

	//prints all permutations for testing
    public void printPermutations(String[] permutations){
    	for(String s : permutations){
    		System.out.println(s);
    	}
    }

    public void printPermutations(ArrayList<String> permutations){
    	for(String s : permutations){
    		System.out.println(s);
    	}
    }

    public static void main(String[] args){
    	Permutation test = new Permutation();
    	System.out.println("\nMethod 1");
    	System.out.println("Testing size 1");
    	String[] a = test.generatePermutations("a");
    	test.printPermutations(a);
    	System.out.println("Testing size 2");
    	String[] bc = test.generatePermutations("bc");
    	test.printPermutations(bc);
    	System.out.println("Testing size 3");
    	String[] def = test.generatePermutations("def");
    	test.printPermutations(def);

    	System.out.println("\nMethod 2");
    	System.out.println("Testing size 1");
    	ArrayList<String> a2 = test.createPermutations("a");
    	test.printPermutations(a2);
    	System.out.println("Testing size 2");
    	ArrayList<String> bc2 = test.createPermutations("bc");
    	test.printPermutations(bc2);
    	System.out.println("Testing size 3");
    	ArrayList<String> def2 = test.createPermutations("def");
    	test.printPermutations(def2);

    	/*
    	System.out.println("Testing size 4");
    	String[] signs = test.generatePermutations("+-*%");
    	test.printPermutations(signs);
    	System.out.println("Testing size 5");
    	String[] gavin = test.generatePermutations("gavin");
    	test.printPermutations(gavin);
    	*/

    }


}