/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import kdesp73.musicplayer.api.JsonParsing;
import org.json.simple.JSONValue;

/**
 *
 * @author konstantinos
 */
public class Artist {
	private String name;
	private String mbid;
	private String URL;
	private String image;
	private ArrayList<String> tags = new ArrayList<>();
	private String content;

	public Artist(){
		name = "Unknown Artist";
	}
	
	public Artist(String json) {
		JSONObject root = (JSONObject) JSONValue.parse(json);
		
		JSONObject artist = (JSONObject) JsonParsing.getJsonValue(json, "artist");
		
		this.name = (String) artist.get("name");
		this.mbid = (String) artist.get("mbid");
		this.URL = (String) artist.get("url");
		
		JSONArray imageArray = (JSONArray) artist.get("image");
		
		JSONObject megaImage = (JSONObject) JSONValue.parse(imageArray.get(4).toString());
		this.image = (String) megaImage.get("#text");
		
		JSONArray tagsArray = (JSONArray) JsonParsing.getJsonValue(artist.toString(), "tags", "tag");
	
		for(int i = 0; i < tagsArray.size(); i++){
			tags.add((String) ((JSONObject) JSONValue.parse(tagsArray.get(i).toString())).get("name"));
		}
		
		this.content = (String) JsonParsing.getJsonValue(artist.toString(), "bio", "content");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMbid() {
		return mbid;
	}

	public void setMbid(String mbid) {
		this.mbid = mbid;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String URL) {
		this.URL = URL;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Artist{" + "name=" + name + ", mbid=" + mbid + ", URL=" + URL + ", image=" + image + ", tags=" + tags + ", content=" + content + '}';
	}
	
	
	

}