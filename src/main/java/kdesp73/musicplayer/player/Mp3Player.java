/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer.player;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import kdesp73.musicplayer.songs.Mp3File;

/**
 *
 * @author konstantinos
 */
public class Mp3Player extends AudioPlayer {

	private BasicPlayer player;
	private BasicController control;
	private JSlider progressSlider;

	public Mp3Player() {
		super("");
		this.player = new BasicPlayer();
		this.control = (BasicController) player;
		this.volume = 1;
	}

	public Mp3Player(String file) {
		super(file);

		this.player = new BasicPlayer();
		this.control = (BasicController) player;
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

			control.open(new File(this.getPath()));
			control.play();
			control.setGain(volume);
		} catch (BasicPlayerException bpEx) {
			Logger.getLogger(Mp3Player.class.getName()).log(Level.SEVERE, null, bpEx);
		}

	}

	@Override
	public void stop() {
		try {
			control.stop();
		} catch (BasicPlayerException bpEx) {
			Logger.getLogger(Mp3Player.class.getName()).log(Level.SEVERE, null, bpEx);
		}

	}

	@Override
	public void pause() {
		try {
			control.pause();
		} catch (BasicPlayerException bpEx) {
			Logger.getLogger(Mp3Player.class.getName()).log(Level.SEVERE, null, bpEx);
		}

	}

	@Override
	public void seek(int seconds) {
		System.out.println("Seconds: " + seconds);
		System.out.println("Frames: " + secondsToFrames(seconds));

		try {
			control.seek(seconds);
			control.setGain(this.volume);
		} catch (BasicPlayerException bpEx) {
			Logger.getLogger(Mp3Player.class.getName()).log(Level.SEVERE, null, bpEx);
		}
	}

	private long secondsToFrames(long seconds){
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
}
