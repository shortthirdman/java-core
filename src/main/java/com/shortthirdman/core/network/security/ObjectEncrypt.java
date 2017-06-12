package com.shortthirdman.core.network.security;

import java.io.*;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

public class ObjectEncrypt {
    private static void writeToFile(String filename, Object object) throws Exception {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(new File(filename));
            oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                oos.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        SecretKey key = KeyGenerator.getInstance("DES").generateKey();
        writeToFile("secretkey.dat", key);

        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        SealedObject sealedObject = new SealedObject("THIS IS A SECRET MESSAGE!", cipher);

        writeToFile("sealed.dat", sealedObject);
    }
}