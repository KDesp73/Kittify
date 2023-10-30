/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer.player;

/**
 *
 * @author konstantinos
 */
public abstract class AudioPlayer {
	private String path;
	private boolean playing = false;
	private int pausedAt = 0;

	public AudioPlayer(String path) {
		this.path = path;
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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}



	
	
	
}
