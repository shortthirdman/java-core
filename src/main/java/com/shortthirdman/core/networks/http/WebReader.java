package com.shortthirdman.core.networks.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebReader {

  static void getData(String address) throws Exception {
    URL page = new URL(address);
    StringBuffer text = new StringBuffer();
    HttpURLConnection conn = (HttpURLConnection) page.openConnection();
    conn.connect();
    InputStreamReader in = new InputStreamReader((InputStream) conn.getContent());
    
    BufferedReader buff = new BufferedReader(in);
    String line = buff.readLine();
    while (line != null) {
        text.append(line + "\n");
        line = buff.readLine();
    }
    System.out.println(text.toString());
  }

  public static void main(String[] arguments) throws Exception {
    getData("http://java2s.com");
  }
}