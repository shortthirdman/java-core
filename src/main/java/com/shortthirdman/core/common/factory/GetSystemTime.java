package com.shortthirdman.core.common.factory;

/*
        Get system using System class
        This example shows how to get the system time using currentTimeMillis
        method of Java System class.
*/
 
public class GetSystemTime {
 
        public static void main(String[] args) {               
                 long lnSystemTime = System.currentTimeMillis();
                 
                 System.out.println("Milliseconds since midnight, January 1, 1970 UTC : " + lnSystemTime);
        }
}