package com.shortthirdman.core.filesystem;

import java.io.*;
 
/**
 * @author Swetank Mohanty (shortthirdman)
 *
 */
public class CreateDirWithNonExistentParentDir {
	public static void main(String args[]) throws IOException, FileNotFoundException {
 		File file = new File("F:\\Testing\\first\\second");
 		boolean b = false;
 
		if (!file.exists()) {
 			b = file.mkdirs();
 		}
 		if (b)
 			System.out.println("Directory successfully created");
 		else
 			System.out.println("Failed to create directory");
	}
}
/*
	File file = new File("directory path");
	if(file.exists()) {
    		System.out.println("File Exists");
	}
	else {
    		boolean wasDirecotyMade = file.mkdirs();
    		if(wasDirecotyMade)
    			System.out.println("Direcoty Created");
    		else
    			System.out.println("Sorry could not create directory");
	}
*/
