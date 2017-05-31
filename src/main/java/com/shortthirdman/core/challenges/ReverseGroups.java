package com.shortthirdman.core.challenges;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReverseGroups {
	public static void main (String[] args) {
		    File file = new File("ReversGroups.txt");
		    BufferedReader in = null;
			try {
				in = new BufferedReader(new FileReader(file));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		    String line;
		    try {
				while ((line = in.readLine()) != null) {
					String s="";
					String[] sub1=line.split(";");
					int k=Integer.parseInt(sub1[1]);
					String[] sub2=sub1[0].split(",");
					int rapport=sub2.length/k;
					for( int i = 0 ; i < rapport*k ; i+=k ) {
						for( int j = k+i-1 ; j >= i  ; j-- ) {
							s+=sub2[j]+",";
						}
					}
					if(sub2.length%k!=0) {
						for( int j = rapport*k ; j <sub2.length  ; j++ ) {
							s+=sub2[j]+",";
						}
					}
					System.out.println(s.substring(0,s.length()-1));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}