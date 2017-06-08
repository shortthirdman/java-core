package com.shortthirdman.core.network.mail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    
    public static void main(String[] args) {
        try {
            String mydomain = "javahungry@blogspot.com";
            String emailregex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
            Boolean b = mydomain.matches(emailregex);
            
            if (b == false) {
                System.out.println("Email Address is Invalid");
            } else if(b == true) {
                System.out.println("Email Address is Valid");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}