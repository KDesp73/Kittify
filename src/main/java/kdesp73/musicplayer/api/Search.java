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
public class Search {

	private ArrayList<SearchTrack> tracks = new ArrayList<>();

	public Search(String json) {
		JSONObject root = (JSONObject) JSONValue.parse(json);
		JSONObject results = (JSONObject) root.get("results");
		JSONObject trackmatches = (JSONObject) results.get("trackmatches");
		JSONArray track = (JSONArray) trackmatches.get("track");

		for (Object o : track) {
			tracks.add(new SearchTrack(((JSONObject) o).toString()));
		}
	}

	public ArrayList<SearchTrack> getTracks() {
		return tracks;
	}
}
