package com.shortthirdman.core.common.factory;

/*
 * Suggest JVM to Run Garbage Collector Example
 * This Java example shows how to suggest the JVM to run garbage
 * collection using gc method of Runtime class.
 *
 * @author Swetank Mohanty (shortthirdman) 
*/
public class RunGarbageCollector {
	public static void main(String args[]) {
		Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        System.out.println("JVM has made best effort to garbage collect!");
    }      
}