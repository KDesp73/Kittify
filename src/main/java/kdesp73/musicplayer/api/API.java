package kdesp73.musicplayer.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class API {

	public API() {
	}

	public Pair<String, Integer> GET(String lastfmMethod, String tags) throws IOException, InterruptedException {
		tags = setupString(tags);
		try {
			String apiKey = readFile(System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/data/api_key.txt");
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
			} catch(IOException ex) {
				JOptionPane.showMessageDialog(null, "It seems you are not connected to the internet. Scraping aborted", "No Internet Connection", JOptionPane.WARNING_MESSAGE);
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

	private String setupString(String s) {
		s = s.replaceAll(Pattern.quote("."), " ");
		s = s.replaceAll(" ", "%20");
		
		return s;
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
