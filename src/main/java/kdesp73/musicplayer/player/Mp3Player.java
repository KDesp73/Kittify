/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer.player;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;

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
		this.volume = 10;

	}

	@Override
	public void play() {
		
		try {
			if(player.getStatus() == BasicPlayer.PAUSED){
				player.resume();
				return;
			}

			player.open(new File(this.getPath()));
			player.play();
			player.setGain(volume);
		} catch (BasicPlayerException bpEx) {
			Logger.getLogger(Mp3Player.class.getName()).log(Level.SEVERE, null, bpEx);
		}

	}

	@Override
	public void stop() {
		try {
			player.stop();
		} catch (BasicPlayerException bpEx) {
			Logger.getLogger(Mp3Player.class.getName()).log(Level.SEVERE, null, bpEx);
		}

	}

	@Override
	public void pause() {
		try {
			player.pause();
		} catch (BasicPlayerException bpEx) {
			Logger.getLogger(Mp3Player.class.getName()).log(Level.SEVERE, null, bpEx);
		}

	}

	@Override
	public void seek(long sample) {
		try {
			player.seek(sample);
			player.setGain(this.volume);
		} catch (BasicPlayerException bpEx) {
			Logger.getLogger(Mp3Player.class.getName()).log(Level.SEVERE, null, bpEx);
		}

	}

	@Override
	public void setVolume(int value, int maxValue) {
		try {
			this.volume = value;

			if (value == 0) {
				player.setGain(0);
			} else {
				player.setGain(calcVolume(value, maxValue));
			}

		} catch (BasicPlayerException bpEx) {
			Logger.getLogger(Mp3Player.class.getName()).log(Level.SEVERE, null, bpEx);
		}
	}

	private double calcVolume(int currentValue, int maximumValue) {
		this.volume = (double) currentValue / (double) maximumValue;
		return volume;
	}

	@Override
	public boolean isPlaying() {
		return (player.getStatus() == BasicPlayer.PLAYING);
	}
}
