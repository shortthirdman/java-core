package com.shortthirdman.core.challenges;

/**
 * @Matrix.java
 * @version 1.00 2012/12/25
 * Description: Rotate a matrix 90 degree clockwise.
 */

public class Matrix {

	int[][] matrix;

    public Matrix(int row, int col) {
    	matrix = new int[row][col];
    }

    public void fillMatrix(){
    	int counter = 1;
    	for(int row = 0; row < matrix.length; row++){
    		for(int col = 0; col < matrix[row].length; col++){
    			matrix[row][col] = counter++;
    		}
    	}
    }

    public void printMatrix(){
    	for(int row = 0; row < matrix.length; row++){
    		for(int col = 0; col < matrix[row].length; col++){
    			System.out.print(matrix[row][col] + " \t");
    		}
    		System.out.println();
    	}
    }

    public void rotate90dc(){
    	for(int row = 0; row < matrix.length/2; row++){ //row = 0, 1
    		int end = matrix.length - 1;		//end = 3
    		for(int col = row; col < matrix[row].length-1-row; col++){	//col = 0			1				2
    			int temp = matrix[row][col];							//temp = [0][0]			   [0][1]		   [0][2]
    			matrix[row][col] = matrix[end-col][row];				//[0][0]=[3][0] 	[0][1]=[2][0]	[0][2]=[1][0]
    			matrix[end-col][row] = matrix[end-row][end-col];		//[3][0]=[3][3]		[2][0]=[3][2]	[1][0]=[3][1]
    			matrix[end-row][end-col] = matrix[col][end-row];		//[3][3]=[0][3]		[3][2]=[1][3]	[3][1]=[2][3]
    			matrix[col][end-row] = temp;
    		}
    	}
    }

	public static void main(String[] args){
		Matrix test = new Matrix(3,3);
		System.out.println("Testing 3x3 Matrix:");
		test.fillMatrix();
		test.printMatrix();
		System.out.println("after rotating 90 degree");
		test.rotate90dc();
		test.printMatrix();

		test = new Matrix(4,4);
		System.out.println("\nTesting 4x4 Matrix:");
		test.fillMatrix();
		test.printMatrix();
		System.out.println("after rotating 90 degree");
		test.rotate90dc();
		test.printMatrix();

		test = new Matrix(5,5);
		System.out.println("\nTesting 5x5 Matrix:");
		test.fillMatrix();
		test.printMatrix();
		System.out.println("after rotating 90 degree");
		test.rotate90dc();
		test.printMatrix();
	}

}