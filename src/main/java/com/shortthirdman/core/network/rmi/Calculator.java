package com.shortthirdman.core.network.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {
	public int sum(int a, int b)throws RemoteException;
	public int sub(int a, int b)throws RemoteException;
	public int mul(int a, int b)throws RemoteException;
} 
