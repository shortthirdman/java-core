/*
Description:

Write a program to print a 2D array (n x m) in spiral order (clockwise)

Input sample:

Your program should accept as its first argument a path to a filename.
The input file contains several lines. Each line is one test case.
Each line contains three items (semicolon delimited). The first is
'n'(rows), the second is 'm'(columns) and the third is a single space
separated list of characters/numbers in row major order. e.g.
3;3;1 2 3 4 5 6 7 8 9

Output sample:

Print out the matrix in clockwise fashion, one per line, space delimited. eg.
1 2 3 6 9 8 7 4 5

*/
package com.shortthirdman.core.challenges;

import java.io.*;

public class SpiraPrinting {
    String[][] array;
    int n, m;
	int size;

    public SpiraPrinting(int nSize, int mSize, String[] chars) {
    	n = nSize;
    	m = mSize;
    	size = n*m;
    	array = new String[n][m];
    	int counter = 0;
    	for(int row = 0; row < n; row++){
    		for(int col = 0; col < m; col++){
    			array[row][col] = chars[counter];
    			counter++;
    		}
    	}
    }

    public String getSpiral(){
    	int leftEnd = 0;
    	int topEnd = 0;
    	int bottomEnd = n-1;
    	int rightEnd = m-1;
    	boolean goRight = true;
    	boolean goDown = true;
		int counter = 0;
		String result = "";
		while(counter < size){
			//go 1 cycle then reset boundaries
			//print top
		   	for(int top = leftEnd; top <= rightEnd; top++){
				result = result + array[topEnd][top] + " ";
				counter++;
	    	}
	    	if(counter >= size){
	    		break;
	    	}
	    	topEnd++;
	    	//print right
	    	for(int right = topEnd; right <= bottomEnd; right++){
	    		result = result + array[right][rightEnd] + " ";
	    		counter++;
	    	}
	    	if(counter >= size){
	    		break;
	    	}
	    	rightEnd--;
	    	//print bottom
	    	for(int bottom = rightEnd; bottom >= leftEnd; bottom--){
	    		result = result + array[bottomEnd][bottom] + " ";
	    		counter++;
	    	}
	    	if(counter >= size){
	    		break;
	    	}
	    	bottomEnd--;
	    	//print left
	    	for(int left = bottomEnd; left >= topEnd; left--){
	    		result = result + array[left][leftEnd] + " ";
	    		counter++;
	    	}
	    	if(counter >= size){
	    		break;
	    	}
	    	leftEnd++;
		}
		return result.substring(0, result.length()-1);
    }


	/*  printSpiral() was my original implementation,
        I thought my submission failed because I was printing
        the space after the last array element

        The above method getSpiral(), which returns the result
        as a String is preferred not only because it eliminates
        the last space character, but also "System.out.print"
        slows down the run time. Printing the returned String
        value from getSpiral() only prints once.
    */
    public void printSpiral(){
    	int leftEnd = 0;
    	int topEnd = 0;
    	int bottomEnd = n-1;
    	int rightEnd = m-1;
    	boolean goRight = true;
    	boolean goDown = true;
		int counter = 0;
		while(counter < size){
			//go 1 cycle then reset boundaries
			//print top
		   	for(int top = leftEnd; top <= rightEnd; top++){
				System.out.print(array[topEnd][top] + " ");
				counter++;
	    	}
	    	if(counter >= size){
	    		break;
	    	}
	    	topEnd++;
	    	//print right
	    	for(int right = topEnd; right <= bottomEnd; right++){
	    		System.out.print(array[right][rightEnd] + " ");
	    		counter++;
	    	}
	    	if(counter >= size){
	    		break;
	    	}
	    	rightEnd--;
	    	//print bottom
	    	for(int bottom = rightEnd; bottom >= leftEnd; bottom--){
	    		System.out.print(array[bottomEnd][bottom] + " ");
	    		counter++;
	    	}
	    	if(counter >= size){
	    		break;
	    	}
	    	bottomEnd--;
	    	//print left
	    	for(int left = bottomEnd; left >= topEnd; left--){
	    		System.out.print(array[left][leftEnd] + " ");
	    		counter++;
	    	}
	    	if(counter >= size){
	    		break;
	    	}
	    	leftEnd++;
		}
		System.out.println();
    }

    public static void main (String[] args) {
	    //File file = new File(args[0]);
	    File file = new File("C:\\Users\\Gavin\\Documents\\GitHub\\MiniProjects\\SpiralPrintingTest.txt");
	    try {
	    	BufferedReader in = new BufferedReader(new FileReader(file));
	    	String line;
		    while ((line = in.readLine()) != null) {
		    	//split the input line into n, m, and values
		    	String[] lineArray = line.split(";");
                //check if input is valid
		    	if(lineArray.length != 3){
		    		//System.out.println("Invalid Input");
		    		continue;
		    	}
		    	//try block for catching parsing int exceptions
		    	num:{
			    	try{
			    		//lineArray[0] contains size of n
				    	int nSize = Integer.parseInt(lineArray[0]);
				    	//System.out.println("n: " + nSize);
				    	//lineArray[1] contains size of m
				    	int mSize = Integer.parseInt(lineArray[1]);
				    	//System.out.println("m: " + mSize);
				    	//lineArray[2] contains the values in the 2D array
				    	String[] chars = lineArray[2].split(" ");
//				    	System.out.print("list: ");
//				    	for(int i = 0; i< nums.length; i++){
//				    		System.out.print(nums[i] + " ");
//				    	}
//				    	System.out.println();
						//check if input values are valid (the numbers fill up the grid with no missing or extra numbers)
				    	if(chars.length != nSize*mSize){
				    		//System.out.println("Invalid Input Values");
				    		break num;
				    	}
				    	SpiraPrinting test = new SpiraPrinting(nSize, mSize, chars);
				    	//print in spiral
				    	//test.printSpiral();
				    	System.out.println(test.getSpiral());
			    	} catch (NumberFormatException e) {
			    		e.printStackTrace();
			    		break num;
			    	}
		    	}
		    }
	    } catch (IOException e) {
	    	e.printStackTrace();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
    }
}