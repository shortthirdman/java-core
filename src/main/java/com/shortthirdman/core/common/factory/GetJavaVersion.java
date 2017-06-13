package com.shortthirdman.core.common.factory;

/*
        Get Java specification version using System class
        This example shows how to get the Java specification version
        using System class.
*/
 
public class GetJavaVersion {
 
        public static void main(String[] args) {
               
                /*
                 * Get java.specification.version system property using
                 * public static String getProperty(String name) method of
                 * System class.
                 */
               
                String strJavaVersion = System.getProperty("java.specification.version");
               
                System.out.println("Java Specification is : " + strJavaVersion);
        }
}