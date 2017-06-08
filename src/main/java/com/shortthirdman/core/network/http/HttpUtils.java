package com.shortthirdman.core.network.http;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class HttpUtils {

    public static void main(String[] args) {
        String fromFile = "ftp://ftp.arin.net/pub/stats/arin/delegated-arin-extended-latest";
        String toFile = "F:\\arin.txt";

        try {
            //connectionTimeout, readTimeout = 10 seconds
            FileUtils.copyURLToFile(new URL(fromFile), new File(toFile), 10000, 10000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
public static void copyURLToFile(URL source, File destination, int connectionTimeout, int readTimeout) throws IOException;
*/