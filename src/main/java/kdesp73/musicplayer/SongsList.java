/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import kdesp73.databridge.connections.DatabaseConnection;
import kdesp73.databridge.helpers.QueryBuilder;
import kdesp73.musicplayer.api.ScrapeThread;
import kdesp73.musicplayer.db.Database;
import kdesp73.musicplayer.files.FileOperations;

/**
 *
 * @author konstantinos
 */
public class SongsList {

	public static final int SORTING_ERROR = -999;
	private HashSet<String> paths = new HashSet<>();

	private ArrayList<Mp3File> list = new ArrayList<>();

	public SongsList(SongsList copy) {
		this.list = copy.list;
	}

	// Import Algorithm                                                                       //
	// ------------------------------------------------------------------------------- //
	// 1. Load all songs from database                                             //
	// 2. Add them to list                                                                    //
	// 3. Load all files from directories                                               //
	// 4. Add file only if path doesn't already exist                           //
	// 5. Add everything in database if it doesn't already exist       //
	public SongsList(List<String> paths) {
		list.addAll(loadSongsFromDB());

		ArrayList<Mp3File> newFiles = loadFiles(paths);

		for (Mp3File f : newFiles) {
			if (!this.paths.contains(f.getAbsolutePath())) {
				list.add(f);
			}
		}

		addToDatabase();
	}

	// Import Algorithm                                                                       //
	// ------------------------------------------------------------------------------- //
	// 1. Load all songs from database                                             //
	// 2. Add them to list                                                                    //
	// 3. Load all files from directories and additional files              //
	// 4. Add file only if path doesn't already exist                           //
	// 5. Add everything in database if it doesn't already exist       //
	public SongsList(List<String> paths, List<String> files) {
		list.addAll(loadSongsFromDB());

		ArrayList<Mp3File> newFiles = loadFiles(paths, files);

		for (Mp3File f : newFiles) {
			if (!this.paths.contains(f.getAbsolutePath())) {
				System.out.println("New file: " + f.getAbsolutePath());
				list.add(f);
			}
		}

		addToDatabase();
	}

	private ArrayList<Mp3File> loadFiles(List<String> paths) {
		ArrayList<Mp3File> tempList = new ArrayList<>();
		for (String path : paths) {
			try {
				List<String> dirs = FileOperations.findFiles(path);
				for (String dir : dirs) {
					tempList.add(new Mp3File(dir));
				}

			} catch (IOException ex) {
				Logger.getLogger(SongsList.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		return tempList;
	}

	private ArrayList<Mp3File> loadFiles(List<String> paths, List<String> files) {
		ArrayList<Mp3File> tempList = new ArrayList<>();
		for (String path : paths) {
			try {
				List<String> dirs = FileOperations.findFiles(path);
				for (String dir : dirs) {
					tempList.add(new Mp3File(dir));
				}
			} catch (IOException ex) {
				Logger.getLogger(SongsList.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		for (String file : files) {
			tempList.add(new Mp3File(file));
		}

		return tempList;
	}

	private ArrayList<Mp3File> loadSongsFromDB() {
		ArrayList dbSongs = new ArrayList<>();

		DatabaseConnection db = Database.connection();

		ResultSet rs = db.executeQuery(new QueryBuilder().select().from("Songs").build());

		try {
			while (rs.next()) {
				Mp3File file = new Mp3File(rs.getString("title"), rs.getString("artist"), rs.getString("album"), rs.getString("path"));

				dbSongs.add(file);
				paths.add(rs.getString("path"));
			}
		} catch (SQLException ex) {
			Logger.getLogger(SongsList.class.getName()).log(Level.SEVERE, null, ex);
		}

		return dbSongs;
	}

	private void addToDatabase() {
		DatabaseConnection db = Database.connection();
		QueryBuilder builder = new QueryBuilder();
		for (Mp3File file : list) {
			boolean dontAdd = false;
			ResultSet rs = db.executeQuery(builder.select("path").from("Songs").build());
			try {
				while (rs.next()) {
					if (rs.getString(1).equals(file.getAbsolutePath())) {
						dontAdd = true;
						break;
					}
				}
			} catch (SQLException ex) {
				Logger.getLogger(SongsList.class.getName()).log(Level.SEVERE, null, ex);
			}

			if (!dontAdd) {
				db.executeUpdate(builder.insertInto("Songs").columns("path", "title", "scraped").values(file.getAbsolutePath().replaceAll(Pattern.quote("\'"), "\'\'"), file.getTrack().getName().replaceAll(Pattern.quote("\'"), "\'\'"), false).build());
			}
		}
		db.close();
	}

	public void addSong(Mp3File file) {
		list.add(file);
	}

	public ArrayList<Mp3File> getSongs() {
		return list;
	}

	public void sortByName() {
		list.sort((o1, o2) -> {
			if (o1.getName() == null || o2.getName() == null) {
				return SORTING_ERROR;
			}
			return o1.getTrack().getName().compareTo(o2.getTrack().getName());
		});
	}

	public void sortByArtist() {
		list.sort((o1, o2) -> {
			if (o1.getTrack().getArtist() == null || o2.getTrack().getArtist() == null) {
				return SORTING_ERROR;
			}
			return o1.getTrack().getArtist().compareTo(o2.getTrack().getArtist());
		});
	}

	public void sortByAlbum() {
		list.sort((o1, o2) -> {
			if (o1.getTrack().getAlbum() == null || o2.getTrack().getAlbum() == null) {
				return SORTING_ERROR;
			}
			return o1.getTrack().getAlbum().compareTo(o2.getTrack().getAlbum());
		});
	}

	public void sortByTime() {
		list.sort((o1, o2) -> o2.getTimeOfImport().compareTo(o1.getTimeOfImport()));
	}
	
	public int searchSong(String search){
		// TODO: REPLACE BRUTE FORCE AT SOME POINT
		for (int i = 0; i < list.size(); i++) {
			Mp3File file = list.get(i);
			if(file.getTrack().getName().contains(search)){
				return i;
			}
		}	
		return -1;
	}
	
	public void scrapeSongs(){
		new ScrapeThread(this).start();
	}
	
	@Override
	public String toString() {
		return "SongsList{" + "list=" + list + '}';
	}

}
