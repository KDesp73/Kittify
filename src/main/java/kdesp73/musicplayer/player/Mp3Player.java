/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer.player;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JSlider;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

/**
 *
 * @author konstantinos
 */
public class Mp3Player extends AudioPlayer {

	private BasicPlayer player;
	private JSlider progressSlider;

	public Mp3Player() {
		super(0, null);
		this.player = new BasicPlayer();
		this.volume = 1;
	}

	public Mp3Player(int index, ArrayList<String> playlist) {
		super(index, playlist);

		this.player = new BasicPlayer();
		this.volume = 1;

	}

	public void setSlider(JSlider slider) {
		this.progressSlider = slider;
	}

	@Override
	public void play() {

		try {

			if (player.getStatus() == BasicPlayer.PAUSED) {
				player.resume();
				return;
			}
			if (player.getStatus() != BasicPlayer.STOPPED) {
				player.stop();
			}

			player.open(new File(playlist.get(playingIndex)));

			System.out.println("Playing \"" + playlist.get(playingIndex) + "\"");

			player.play();
			player.setGain(volume);
		} catch (BasicPlayerException bpEx) {
			Logger.getLogger(Mp3Player.class.getName()).log(Level.SEVERE, null, bpEx);
		}

	}

	public void play(int index) {

		try {

			if (player.getStatus() == BasicPlayer.PAUSED) {
				player.resume();
				return;
			}
			if (player.getStatus() != BasicPlayer.STOPPED) {
				player.stop();
			}

			player.open(new File(playlist.get(index)));

			System.out.println("Playing \"" + playlist.get(playingIndex) + "\"");

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
	public void seek(int seconds) {
		System.out.println("Seconds: " + seconds);
		System.out.println("Frames: " + secondsToFrames(seconds));

		try {
			player.seek(seconds);
			player.setGain(this.volume);
		} catch (BasicPlayerException bpEx) {
			Logger.getLogger(Mp3Player.class.getName()).log(Level.SEVERE, null, bpEx);
		}
	}

	private long secondsToFrames(long seconds) {
		return -1;
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

	@Override
	public void next() {
		this.playingIndex++;
		play(playingIndex);
	}

	@Override
	public void prev() {
		this.playingIndex--;
		play(playingIndex);
	}

	@Override
	public void setSong(int index) {
		this.playingIndex = index;
	}
}
