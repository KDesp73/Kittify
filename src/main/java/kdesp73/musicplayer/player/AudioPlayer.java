/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer.player;

import java.io.File;
import kdesp73.musicplayer.songs.Mp3File;

/**
 *
 * @author konstantinos
 */
public abstract class AudioPlayer {
	private Mp3File file;
	private boolean playing = false;
	private int pausedAt = 0;

	public AudioPlayer(Mp3File file) {
		this.file = file;
	}

	
	
	
	public abstract void play();

	public abstract void stop();
	
	public abstract void pause();
	
	public abstract void seek(long sample);
	
	

	
	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}

	public int getPausedAt() {
		return pausedAt;
	}

	public void setPausedAt(int pausedAt) {
		this.pausedAt = pausedAt;
	}

	public Mp3File getFile() {
		return file;
	}

	public void setFile(Mp3File file) {
		this.file = file;
	}

	
	
	
}
