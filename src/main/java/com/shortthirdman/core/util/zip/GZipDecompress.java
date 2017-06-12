package org.kodejava.example.zip;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class GZipDecompress {
    public static void main(String[] args) {
        String sourceFile = "core-sample/target/classes/output.gz";
        String targetFile = "core-sample/target/classes/input-1.txt";

        try {
			FileInputStream fis = new FileInputStream(sourceFile);

            GZIPInputStream gzis = new GZIPInputStream(fis);

            byte[] buffer = new byte[1024];
            int length;

            FileOutputStream fos = new FileOutputStream(targetFile);

            while ((length = gzis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
            fos.close();
            gzis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}