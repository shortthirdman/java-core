package com.shortthirdman.core.filesystem;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Swetank Mohanty (shortthirdman)
 *
 */
public class ReadContent {

	public static void main (String args[]) {
		try {
	         DataInputStream dis = new DataInputStream(new FileInputStream ("c:\\logging.log"));    
			 byte[] datainBytes = new byte[dis.available()];
			 
			 dis.readFully(datainBytes);
			 dis.close();
		     
			 String content = new String(datainBytes, 0, datainBytes.length);
		     System.out.println(content);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
	}
}