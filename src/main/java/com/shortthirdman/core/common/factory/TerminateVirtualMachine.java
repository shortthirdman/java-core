package com.shortthirdman.core.common.factory;

/*
        Terminate Java Virtual Machine (JVM) Example
        This Java example shows how to terminate currently running
        Java Virtual Machine(JVM) using halt method of Runtime class.
       
*/
 
public class TerminateVirtualMachine {
       
        public static void main(String args[]) {
                /*
                 * get current Java Runtime using getRuntime()
                 * method of Runtime class.
                 */
                Runtime runtime = Runtime.getRuntime();
               
                /*
                 * To forcibly terminate the currently running virtual machine, use
                 *
                 * void halt(int status)
                 * method of Runtime class.
                 *
                 * Usually, non-zero status is passed as status for abnormal exit.
                 *
                 * Please note that, unlike exit method, this method DOES NOT invoke
                 * shutdown hooks or run object finalizers.
                 *
                 *
                 */
                 
                 System.out.println("About to halt the current jvm");
                 runtime.halt(1);
                 
                 /*
                  * THIS METHOD NEVER RETURNS NORMALLY.
                  * This statement will never executed, as the JVM is
                  * terminated!
                  */
                 System.out.println("JVM Terminated");
        }      
}