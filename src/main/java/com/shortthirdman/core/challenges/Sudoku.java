package com.shortthirdman.core.challenges;

/**
 * @(#)Sudoku.java
 * @version 1.00 2013/1/26
Description:

Sudoku is a number-based logic puzzle. It typically comprises
of a 9*9 grid with digits so that each column, each row and
each of the nine 3*3 sub-grids that compose the grid contains
all the digits from 1 to 9. For this challenge, you will be
given an N*N grid populated with numbers from 1 through N and
you have to determine if it is a valid sudoku solution.
You may assume that N will be either 4 or 9. The grid can be
divided into square regions of equal size, where the size of
a region is equal to the square root of a side of the entire
grid. Thus for a 9*9 grid there would be 9 regions of size 3*3 each.

Input sample:

Your program should accept as its first argument a path to a
filename. Each line in this file contains the value of N, a
semicolon and the sqaure matrix of integers in row major form,
comma delimited. e.g.
4;1,4,2,3,2,3,1,4,4,2,3,1,3,1,4,2
4;2,1,3,2,3,2,1,4,1,4,2,3,2,3,4,1

Output sample:

Print out True/False if the grid is a valid sudoku layout. e.g.
True
False
 */

import java.io.*;
import java.math.*;

public class Sudoku {

    public Sudoku() {}

    public boolean checkSudoku(int[] nums, int n){
    	return checkRow(nums, n) && checkColumn(nums, n) && checkBox(nums, n);
    }

    public boolean checkRow(int[] nums, int n){
		//check all n rows
    	for(int row = 0; row < n; row++){
    		//create a boolean array to keep track of used digits (eg 1-4, 1-9)
    		boolean[] digits = new boolean[n];
    		//within each row, iterate through the columns
    		for(int col = 0; col < n; col++){
    			if(digits[nums[row*n + col] - 1]){
    				return false;
	    		}
	    		digits[nums[row*n + col] - 1] = true;
	    	}
    	}
    	//if all n rows passed, then return true
    	return true;
    }

    public boolean checkColumn(int[] nums, int n){
    	//check all n columns
    	for(int col = 0; col < n; col++){
    		//create a boolean array to keep track of used digits (eg 1-4, 1-9)
    		boolean[] digits = new boolean[n];
    		//within each column, iterate through the rows
    		for(int row = 0; row < n; row++){
    			if(digits[nums[row*n + col] - 1]){
    				return false;
	    		}
	    		digits[nums[row*n + col] - 1] = true;
	    	}
    	}
    	//if all n columns passed, return true
		return true;
    }

    public boolean checkBox(int[] nums, int n){
    	//size of each box is either 2x2 or 3x3, which is square root of n
    	int size = (int)Math.sqrt(n);
    	//check n boxes with double for loops (eg 4x4 grid has 4 boxes)
    	for(int boxRow = 0; boxRow < size; boxRow++){
    		for(int boxCol = 0; boxCol < size; boxCol++){
	    		//create a boolean array to keep track of used digits (eg 1-4, 1-9)
	    		boolean[] digits = new boolean[n];
	    		for(int row = 0; row < size; row++){
	    			for(int col = 0; col < size; col++){
	    				if(digits[nums[boxRow*size*n + boxCol*size + row*n + col] - 1]){
		    				return false;
			    		}
			    		digits[nums[boxRow*size*n + boxCol*size + row*n + col] - 1] = true;
	    			}
	    		}
    		}
    	}
		//if all n boxes passed, return true
		return true;
    }

	public static void main(String[] args){
		Sudoku sudoku = new Sudoku();
		//File file = new File(args[0]);
		File file = new File("C:\\Users\\Gavin_2\\Documents\\Java Projects\\SudokuTest.txt");
	    try{
	    	BufferedReader in = new BufferedReader(new FileReader(file));
	    	String line;
		    while ((line = in.readLine()) != null) {
		    	//split the input line into the size (4 or 9) and values
		    	String[] lineArray = line.split(";");
		    	//check if input is valid
		    	if(lineArray.length != 2){
		    		System.out.println("Invalid Input");
		    		continue;
		    	}
		    	//lineArray[0] contains the size
		    	int size = Integer.parseInt(lineArray[0]);
		    	//lineArray[1] contains the values, which can be further split into individual numbers
		    	String[] nums = lineArray[1].split(",");
				//check if input values are valid (the numbers fill up the grid with no missing or extra numbers)
		    	if(nums.length != size*size){
		    		System.out.println("Invalid Input");
		    		continue;
		    	}
		    	int[] input = new int[size*size];
		    	for(int i = 0; i < size*size; i++){
					input[i] = Integer.parseInt(nums[i]);
		    	}
		    	//perform Sudoku check
		    	boolean result = sudoku.checkSudoku(input, size);
		    	if(result){
		    		System.out.println("True");
		    	}else{
		    		System.out.println("False");
		    	}
		    }
	    }catch(IOException e){
	    	e.printStackTrace();
	    }
		//4;1,4,2,3,2,3,1,4,4,2,3,1,3,1,4,2 true
		//4;2,1,3,2,3,2,1,4,1,4,2,3,2,3,4,1 false


//	    int[] array1 = {1,4,2,3,2,3,1,4,4,2,3,1,3,1,4,2};
//	    int[] array2 = {2,1,3,2,3,2,1,4,1,4,2,3,2,3,4,1};
//	    boolean test1a = sudoku.checkRow(array1, 4);
//	    boolean test2a = sudoku.checkRow(array2, 4);
//	    System.out.println("Test 1 check row: " + test1a);
//	    System.out.println("Test 2 check row: " + test2a);
//	    boolean test1b = sudoku.checkColumn(array1, 4);
//	    boolean test2b = sudoku.checkColumn(array2, 4);
//	    System.out.println("Test 1 check col: " + test1b);
//	    System.out.println("Test 2 check col: " + test2b);
//	    boolean test1c = sudoku.checkBox(array1, 4);
//	    boolean test2c = sudoku.checkBox(array2, 4);
//	    System.out.println("Test 1 check box: " + test1c);
//	    System.out.println("Test 2 check box: " + test2c);
//	    System.out.println("Test 1 check col: " + test1b);
//	    System.out.println("Test 2 check col: " + test2b);
//	    boolean test1d = sudoku.checkSudoku(array1, 4);
//	    boolean test2d = sudoku.checkSudoku(array2, 4);
//	    System.out.println("Test 1 check Sudoku: " + test1d);
//	    System.out.println("Test 2 check Sudoku: " + test2d);
	}

}