package com.shortthirdman.core.common.factory;

/*
        Get Free Memory of Java Virtual Machine(JVM) Example
        This Java example shows how to get amount of free memory
        in the Java Virtual Machine(JVM) using freeMemory method
        of Java Runtime class.
*/
public class GetFreeMemory {
        public static void main(String args[]) {
                /*
                 * get current Java Runtime using getRuntime()
                 * method of Runtime class.
                 */
                Runtime runtime = Runtime.getRuntime();
               
                /*
                 * To determine amount of free memory available to current
                 * Java Virtual Machine(JVM), use
                 *
                 * long freeMemory()
                 * method of Runtime class.
                 */
                 
                 long freeMemory = runtime.freeMemory();
                 
                 System.out.println(freeMemory + " bytes free in JVM");
        }      
}