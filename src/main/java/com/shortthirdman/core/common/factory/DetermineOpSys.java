package com.shortthirdman.core.common.factory;

/*
        Determine operating system using System class
        This example shows how to determine the operatng system
        JVM is running on.
*/
 
 
public class DetermineOpSys {
 
        public static void main(String[] args) {
               
                /*
                 * Get os.name system property using
                 * public static String getProperty(String name) method of
                 * System class.
                 */
                 
                 String strOSName = System.getProperty("os.name");
                 
                 System.out.print("Get OS name example.. OS is ");
                 if(strOSName != null)
                 {
                        if(strOSName.toLowerCase().indexOf("windows") != -1)
                                System.out.println("Windows");
                        else
                                System.out.print("not windows");
                 }
        }
}