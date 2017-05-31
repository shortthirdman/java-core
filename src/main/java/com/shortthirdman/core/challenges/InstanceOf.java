package com.shortthirdman.core.challenges;

import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;

public class InstanceOf{
	public Object[] objs;
	public int counter;
	
	public InstanceOf(){
		objs = new Object[5];
		counter = 0;
	}
	
	public void addItem(Object o){
		objs[counter++] = o;
	}
	
	public static void main(String[] args){
		InstanceOf test = new InstanceOf();
		test.addItem(1);
		test.addItem("string");
		test.addItem(new ArrayList<Integer>());
		test.addItem(false);
		test.addItem(3.2);
		
		if(test.objs[0] instanceof Integer)
			System.out.println("is int");
		if(test.objs[0] instanceof Float)
			System.out.println("int is float");
		if(test.objs[0] instanceof String)
			System.out.println("int is not string");
		if(test.objs[1] instanceof String)
			System.out.println("is string");
		if(test.objs[2] instanceof ArrayList)
			System.out.println("is array");
		if(test.objs[3] instanceof Boolean)
			System.out.println("is boolean");
		if(test.objs[4] instanceof Double)
			System.out.println("is double");
	}
	
}