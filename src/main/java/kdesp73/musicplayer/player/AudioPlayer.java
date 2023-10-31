/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer.player;

import java.util.ArrayList;

/**
 *
 * @author konstantinos
 */
public abstract class AudioPlayer {
	public int pausedAt = 0;
	public double volume;
	public ArrayList<String> playlist;
	public int playingIndex;
	
	public AudioPlayer(int playingIndex, ArrayList<String> playlist) {
		this.playlist = playlist;
		this.playingIndex = playingIndex;
	}

	public abstract void play();
	public abstract void play(int index);

	public abstract void stop();
	
	public abstract void pause();
	
	public abstract void seek(int seconds);
	
	public abstract void setVolume(int value, int maxValue);
	
	public abstract void next();
	
	public abstract void prev();
	
	public abstract boolean isPlaying();
	
	public abstract void setSong(int index);

	public int getPausedAt() {
		return pausedAt;
	}

	public void setPausedAt(int pausedAt) {
		this.pausedAt = pausedAt;
	}

	public String getCurrentSong(){
		return playlist.get(playingIndex);
	}

	public double getVolume() {
		return volume;
	}
}
