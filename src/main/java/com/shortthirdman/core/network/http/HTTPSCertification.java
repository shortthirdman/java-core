package com.shortthirdman.core.network.http;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.cert.CertPath;
import java.security.cert.CertificateFactory;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class HTTPSCertification {
  public static void main(String args[]) throws Exception {
    SSLSocketFactory factory = HttpsURLConnection.getDefaultSSLSocketFactory();
    SSLSocket socket = (SSLSocket) factory.createSocket("127.0.0.1", 9999);
    socket.startHandshake();
    SSLSession session = socket.getSession();
    Certificate[] servercerts = session.getPeerCertificates();

    List mylist = new ArrayList();
    for (int i = 0; i < servercerts.length; i++) {
      mylist.add(servercerts[i]);
    }

    CertificateFactory cf = CertificateFactory.getInstance("X.509");
    CertPath cp = cf.generateCertPath(mylist);

    FileOutputStream f = new FileOutputStream("CertPath.dat");
    ObjectOutputStream b = new ObjectOutputStream(f);
    b.writeObject(cp);

  }
}