package com.shortthirdman.core.common.factory;

/*
        Get Maximum Memory Available to Java Virtual Machine(JVM) Example
        This Java example shows how to get maximum amount memory
        Java Virtual Machine will attempt to use using maxMemory()
        method of Runtime class.
*/
 
public class GetMaxMemory {
        public static void main(String args[]) {
                /*
                 * get current Java Runtime using getRuntime()
                 * method of Runtime class.
                 */
                Runtime runtime = Runtime.getRuntime();
               
                /*
                 * To determine amount of maximum memory Java Virtual
                 * Machine (JVM) will attempt to use, use
                 *
                 * long maxMemory()
                 * method of Runtime class.
                 *
                 * If there is no limit inherited, value of Long.MAX_VALUE
                 * will be returned.
                 */
                 
                 long maxMemory = runtime.maxMemory();
                 
                 System.out.println(maxMemory + " bytes max");
        }       
}