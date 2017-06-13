package com.shortthirdman.core.common.factory;

/*
        Get system properties using System class
        This example shows how to get system properties using
        System class. This example also shows how to get and print
        all system properties.
*/
 
import java.util.Properties;
 
public class GetSystemProperties {
 
        public static void main(String[] args) {
               
                /*
                 * To get system properties use,
                 * static Properties getProperties() of System class.
                 *
                 * getProperties is a static method.
                 *
                 * Plase note that these properties are environment
                 * specific.
                 */
                 
                 Properties prop = System.getProperties();
                 
                 System.out.println("Printing all System properties");
                 
                 /*
                  * To print all system properties use
                  * static void list(PrintStream ps) method of System
                  * class.
                  *
                  * Hint : To print properties on console, paas
                  * System.out to list method.
                  */
                 
                  prop.list(System.out);
                 
                 
        }
}