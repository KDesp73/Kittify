/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer.player;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author konstantinos
 */
public class Mp3Player extends AudioPlayer {

	private BasicPlayer player;

	public Mp3Player() {
		super("");
		this.player = new BasicPlayer();
	}

	public Mp3Player(String file) {
		super(file);

		this.player = new BasicPlayer();

		try {
			player.open(new File(super.getPath()));
		} catch (BasicPlayerException ex) {
			Logger.getLogger(Mp3Player.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@Override
	public void play() {
		System.out.println("Play");

		try {
			player.play();
		} catch (BasicPlayerException ex) {
			Logger.getLogger(Mp3Player.class.getName()).log(Level.SEVERE, null, ex);
		}

		super.setPlaying(true);
	}

	@Override
	public void stop() {
		System.out.println("Stop");
		try {
			player.stop();
		} catch (BasicPlayerException ex) {
			Logger.getLogger(Mp3Player.class.getName()).log(Level.SEVERE, null, ex);
		}
		super.setPlaying(false);
	}

	@Override
	public void pause() {
		System.out.println("Pause");
		try {
			player.pause();
		} catch (BasicPlayerException ex) {
			Logger.getLogger(Mp3Player.class.getName()).log(Level.SEVERE, null, ex);
		}
		super.setPlaying(false);
	}

	@Override
	public void seek(long sample) {
		System.out.println("Seek");
		try {
			player.seek(sample);
		} catch (BasicPlayerException ex) {
			Logger.getLogger(Mp3Player.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
