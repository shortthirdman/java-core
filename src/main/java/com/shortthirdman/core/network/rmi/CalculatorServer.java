package com.shortthirdman.core.network.rmi;

import java.rmi.*;
import java.rmi.server.*;

public class CalculatorServer extends UnicastRemoteObject implements Calculator {

	public CalculatorServer()throws RemoteException {
		System.out.println("Server is Instantiated");
	}

	public int sum(int first,int Second) throws RemoteException {
		return (first + Second);
	}

	public int sub(int first,int Second) throws RemoteException {
		return (first - Second);
	}    

	public int mul(int first,int Second) throws RemoteException {
		return (first * Second);
	}    

	public static void main(String arg[]) {
		try {
			CalculatorServer p = new CalculatorServer();
			Naming.rebind("Cal",p);
		} catch(Exception e) {
			System.out.println("Exception occurred : " + e.getMessage());
		}
	}
}