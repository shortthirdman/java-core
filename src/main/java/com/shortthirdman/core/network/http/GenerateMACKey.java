package com.shortthirdman.core.network.http;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class GenerateMACKey {
  public static void main(String[] argv) throws Exception {

    KeyGenerator keyGen = KeyGenerator.getInstance("HmacMD5");
    SecretKey key = keyGen.generateKey();

    // Generate a key for the HMAC-SHA1 keyed-hashing algorithm
    keyGen = KeyGenerator.getInstance("HmacSHA1");
    key = keyGen.generateKey();
  }
}
