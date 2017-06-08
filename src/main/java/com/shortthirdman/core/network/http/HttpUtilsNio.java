package com.shortthirdman.core.network.http;

import java.io.FileOutputStream;
import java.io.IOException;

import java.net.URL;

import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class HttpUtilsNio {

    public static void main(String[] args) {

        String fromFile = "ftp://ftp.arin.net/pub/stats/arin/delegated-arin-extended-latest";
        String toFile = "F:\\arin.txt";

        try {
            URL website = new URL(fromFile);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(toFile);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}