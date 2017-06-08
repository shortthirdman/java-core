package com.shortthirdman.core.network.mail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailSubDomainValidator {
    
    public static void main(String[] args) {
        try {
            String mydomain = "javahungry@gmail.co.in";
            String emailregex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.(?:[A-Z]{2,}|com|co)*(\\.(?:[A-Z]{2,}|us|in|ch|jp|uk)))+$"; 
            Boolean result = mydomain.matches(emailregex);
            
            if (result == false) {
                System.out.println("Email Address is Invalid");
            } else if (result == true) {
                System.out.println("Email Address is Valid");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}