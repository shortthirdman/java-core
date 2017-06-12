package org.kodejava.example.zip;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class GZipCompress {
    public static void main(String[] args) {
        String sourceFile = "core-sample/target/classes/input.txt";
        String targetFile = "core-sample/target/classes/output.gz";

        try {
            FileOutputStream fos = new FileOutputStream(targetFile);

            GZIPOutputStream gzos = new GZIPOutputStream(fos);

            byte[] buffer = new byte[1024];
            int length;

            FileInputStream fis = new FileInputStream(sourceFile);

            while ((length = fis.read(buffer)) > 0) {
                gzos.write(buffer, 0, length);
            }

            fis.close();
            gzos.finish();
            gzos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}