/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import kdesp73.databridge.connections.DatabaseConnection;
import kdesp73.databridge.helpers.QueryBuilder;
import kdesp73.musicplayer.Mp3File;
import kdesp73.musicplayer.SongsList;
import kdesp73.musicplayer.api.Album;
import kdesp73.musicplayer.api.Artist;
import kdesp73.musicplayer.gui.MainFrame;

/**
 *
 * @author konstantinos
 */
public class Queries {

	public static List<String> getDirectories() {
		DatabaseConnection db = Database.connection();
		QueryBuilder builder = new QueryBuilder();

		ResultSet rs = db.executeQuery(builder.select("directory").from("Directories").build());
		List<String> paths = new ArrayList<>();

		try {
			while (rs.next()) {
				paths.add(rs.getString("directory"));
			}
		} catch (SQLException ex) {
			Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
		}

		return paths;
	}

	public static List<String> getFiles() {
		DatabaseConnection db = Database.connection();
		QueryBuilder builder = new QueryBuilder();

		ResultSet rs = db.executeQuery(builder.select("filepath").from("Files").build());
		List<String> paths = new ArrayList<>();

		try {
			while (rs.next()) {
				paths.add(rs.getString("filepath"));
			}
		} catch (SQLException ex) {
			Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
		}

		return paths;
	}

	public static void clearDatabase() {
		DatabaseConnection db = Database.connection();
		QueryBuilder builder = new QueryBuilder();

		db.executeUpdate(builder.deleteFrom("Directories").build());
		db.executeUpdate(builder.deleteFrom("Files").build());
		db.executeUpdate(builder.deleteFrom("Songs").build());

		db.close();

		System.out.println("Database cleared");
	}

	public static String getSortBy() {
		DatabaseConnection db = Database.connection();
		ResultSet rs = db.executeQuery(new QueryBuilder().select("sort_by").from("Settings").build());
		String sort_by = "";
		try {
			rs.next();
			sort_by = rs.getString(1);
		} catch (SQLException ex) {
			Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
		}

		db.close();

		return sort_by;
	}

	public static void updateSortBy(String sort_by) {
		DatabaseConnection db = Database.connection();

		db.executeUpdate(new QueryBuilder().update("Settings").set("sort_by", sort_by).where("key = 1").build());

		db.close();
	}

	public static int getLastPlayed() {
		DatabaseConnection db = Database.connection();
		ResultSet rs = db.executeQuery(new QueryBuilder().select("last_played").from("Settings").build());
		int sort_by = 0;
		try {
			rs.next();
			sort_by = rs.getInt(1);
		} catch (SQLException ex) {
			Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
		}

		db.close();

		return sort_by;
	}

	public static void updateLastPlayed(int index) {
		DatabaseConnection db = Database.connection();
		db.executeUpdate(new QueryBuilder().update("Settings").set("last_played", index).where("key = 1").build());
		db.close();

	}

	public static void updateSong(Mp3File file) {
		DatabaseConnection db = Database.connection();

		String query = "UPDATE Songs "
				+ "SET title = \'" + Utils.replaceQuotes(file.getTrack().getName())
				+ "\', artist = \'" + Utils.replaceQuotes(file.getTrack().getArtist())
				+ "\', album = \'" + Utils.replaceQuotes(file.getTrack().getAlbum())
				+ "\', image_path = \'" + Utils.replaceQuotes(file.getCoverPath())
				+ "\' WHERE path = \'" + Utils.replaceQuotes(file.getAbsolutePath()) + "\'";

		db.executeUpdate(query);
		db.close();
	}

	public static void insertAlbum(Album album) {
		DatabaseConnection db = Database.connection();

		ResultSet rs = db.executeQuery("SELECT EXISTS(SELECT 1 FROM Albums WHERE mbid= \'" + Utils.replaceQuotes(album.getMbid()) + "\')");

		try {
			rs.next();
			if (rs.getInt(1) == 1) {
				System.out.println("Album exists in DB");
				db.close();
				return;
			}
		} catch (SQLException ex) {
			Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
		}

		String query = "INSERT INTO Albums "
				+ "(artist, mbid, cover_url, name, url, content, tracks) "
				+ "VALUES (\'" + Utils.replaceQuotes(album.getArtist()) + "\', "
				+ "\'" + Utils.replaceQuotes(album.getMbid()) + "\', "
				+ "\'" + Utils.replaceQuotes(album.getCoverURL()) + "\', "
				+ "\'" + Utils.replaceQuotes(album.getName()) + "\', "
				+ "\'" + Utils.replaceQuotes(album.getURL()) + "\', "
				+ "\'" + Utils.replaceQuotes(album.getContent()) + "\', "
				+ "\'" + Utils.replaceQuotes(Utils.arrayToList(album.getTracks())) + "\')";

		db.executeUpdate(query);
		db.close();
	}

	public static Album selectAlbum(String name) {
		DatabaseConnection db = Database.connection();
		ResultSet rs = db.executeQuery(new QueryBuilder().select().from("Albums").where("name = \'" + Utils.replaceQuotes(name) + "\'").build());

		if (rs == null) {
			return null;
		}

		Album album = new Album();
		try {
			if (!rs.next()) {
				return null;
			}

			album.setArtist(rs.getString("artist"));
			album.setMbid(rs.getString("mbid"));
			album.setCoverURL(rs.getString("cover_url"));
			album.setName(rs.getString("name"));
			album.setURL(rs.getString("url"));
			album.setContent(rs.getString("content"));
			album.setTracks(Utils.parseList(rs.getString("tracks")));

		} catch (SQLException ex) {
			Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
		}
		db.close();

		return album;
	}

	public static Album selectAlbum(String name, String artist) {
		DatabaseConnection db = Database.connection();
		ResultSet rs = db.executeQuery(new QueryBuilder().select().from("Albums").where("name = \'" + Utils.replaceQuotes(name) + "\' AND artist = \'" + Utils.replaceQuotes(artist) + "\'").build());

		if (rs == null) {
			return null;
		}

		Album album = new Album();
		try {
			if (!rs.next()) {
				return null;
			}

			album.setArtist(rs.getString("artist"));
			album.setMbid(rs.getString("mbid"));
			album.setCoverURL(rs.getString("cover_url"));
			album.setName(rs.getString("name"));
			album.setURL(rs.getString("url"));
			album.setContent(rs.getString("content"));
			album.setTracks(Utils.parseList(rs.getString("tracks")));

		} catch (SQLException ex) {
			Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
		}

		db.close();
		return album;
	}

	public static void insertArtist(Artist artist) {
		DatabaseConnection db = Database.connection();

		ResultSet rs = db.executeQuery("SELECT EXISTS(SELECT 1 FROM Artists WHERE mbid= \'" + Utils.replaceQuotes(artist.getMbid()) + "\')");

		try {
			rs.next();
			if (rs.getInt(1) == 1) {
				System.out.println("Artist exists in DB");
				db.close();
				return;
			}
		} catch (SQLException ex) {
			Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
		}

		String query = "INSERT INTO Artists "
				+ "(name, mbid, url, image, tags, content) "
				+ "VALUES (\'" + Utils.replaceQuotes(artist.getName()) + "\', "
				+ "\'" + Utils.replaceQuotes(artist.getMbid()) + "\', "
				+ "\'" + Utils.replaceQuotes(artist.getURL()) + "\', "
				+ "\'" + Utils.replaceQuotes(artist.getImage()) + "\', "
				+ "\'" + Utils.replaceQuotes(Utils.arrayToList(artist.getTags())) + "\', "
				+ "\'" + Utils.replaceQuotes(artist.getContent()) + "\')";

		db.executeUpdate(query);
		db.close();
	}

	public static Artist selectArtist(String name) {
		DatabaseConnection db = Database.connection();
		ResultSet rs = db.executeQuery(new QueryBuilder().select().from("Artists").where("name = \'" + Utils.replaceQuotes(name) + "\'").build());

		if (rs == null) {
			return null;
		}

		Artist artist = new Artist();
		try {
			if (!rs.next()) {
				return null;
			}

			artist.setName(rs.getString("name"));
			artist.setMbid(rs.getString("mbid"));
			artist.setURL(rs.getString("url"));
			artist.setImage(rs.getString("image"));
			artist.setTags(Utils.parseList(rs.getString("tags")));
			artist.setContent(rs.getString("content"));

		} catch (SQLException ex) {
			Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
		}

		db.close();
		return artist;
	}

	public static String selectAlbumCover(String album, String artist) {
		DatabaseConnection db = Database.connection();

		ResultSet rs = db.executeQuery(new QueryBuilder().select("cover_url").from("Albums").where("name = \'" + Utils.replaceQuotes(album) + "\' AND artist = \'" + Utils.replaceQuotes(artist) + "\'").build());
		String cover = null;

		if (rs == null) {
			return null;
		}

		try {
			if (!rs.next()) {
				return null;
			}
			cover = rs.getString(1);
		} catch (SQLException ex) {
			Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
		}
		db.close();

		return cover;
	}
	
	public static boolean selectScraped(String path){
		DatabaseConnection db = Database.connection();
		
		ResultSet rs = db.executeQuery(new QueryBuilder().select("scraped").from("Songs").where("path = \'" + Utils.replaceQuotes(path) + "\'").build());
		
		boolean scraped = false;
		try {
			rs.next();
			scraped = rs.getBoolean(1);
		} catch (SQLException ex) {
			Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		db.close();
		return scraped;
	}
	
	public static void updateScraped(boolean scraped, String path){
		DatabaseConnection db = Database.connection();
		
		db.executeUpdate(new QueryBuilder().update("Songs").set("scraped", scraped).build());
		
		db.close();
	}

	private class Utils {

		private static final String DELIMITER = ", ";

		public static String replaceQuotes(String str) {
			if (str == null) {
				return "";
			}
			return str.replaceAll(Pattern.quote("\'"), "\'\'");
		}

		public static ArrayList<String> parseList(String list) {
			String[] out = list.split(DELIMITER);

			return new ArrayList<>(Arrays.asList(out));
		}

		public static String arrayToList(ArrayList<String> array) {
			return String.join(DELIMITER, array);
		}
	}

}
