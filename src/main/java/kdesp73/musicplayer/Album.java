/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer;

import java.util.ArrayList;

/**
 *
 * @author konstantinos
 */


public class Album {
	private String artist;
	private String mbid;
	private String coverURL;
	private String coverPath;
	private ArrayList<String> tracks;
	private String name;
	private String URL;
	private String content;

	public Album(){
		this.name = "Unknown Album";
	}
	
	public Album(String json) {
		
	
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getMbid() {
		return mbid;
	}

	public void setMbid(String mbid) {
		this.mbid = mbid;
	}

	public String getCoverURL() {
		return coverURL;
	}

	public void setCoverURL(String coverURL) {
		this.coverURL = coverURL;
	}

	public ArrayList<String> getTracks() {
		return tracks;
	}

	public void setTracks(ArrayList<String> tracks) {
		this.tracks = tracks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String URL) {
		this.URL = URL;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCoverPath() {
		return coverPath;
	}

	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}

	
	
	@Override
	public String toString() {
		return "Album{" + "artist=" + artist + ", mbid=" + mbid + ", coverURL=" + coverURL + ", tracks=" + tracks + ", name=" + name + ", URL=" + URL + ", content=" + content + '}';
	}
	
	
	
	
}