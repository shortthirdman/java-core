package com.shortthirdman.core.filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Swetank Mohanty (shortthirdman)
 *
 */
public class FileReadAttribute {
    public static void main(String[] args) throws IOException, FileNotFoundException {
    	File file = new File("c:\\file.txt");

    	//mark this file as read only, since jdk 1.2
    	file.setReadOnly();

    	if(file.canWrite()) {
    	     System.out.println("This file is writable");
    	} else {
    	     System.out.println("This file is read only");
    	}

    	//revert the operation, mark this file as writable, since jdk 1.6
    	file.setWritable(true);

    	if(file.canWrite()) {
    	     System.out.println("This file is writable");
    	} else {
    	     System.out.println("This file is read only");
    	}
    }
}