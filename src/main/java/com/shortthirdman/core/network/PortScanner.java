package com.shortthirdman.core.network;

import java.io.IOException;

import java.net.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class PortScanner {
	public static void main(String[] args) {
         InetAddress ia = null;
         String host = null;
         try {   
             host = JOptionPane.showInputDialog("Enter the Host name to scan:\n example: xxx.com");
             if(host != null) {
				ia = InetAddress.getByName(host);
				scan(ia);
			}
         } catch (UnknownHostException e) {
             System.err.println(e );
         }
         System.out.println("Bye from NFS");
     }
    
	public static void scan(final InetAddress remote) {
		int port = 0;
        String hostname = remote.getHostName();
        for (port = 0; port < 65536; port++) {
			try {
				Socket s = new Socket(remote,port);
				System.out.println("Server is listening on port " + port+ " of " + hostname);
				s.close();
			} catch (IOException ex) {
				System.out.println("Server is not listening on port " + port+ " of " + hostname);
			}
        }
    }
}