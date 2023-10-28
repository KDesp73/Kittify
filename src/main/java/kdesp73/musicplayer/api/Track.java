/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer.api;

import java.util.ArrayList;
import kdesp73.musicplayer.api.JsonParsing;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author konstantinos
 */
public class Track {

	private String name;
	private String URL;
	private int duration;
	private String artist;
	private String artistMbid;
	private String album;
	private String cover;

	public Track() {

	}

	public Track(String json) {
		JSONObject root = (JSONObject) JSONValue.parse(json);
		JSONObject track = (JSONObject) root.get("track");

		if (track == null) {
			return;
		}

		this.name = (String) track.get("name");
		this.URL = (String) track.get("url");
		this.duration = Integer.parseInt((String) track.get("duration"));

		JSONObject artist = (JSONObject) track.get("artist");
		JSONObject albumObject = (JSONObject) track.get("album");

		if (artist != null) {
			this.artist = (String) artist.get("name");
			this.artistMbid = (String) artist.get("mbid");
		}

		if (albumObject != null) {
			this.album = (String) albumObject.get("title");
			JSONArray image = (JSONArray) albumObject.get("image");

			if (image != null) {
				this.cover = (String) ((JSONObject) image.get(3)).get("#text");
			}
		}

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

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getArtistMbid() {
		return artistMbid;
	}

	public void setArtistMbid(String artistMbid) {
		this.artistMbid = artistMbid;
	}

	@Override
	public String toString() {
		return "Track{" + "name=" + name + ", URL=" + URL + ", duration=" + duration + ", artist=" + artist + ", artistMbid=" + artistMbid + ", album=" + album + ", cover=" + cover + '}';
	}

}
