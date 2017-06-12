package com.shortthirdman.core.gui.jmf;

import javax.media.*;
import javax.media.NoPlayerException;

import java.io.*;
import java.io.IOException;

import java.net.URL;
import java.net.MalformedURLException;

class MP3 extends Thread {
  private URL url;
  private MediaLocator mediaLocator;
  private Player playMP3;
 
  public MP3(String mp3) {
    try {
      this.url = new URL(mp3);
    } catch (MalformedURLException e) {
	    System.out.println(e.getMessage());
	  }
  }
 
  public void run() {
    try {
      mediaLocator = new MediaLocator(url);     
      playMP3 = Manager.createPlayer(mediaLocator);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    } catch(NoPlayerException e) {
      System.out.println(e.getMessage());
    }

  playMP3.addControllerListener(new ControllerListener() {
      public void controllerUpdate(ControllerEvent e) {
        if (e instanceof EndOfMediaEvent) {
          playMP3.stop();
          playMP3.close();
        }
      }
    }
 );
 playMP3.realize();
 playMP3.start();
 } 
}

public class PlayMP3 {
  public static void main(String[] args) {
    MP3 t = new MP3("file:///C://JavaApplications//cd.mp3");
    t.start();
  }
}
