package com.shortthirdman.core.challenges;

import java.io.*;

public class LongestLines {
	int numOutputLines;
	String[] lines;
	public LongestLines(){}
	
	public void initializeVariables(int size){
		numOutputLines = size;
		lines = new String[numOutputLines];
	}
	
    public static void main (String[] args) {
		//File file = new File(args[0]);
		LongestLines test = new LongestLines();
		File file = new File("C:\\Users\\Gavin\\Documents\\GitHub\\MiniProjects\\LongestLinesTest.txt");
		try{
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line;
			int counter = 0;
			while ((line = in.readLine()) != null) {
				if(counter == 0){
					test.initializeVariables(Integer.parseInt(line));
				}else if(counter < test.numOutputLines){
					test.lines[counter-1] = line;
				}else if(counter == test.numOutputLines){
					test.lines[counter-1] = line;
					test.sort();
				}else{
					test.addToHeap(line);
				}
				counter++;
			}
		}catch(IOException e){
	    	e.printStackTrace();
	    }
		for(int i = 0; i < test.lines.length; i++){
			System.out.println(test.lines[i]);
		}
	}
	
	public void sort(){
		for(int i = 0; i < lines.length; i++){
			int maxIndex = i;
			for(int j = i+1; j < lines.length; j++){
				if(lines[j].length() > lines[maxIndex].length()){
					maxIndex = j;
				}
			}
			String temp = lines[i];
			lines[i] = lines[maxIndex];
			lines[maxIndex] = temp;
		}
	}
	
	public void addToHeap(String line){
		if(line.length() > lines[numOutputLines-1].length()){
			lines[numOutputLines-1] = line;
			int insertIndex = numOutputLines - 1;
			for(int i = numOutputLines - 2; i >= 0; i--){
				if(line.length() > lines[i].length()){
					String temp = lines[i];
					lines[i] = lines[i+1];
					lines[i+1] = temp;
				}else{
					break;
				}
			}
		}
	}
}