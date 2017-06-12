package com.shortthirdman.core.network.security;

import java.io.*;

import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import javax.crypto.SealedObject;

public class ObjectDecrypt {
    private static Object readFromFile(String filename) throws Exception {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Object object = null;

        try {
            fis = new FileInputStream(new File(filename));
            ois = new ObjectInputStream(fis);
            object = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                ois.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return object;
    }

    public static void main(String[] args) throws Exception {
        SecretKey key = (SecretKey) readFromFile("secretkey.dat");

        SealedObject sealedObject = (SealedObject) readFromFile("sealed.dat");

        String algorithmName = sealedObject.getAlgorithm();
        Cipher cipher = Cipher.getInstance(algorithmName);
        cipher.init(Cipher.DECRYPT_MODE, key);

        String text = (String) sealedObject.getObject(cipher);
        System.out.println("Text = " + text);
    }
}