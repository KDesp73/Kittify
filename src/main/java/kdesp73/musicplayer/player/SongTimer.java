/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer.player;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JSlider;
import kdesp73.musicplayer.backend.Backend;
import kdesp73.musicplayer.songs.Mp3File;

/**
 *
 * @author konstantinos
 */
public class SongTimer extends Thread {

	private boolean pause;
	private Mp3File file;
	private JSlider slider;
	private JLabel timerLabel;
	private int startAt = 0;
	public int progress = 0;

	public SongTimer(JLabel timerLabel, JSlider slider, Mp3File file) {
		super();

		this.file = file;
		this.slider = slider;
		this.timerLabel = timerLabel;
	}

	public SongTimer(JLabel timerLabel, JSlider slider, Mp3File file, int startAt) {
		super();

		this.file = file;
		this.slider = slider;
		this.timerLabel = timerLabel;
		this.startAt = startAt;
	}

	@Override
	public void run() {
		if (!pause) {
			for (int i = startAt; i < file.getDurationInSeconds(); i++) {
				try {
					Thread.sleep(1010);
				} catch (InterruptedException ex) {
					Logger.getLogger(SongTimer.class.getName()).log(Level.SEVERE, null, ex);
				}

				slider.setValue(slider.getValue() + 1);
				timerLabel.setText(Backend.secondsToMinutes(slider.getValue()));

			}
		}
	}

	public void pause() {
		this.pause = true;
		this.stop();
//		try {
//			this.join();
//		} catch (InterruptedException ex) {
//			Logger.getLogger(SongTimer.class.getName()).log(Level.SEVERE, null, ex);
//		}
	}

	public void proceed() {
		this.startAt = slider.getValue();
		this.pause = false;
		
		this.start();
	}

}
