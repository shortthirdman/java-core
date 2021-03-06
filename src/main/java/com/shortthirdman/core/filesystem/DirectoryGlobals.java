package com.shortthirdman.core.filesystem;

import java.io.*;

/**
 * @author Swetank Mohanty (shortthirdman)
 *
 */
public class DirectoryGlobals {

    /**
     * @param dir
     */
    public static void removeDirectory(File dir) {
    	if (dir.isDirectory()) {
    		File[] files = dir.listFiles();
    		if (files != null && files.length > 0) {
    			for (File aFile : files) {
    				removeDirectory(aFile);
    			}
    		}
    		dir.delete();
    	}
		else {
    		dir.delete();
    	}
    }

    /**
     * @param dir
     */
    public static void cleanDirectory(File dir) {
    	if (dir.isDirectory()) {
    		File[] files = dir.listFiles();
    		if (files != null && files.length > 0) {
    			for (File aFile : files) {
    				removeDirectory(aFile);
    			}
    		}
    	}
    }

	public static void main(String[] args) {
		String dirToRemove = "D:/Dir/To/Remove";
		removeDirectory(new File(dirToRemove));

		String dirToClean = "D:/Dir/To/Clean";
		cleanDirectory(new File(dirToClean));
	}
}