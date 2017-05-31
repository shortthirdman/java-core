/**
 * @RobotMovements.java
 * @version 1.00 2013/1/27
Description:

A robot is located at the top-left corner of a 4x4 grid.
The robot can move either up, down, left, or right, but
can not visit the same spot twice. The robot is trying
to reach the bottom-right corner of the grid.

Input sample:

There is no input for this program.

Output sample:

Print out the unique number of ways the robot can reach
its destination. (The number should be printed as an integer
whole number eg. if the answer is 10 (its not !!), print
out 10, not 10.0 or 10.00 etc)
 */
package com.shortthirdman.core.challenges;

public class RobotMovements {

	boolean[][] grid;
	int paths;

    public RobotMovements() {
    	grid = new boolean[4][4];
    	for(int row = 0; row < 4; row++){
    		for(int col = 0; col < 4; col++){
    			grid[row][col] = false;
    		}
    	}
    	//initialize robot in top left corner
    	grid[0][0] = true;
    	paths = 0;
    }

    public int findNumPath(int row, int col){
    	int total = 0;
		//if grid[3][3] is reached, 1 path is found
    	if(row == 3 && col == 3){
    		return 1;
    	}
    	grid[row][col] = true;
    	//check 4 directions
    	if(col < 3 && grid[row][col+1] == false){
    		total = total + findNumPath(row, col+1);
    	}
    	if(col > 0 && grid[row][col-1] == false){
    		total = total + findNumPath(row, col-1);
    	}
    	if(row < 3 && grid[row+1][col] == false){
    		total = total + findNumPath(row+1, col);
    	}
    	if(row > 0 && grid[row-1][col] == false){
    		total = total + findNumPath(row-1, col);
    	}
    	grid[row][col] = false;
    	return total;
    }

    public void printGrid(){
    	for(int row = 0; row < 4; row++){
    		for(int col = 0; col < 4; col++){
    			System.out.print(grid[row][col]+"\t");
    		}
    		System.out.println();
    	}
    }

    public static void main(String[] args){
    	RobotMovements test = new RobotMovements();
    	//test.printGrid();
    	System.out.println(test.findNumPath(0,0));
    }


}