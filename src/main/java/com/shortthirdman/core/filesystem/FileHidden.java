package com.shortthirdman.core.filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Swetank Mohanty (shortthirdman)
 *
 */
public class FileHidden {
    public static void main(String[] args) throws FileNotFoundException, IOException {
    	File file = new File("c:\\hidden-file.txt");

    	if(file.isHidden()) {
    		System.out.println("This file is hidden");
    	} else {
    		System.out.println("This file is not hidden");
    	}
    }
}