/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer.api;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author konstantinos
 */
public class Album {

	private String artist;
	private String mbid;
	private String coverURL;
	private ArrayList<String> tracks = new ArrayList<>();
	private String name;
	private String URL;
	private String content;

	public Album() {
		this.name = "Unknown Album";
	}

	public Album(String json) {
		JSONObject root = (JSONObject) JSONValue.parse(json);

		JSONObject album = (JSONObject) root.get("album");

		if (album == null) {
			return;
		}

		this.artist = (String) album.get("artist");
		this.mbid = (String) album.get("mbid");

		JSONArray image = (JSONArray) album.get("image");

		if (image != null) {
			this.coverURL = (String) ((JSONObject) image.get(4)).get("#text");
		}

		JSONObject tracksObject = (JSONObject) album.get("tracks");

		if (tracksObject != null) {
			Object track = tracksObject.get("track");

			if (track instanceof JSONArray) {
				for (int i = 0; i < ((JSONArray)track).size(); i++) {
					JSONObject o = (JSONObject) ((JSONArray)track).get(i);
					this.tracks.add((String) o.get("name"));
				}
			} else if(track instanceof JSONObject){
				this.tracks.add((String) ((JSONObject) track).get("name"));
			}

		}

		this.name = (String) album.get("name");

		JSONObject wiki = (JSONObject) album.get("wiki");

		if (wiki != null) {
			this.content = (String) wiki.get("content");
		}

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

	@Override
	public String toString() {
		return "Album{" + "\n\tartist=" + artist + ", \n\tmbid=" + mbid + ", \n\tcoverURL=" + coverURL + ", \n\ttracks=" + tracks + ", \n\tname=" + name + ", \n\tURL=" + URL + ", \n\tcontent=" + content + "\n}";
	}

}
