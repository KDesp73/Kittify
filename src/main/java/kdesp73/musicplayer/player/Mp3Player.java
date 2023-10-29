/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer.player;

import kdesp73.musicplayer.songs.Mp3File;

/**
 *
 * @author konstantinos
 */
public class Mp3Player extends AudioPlayer {
	
	public Mp3Player(Mp3File file) {
		super(file);
	}
	
	

	@Override
	public void play() {
		System.err.println("Not implemented yet.");
	}

	@Override
	public void stop() {
		System.err.println("Not implemented yet.");
	}

	@Override
	public void pause() {
		System.err.println("Not implemented yet.");
	}

	@Override
	public void seek(long sample) {
		System.err.println("Not implemented yet.");
	}
}
