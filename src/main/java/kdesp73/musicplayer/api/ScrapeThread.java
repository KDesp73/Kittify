/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer.api;

import javax.swing.JOptionPane;
import kdesp73.musicplayer.songs.Mp3File;
import kdesp73.musicplayer.songs.SongsList;

/**
 *
 * @author konstantinos
 */
public class ScrapeThread extends Thread {

	private SongsList list;

	public ScrapeThread(SongsList list) {
		super();

		this.list = list;
	}

	@Override
	public void run() {
		for(Mp3File file : list.getSongs()){
			file.selfScrape();
		}
		
		JOptionPane.showMessageDialog(null, "Scrape Completed!");
	}
}
