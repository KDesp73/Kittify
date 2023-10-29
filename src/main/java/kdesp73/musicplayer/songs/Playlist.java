/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer.songs;

import java.util.ArrayList;
import java.util.LinkedList;
import kdesp73.musicplayer.db.Queries;

/**
 *
 * @author konstantinos
 */
public class Playlist {

	private LinkedList<Mp3File> songs = new LinkedList<>();
	public String name;

	public Playlist(String name) {
		this.name = name;
	}

	public Playlist(SongsList list) {
		this.name = "Global";

		songs.addAll(list.getSongs());
	}

	public void addSong(Mp3File song) {
		songs.add(song);
	}

	public void removeSong(Mp3File song) {
		songs.remove(song);
	}

	public void removeSong(int index) {
		songs.remove(index);
	}

	public Mp3File getSong(int index) {
		return songs.get(index);
	}

	public void loadFromDB() {
		ArrayList<Mp3File> list = Queries.selectPlaylistSongs(name);

		songs.addAll(list);
	}

	public void saveToDB() {
		for (Mp3File song : songs) {
			if (!Queries.playlistSongExists(name, song)) {
				Queries.insertPlayListSong(name, song);
			}
		}
	}

}
