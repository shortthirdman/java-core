package com.shortthirdman.core.networks.rmi;

import java.rmi.*;
import java.io.*;

public class CalculatorClient {    
	public static void  main(String args[]) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			Calculator p = (Calculator) Naming.lookup("Cal");

			System.out.println("Enter fisrt number");
			String strFirst = br.readLine();

			System.out.println("Enter second number");
			String strSecond = br.readLine();

			System.out.println("Sum : " + p.sum(Integer.parseInt(strFirst),Integer.parseInt(strSecond)));
			System.out.println("Sub : " + p.sub(Integer.parseInt(strFirst),Integer.parseInt(strSecond)));
			System.out.println("Mul : " + p.mul(Integer.parseInt(strFirst),Integer.parseInt(strSecond)));
		} catch (Exception e) {
			System.out.println("Exception occurred : "+e.getMessage());
		}
	}
}