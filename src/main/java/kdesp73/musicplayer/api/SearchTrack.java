/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author konstantinos
 */
public class SearchTrack {

	private String name;
	private String artist;
	private String url;
	private String image;

	public SearchTrack(String json) {
		JSONObject root = (JSONObject) JSONValue.parse(json);

		this.name = (String) root.get("name");
		this.artist = (String) root.get("artist");
		this.url = (String) root.get("url");

		JSONArray image = (JSONArray) root.get("image");

		this.image = (String) ((JSONObject) image.get(image.size()-1)).get("#text");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "SearchTrack{" + "name=" + name + ", artist=" + artist + ", url=" + url + ", image=" + image + '}';
	}
	
	

}
