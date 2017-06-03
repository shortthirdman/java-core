package com.shortthirdman.core.gui.swing;

import java.awt.Dimension;
import javax.swing.*;

import javax.media.bean.playerbean.MediaPlayer;

class CustomMediaPlayer extends JFrame {
	MediaPlayer player; // our player

	public CustomMediaPlayer(String path) {
		super("Simple Media Player");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(640,480)); // set the window size
		player = new MediaPlayer();
		
		// path – path to the file
		player.setMediaLocation("file:///" + path); 
		player.setPlaybackLoop(false); // repeat the video
		player.prefetch (); // preliminary processing of the player; without it the player will not appear
		
		// add to the frame 
		add(player);
		// player.start (); - start the player immediately

		setVisible(true);
	}

	public static void main(String []args) {
		CustomMediaPlayer ve = new CustomMediaPlayer("iPhone - Security - Apple.mp4");
	}
}