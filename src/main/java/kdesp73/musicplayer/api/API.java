package kdesp73.musicplayer.api;

import ealvatag.utils.StandardCharsets;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class API {

	public API() {
	}

	public Pair<String, Integer> GET(String lastfmMethod, String tags) throws IOException, InterruptedException {
		try {
			String apiKey = getKey("https://users.iee.ihu.gr/~iee2021035/LastFmKey.txt");

			if (apiKey == null) {
				return null;
			}

			String apiUrl = "http://ws.audioscrobbler.com/2.0/?method=" + lastfmMethod + tags + "&api_key=" + apiKey + "&format=json";

			URL url = new URL(apiUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			StringBuffer response;
			try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
				String inputLine;
				response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
			} catch (IOException ex) {
//				JOptionPane.showMessageDialog(null, "It seems you are not connected to the internet. Scraping aborted", "No Internet Connection", JOptionPane.WARNING_MESSAGE);
				return null;
			}

			String jsonResponse = response.toString();

			conn.disconnect();

			return new Pair<>(jsonResponse, conn.getResponseCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String setupString(String s) {
		try {
			return URLEncoder.encode(s, StandardCharsets.UTF_8.toString()).replaceAll(Pattern.quote("."), "%2E");
		} catch (UnsupportedEncodingException ex) {
			Logger.getLogger(API.class.getName()).log(Level.SEVERE, null, ex);
		}

		return null;
	}

	public static String getKey(String urlString) {
		URL url = null;
		try {
			url = new URL(urlString);
		} catch (MalformedURLException ex) {
			return null;
		}
		InputStream inputStream = null;
		try {
			inputStream = url.openStream();
		} catch (IOException ex) {
			return null;
		}

		Scanner s = new Scanner(inputStream).useDelimiter("\\A");
		String key = s.hasNext() ? s.next() : "";
		s.close();

		return key;
	}

	public static boolean checkInternetConnection(){
		try { 
            URL url = new URL("https://www.google.com/"); 
            URLConnection connection = url.openConnection(); 
            connection.connect(); 
			return true;
        } 
        catch (Exception e) { 
			return false;
		} 
	}
	
	private static String readFile(String path) {
		BufferedReader reader;
		String data = "";

		try {
			reader = new BufferedReader(new FileReader(path));
			String line = reader.readLine();

			while (line != null) {
				data = data + line + "\n";
				line = reader.readLine();
			}

			reader.close();
			return data.trim();
		} catch (IOException e) {
		}
		return null;
	}

}
