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
public class SongTimer extends Timer {

	private boolean pause;
	private Mp3File file;
	private JSlider slider;
	private JLabel timerLabel;
	public int progress = 0;

	public SongTimer(JLabel timerLabel, JSlider slider, Mp3File file) {
		super();

		this.file = file;
		this.slider = slider;
		this.timerLabel = timerLabel;
	}

	public void start() {
		this.schedule(new TimerTask() {
			@Override
			public void run() {
				if (!pause) {

					slider.setValue(slider.getValue() + 1);
					timerLabel.setText(Backend.secondsToMinutes(slider.getValue()));

					try {
						Thread.sleep(1000);
					} catch (InterruptedException ex) {
						Logger.getLogger(SongTimer.class.getName()).log(Level.SEVERE, null, ex);
					}
				}

			}

		}, 0, file.getDurationInSeconds());
	}

	public void start(int seconds) {
		this.schedule(new TimerTask() {
			@Override
			public void run() {
				if (!pause) {

					slider.setValue(slider.getValue() + 1);
					timerLabel.setText(Backend.secondsToMinutes(slider.getValue()));

					try {
						Thread.sleep(1000);
					} catch (InterruptedException ex) {
						Logger.getLogger(SongTimer.class.getName()).log(Level.SEVERE, null, ex);
					}

				}
			}

		}, seconds, file.getDurationInSeconds());
	}

	public void stop() {
		this.cancel();
	}

	public void pause() {
		this.pause = true;
		this.cancel();
	}

	public void resume() {
		this.pause = false;
		start(slider.getValue());
	}
}
