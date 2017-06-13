package com.shortthirdman.core.common.factory;

/*
        Get Total Memory of Java Virtual Machine(JVM) Example
        This Java example shows how to get amount of total memory
        in the Java Virtual Machine(JVM) using totalMemory method
        of Java Runtime class.
*/
public class GetTotalMemory {
	public static void main(String args[]) {
		Runtime runtime = Runtime.getRuntime();
               
		long totalMemory = runtime.totalMemory();
                 
        System.out.println(totalMemory + " bytes total in JVM");
    }
}
 