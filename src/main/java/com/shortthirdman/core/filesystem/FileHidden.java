package com.shortthirdman.core.filesystem;

import java.io.File;
import java.io.IOException;

public class FileHidden {
    public static void main(String[] args) throws IOException {
    	File file = new File("c:\\hidden-file.txt");

    	if(file.isHidden()) {
    		System.out.println("This file is hidden");
    	} else {
    		System.out.println("This file is not hidden");
    	}
    }
}