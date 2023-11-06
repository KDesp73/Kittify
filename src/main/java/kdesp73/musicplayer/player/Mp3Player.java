/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer.player;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;
import kdesp73.musicplayer.gui.MainFrame;
import kdesp73.musicplayer.songs.Mp3File;

/**
 *
 * @author konstantinos
 */
public class Mp3Player extends AudioPlayer implements BasicPlayerListener {

	private BasicPlayer player;
	private BasicController controller;
	private JSlider progressSlider;
	private JLabel timerLabel;
	public SongTimer timer;

	public Mp3Player() {
		super(0, null);
		this.player = new BasicPlayer();
		this.volume = 1;
		player.addBasicPlayerListener(this);
		this.timer = new SongTimer(timerLabel, progressSlider, new Mp3File(this.playlist.get(playingIndex)));
	}

	public Mp3Player(int index, ArrayList<String> playlist) {
		super(index, playlist);

		this.player = new BasicPlayer();
		this.volume = 1;
		player.addBasicPlayerListener(this);
		this.timer = new SongTimer(timerLabel, progressSlider, new Mp3File(this.playlist.get(playingIndex)));
	}

	public void setFrame(JFrame frame) {
		if (frame instanceof MainFrame mf) {
			this.progressSlider = mf.getSlider();
			this.timerLabel = mf.getTimeLabel();
		}
	}

	@Override
	public void play() {
		this.timer = new SongTimer(timerLabel, progressSlider, new Mp3File(this.playlist.get(playingIndex)));

		try {

			if (player.getStatus() == BasicPlayer.PAUSED) {
				player.resume();
				timer.proceed();
				return;
			}
			if (player.getStatus() != BasicPlayer.STOPPED) {
				player.stop();
			}

			player.open(new File(playlist.get(playingIndex)));

			System.out.println("Playing \"" + playlist.get(playingIndex) + "\"");

			player.play();
			timer.start();
			player.setGain(volume);
		} catch (BasicPlayerException bpEx) {
			Logger.getLogger(Mp3Player.class.getName()).log(Level.SEVERE, null, bpEx);
		}
	}

	@Override
	public void play(int index) {
		this.playingIndex = index;
		this.timer = new SongTimer(timerLabel, progressSlider, new Mp3File(this.playlist.get(playingIndex)));
		try {

			if (player.getStatus() == BasicPlayer.PAUSED) {
				player.resume();
				timer.proceed();
				return;
			}
			if (player.getStatus() != BasicPlayer.STOPPED) {
				player.stop();
			}

			player.open(new File(playlist.get(index)));

			System.out.println("Playing \"" + playlist.get(index) + "\"");

			player.play();
			timer.start();
			player.setGain(volume);
		} catch (BasicPlayerException bpEx) {
			Logger.getLogger(Mp3Player.class.getName()).log(Level.SEVERE, null, bpEx);
		}
	}

	@Override
	public void stop() {
		try {
			player.stop();
			timer.stop();
		} catch (BasicPlayerException bpEx) {
			Logger.getLogger(Mp3Player.class.getName()).log(Level.SEVERE, null, bpEx);
		}
	}
	
	public void resume(){
		try {
			player.resume();
		} catch (BasicPlayerException ex) {
			Logger.getLogger(Mp3Player.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void pause() {
		try {
			player.pause();
			timer.pause();
		} catch (BasicPlayerException bpEx) {
			Logger.getLogger(Mp3Player.class.getName()).log(Level.SEVERE, null, bpEx);
		}

	}

	@Override
	public void seek(int seconds) {
		int frames = (int) secondsToFrames(seconds);
		System.out.println("Seconds: " + seconds);
		System.out.println("Frames: " + frames);

		try {
			player.seek(frames);
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}

	private long secondsToFrames(long seconds) {
		/*
		double sampleRate = audioFile.getSampleRate();
size_t offsetInSeconds = 2;
size_t sampleToRead = sampleRate * offsetInSeconds * audioFile.getChannelCount();
AudioSample sample = audioFile.getSampleAt(sampleToRead);
		 */
		
		Mp3File file = new Mp3File(this.getCurrentSong());
		int sampleRate = (int) file.getMetadata().get("sample-rate");
		System.out.println("Channel Count: " + (int) file.getMetadata().get("channel-count"));
		int sampleToRead = (int) (sampleRate * seconds * (int) file.getMetadata().get("channel-count"));
		System.out.println("Sample to Read: " + sampleToRead);
		
		return sampleRate * seconds / ( 2 * (int) file.getMetadata().get("channel-count") );
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

	@Override
	public void opened(Object o, Map map) {
		System.out.println("Song opened for playback.");
	}

	@Override
	public void progress(int i, long l, byte[] bytes, Map map) {
//		double duration = (double) map.get("duration");
//		double currentProgress = (l / (duration * 1000.0)) * 100.0;
//		progressSlider.setValue((int) currentProgress);
	}

	@Override
	public void stateUpdated(BasicPlayerEvent bpe) {
//		if (bpe.getCode() == BasicPlayerEvent.STOPPED /* && duration == timePassed*/) {
//			// IF REPEAT
//			// IF SHUFFLE
//
//			
//			
//			if (this.playingIndex < playlist.size()) {
//				next();
//			} else {
//				System.out.println("End of playlist.");
//			}
//		}
	}

	@Override
	public void setController(BasicController bc) {
		controller = bc;
	}
	
	public void setPlaylist(ArrayList<String> playlist){
		this.playlist.clear();
		this.playlist.addAll(playlist);
	}
}
