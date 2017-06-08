package com.shortthirdman.core.network.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.PasswordAuthentication;
import java.net.URL;

public class PasswordProtectedAccess {
  public static void main(String[] argv) throws Exception {
    Authenticator.setDefault(new CustomAuthenticator());
    URL url = new URL("http://hostname:80/index.html");

    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
    String str;
    while ((str = in.readLine()) != null) {
      System.out.println(str);
    }
    in.close();
  }
}

class CustomAuthenticator extends Authenticator {
  
  protected PasswordAuthentication getPasswordAuthentication() {
    String promptString = getRequestingPrompt();
    System.out.println(promptString);
    String hostname = getRequestingHost();
    System.out.println(hostname);
    InetAddress ipaddr = getRequestingSite();
    System.out.println(ipaddr);
    int port = getRequestingPort();

    String username = "name";
    String password = "password";
    return new PasswordAuthentication(username, password.toCharArray());
  }
}