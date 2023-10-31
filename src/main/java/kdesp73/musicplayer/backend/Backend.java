/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer.backend;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import kdesp73.musicplayer.api.API;
import kdesp73.musicplayer.api.Album;
import kdesp73.musicplayer.api.Artist;
import kdesp73.musicplayer.api.LastFmMethods;
import kdesp73.musicplayer.api.Pair;
import kdesp73.musicplayer.api.Search;
import kdesp73.musicplayer.api.SearchTrack;
import kdesp73.musicplayer.api.Track;
import kdesp73.musicplayer.db.Queries;
import kdesp73.musicplayer.files.FileOperations;
import kdesp73.musicplayer.gui.EditSongInfo;
import kdesp73.musicplayer.gui.GUIMethods;
import kdesp73.musicplayer.gui.MainFrame;
import kdesp73.musicplayer.player.Mp3Player;
import kdesp73.musicplayer.songs.Mp3File;
import kdesp73.musicplayer.songs.SongsList;

/**
 *
 * @author konstantinos
 */
public class Backend {

	private static MainFrame mainFrame;

	public static void setMainFrame(MainFrame mainFrame) {
		Backend.mainFrame = mainFrame;
		UIFunctionality.setMainFrame(mainFrame);
	}

	public static void setup(JFrame frame) {
		if (frame instanceof MainFrame) {

			mainFrame.setLocationRelativeTo(null);
			mainFrame.getRootPane().requestFocus();

			mainFrame.addWindowListener(new WindowAdapter() {

				@Override
				public void windowClosing(WindowEvent e) {
					Queries.updateLastPlayed(mainFrame.currentSong.getAbsolutePath());
				}

			});

			updateSongs(frame);
			if (!mainFrame.list.getSongs().isEmpty()) {
				sort(mainFrame);
				mainFrame.currentIndex = (mainFrame.list.searchSongPath(Queries.selectLastPlayed()) < 0) ? 0 : mainFrame.list.searchSongPath(Queries.selectLastPlayed());
				mainFrame.currentSong = mainFrame.list.getSongs().get(mainFrame.currentIndex);
			} else {
				setDefaultSongInfo(frame);
			}

			mainFrame.player = new Mp3Player(mainFrame.currentIndex, mainFrame.list.getPaths());
			((Mp3Player) mainFrame.player).setFrame(mainFrame);

			selectSong(mainFrame, mainFrame.currentIndex);

			mainFrame.getSongsList().setFixedCellHeight(35);

			GUIMethods.loadImage(mainFrame.getOptionsLabel(), mainFrame.getProject_path() + "/assets/ellipsis-vertical-solid-small.png");

			mainFrame.getSortComboBox().setSelectedItem(Queries.selectSortBy());

			mainFrame.scrapeAtStart = Queries.selectScrapeAtStart();
			mainFrame.getScrapeAtStartMenuItem().setSelected(mainFrame.scrapeAtStart);

			if (mainFrame.scrapeAtStart) {
				mainFrame.list.scrapeSongs();
			}

		}
	}

	public static void addExtensions() {
		FileOperations.acceptedExtensions.add("mp3");
		FileOperations.acceptedExtensions.add("wav");
		FileOperations.acceptedExtensions.add("mpeg");
	}

	public static void updateSongs(JFrame frame) {
		addExtensions();
		initList(frame);

		sort(frame);
		refreshList(frame);
	}

	public static String sort(JFrame frame) {
		if (frame instanceof MainFrame) {

			int selected = mainFrame.getSortComboBox().getSelectedIndex();

			switch (selected) {
				case 0 -> {
					mainFrame.list.sortByName();
					refreshList(mainFrame, "Name");
					return "Name";
				}
				case 1 -> {
					mainFrame.list.sortByArtist();
					refreshList(mainFrame, "Artist");
					return "Artist";
				}
				case 2 -> {
					mainFrame.list.sortByAlbum();
					refreshList(mainFrame, "Album");
					return "Album";
				}
				default ->
					throw new AssertionError();
			}
		}

		if (mainFrame.player != null) {
			mainFrame.player.playlist = mainFrame.list.getPaths();
		}

		return null;
	}

	public static void initList(JFrame frame) {
		if (frame instanceof MainFrame) {

			try {
				mainFrame.list = new SongsList(Queries.selectDirectories(), Queries.selectFiles());
			} catch (java.lang.StringIndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		}
	}

	public static void refreshList(JFrame frame) {
		if (frame instanceof MainFrame) {
			String sortBy = Queries.selectSortBy();
			DefaultListModel listModel = new DefaultListModel();
			for (Mp3File song : mainFrame.list.getSongs()) {
				String listText = "";
				switch (sortBy) {
					case "Name" ->
						listText = (song.getTrack().getArtist() == null || song.getTrack().getArtist().isBlank())
								? song.getTrack().getName()
								: song.getTrack().getName() + " - " + song.getTrack().getArtist();
					case "Artist" ->
						listText = song.getTrack().getArtist() + " - " + song.getTrack().getName();
					case "Album" ->
						listText = song.getTrack().getAlbum() + " - " + song.getTrack().getName();

				}
				listModel.addElement(listText);
			}
			mainFrame.getSongsList().setModel(listModel);
		}
	}

	public static void refreshList(JFrame frame, String sortBy) {
		if (frame instanceof MainFrame) {

			DefaultListModel listModel = new DefaultListModel();
			for (Mp3File song : mainFrame.list.getSongs()) {
				switch (sortBy) {
					case "Name" ->
						listModel.addElement(song.getTrack().getName() + " - " + song.getTrack().getArtist());
					case "Artist" ->
						listModel.addElement(song.getTrack().getArtist() + " - " + song.getTrack().getName());
					case "Album" ->
						listModel.addElement(song.getTrack().getAlbum() + " - " + song.getTrack().getName());

				}
			}
			mainFrame.getSongsList().setModel(listModel);
		}
	}

	public static String secondsToMinutes(int seconds) {
		int minutes = seconds / 60;
		seconds %= 60;

		return "" + ((minutes < 10) ? "0" + minutes : minutes) + ":" + ((seconds < 10) ? "0" + seconds : seconds);
	}

	public static void selectSong(JFrame frame, int index) {
		if (frame instanceof MainFrame) {
			if (mainFrame.list.getSongs().isEmpty()) {
				return;
			}

			updateSongInfo(frame, index);

			mainFrame.currentSong = mainFrame.list.getSongs().get(index);
			mainFrame.currentIndex = index;

			mainFrame.player.setSong(index);

			mainFrame.getSongsList().ensureIndexIsVisible(index);
			mainFrame.getSongsList().setSelectedIndex(index);

			mainFrame.getSlider().setMaximum((int) (mainFrame.currentSong.getDurationInSeconds() + 1));
			mainFrame.getSlider().setValue(0);
			mainFrame.getTimeLabel().setText("00:00");
		}

	}

	public static void setDefaultSongInfo(JFrame frame) {
		if (frame instanceof MainFrame) {
			mainFrame.getTrackLabel().setText("Title");
			mainFrame.getArtistLabel().setText("Artist");
			mainFrame.getAlbumLabel().setText("Album");
			mainFrame.getTimeLabel().setText("00:00");

			GUIMethods.loadImage(mainFrame.getAlbumImageLabel(), mainFrame.getProject_path() + "/assets/album-image-placeholder.png");
		}

	}

	public static void updateSongInfo(JFrame frame, int index) {
		if (frame instanceof MainFrame) {
			String path = mainFrame.list.getSongs().get(index).getAbsolutePath();
			String title = mainFrame.list.getSongs().get(index).getTrack().getName();
			String artist = mainFrame.list.getSongs().get(index).getTrack().getArtist();
			String album = mainFrame.list.getSongs().get(index).getTrack().getAlbum();
			String cover = mainFrame.list.getSongs().get(index).getCoverPath();

			mainFrame.getTrackLabel().setText(title);
			mainFrame.getArtistLabel().setText((artist == null) ? "Unknown Artist" : artist);
			mainFrame.getAlbumLabel().setText((album == null) ? "Unknown Album" : album);

			Queries.updateSong(mainFrame.list.getSongs().get(index));

			if (cover != null && cover.isBlank()) {
				Queries.updateLocalCoverPath(album, artist, cover);
			}

			// If album has been scraped load album cover
			if (album != null && !album.isBlank() && !album.equals("Unknown Album")) {
				String coverURL = Queries.selectAlbumCover(album, artist);
				String coverPath = Queries.selectLocalCoverPath(album, artist);

				if (coverPath != null) {
					BufferedImage image = GUIMethods.imageFromURL(coverPath);
					GUIMethods.loadImage(mainFrame.getAlbumImageLabel(), image);
				} else if (coverURL != null) {
					BufferedImage image = GUIMethods.imageFromURL(coverURL);
					GUIMethods.loadImage(mainFrame.getAlbumImageLabel(), image);
				} else {
					GUIMethods.loadImage(mainFrame.getAlbumImageLabel(), mainFrame.getProject_path() + "/assets/album-image-placeholder.png");
				}
			}

//			// if not load imported image or placeholder
//			if (cover == null) {
//				System.out.println("Cover is null");
//				GUIMethods.loadImage(mainFrame.getAlbumImageLabel(), mainFrame.getProject_path() + "/assets/album-image-placeholder.png");
//				return;
//			}
//
//			File albumCover = new File(cover);
//
//			if (albumCover.isFile()) {
//				BufferedImage img = null;
//
//				try {
//					img = ImageIO.read(new File(albumCover.getAbsolutePath()));
//				} catch (IOException e) {
//				}
//
//				if (img == null) {
//					return;
//				}
//
//				if (img.getWidth() > 392 || img.getHeight() > 324) {
//					BufferedImage resized = GUIMethods.resizeImage(img, 392, 324);
//					GUIMethods.loadImage(mainFrame.getAlbumImageLabel(), resized);
//				} else {
//					GUIMethods.loadImage(mainFrame.getAlbumImageLabel(), img);
//				}
//
//			}
		}
	}

	public static void selectSong(JFrame frame) {
		if (frame instanceof MainFrame) {
			selectSong(frame, mainFrame.getSongsList().getSelectedIndex());

		}
	}

	public static void editAction(JFrame frame) {
		if (frame instanceof MainFrame) {

			new EditSongInfo(mainFrame).setVisible(true);

			selectSong(mainFrame, mainFrame.currentIndex);
			refreshList(mainFrame);
		}
	}

	public static void scrapeAction(JFrame frame) {
		if (frame instanceof MainFrame) {

			API api = new API();
			String artist = mainFrame.currentSong.getTrack().getArtist();
			String title = mainFrame.currentSong.getTrack().getName();

			Pair<String, Integer> response = null;

			// If artist is not specified select one
			if (artist == null || "Unknown Artist".equals(artist)) {
				try {
					response = api.GET(LastFmMethods.Track.search, LastFmMethods.Track.tags(title));
				} catch (IOException | InterruptedException ex) {
					Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
					return;
				}

				if (response.second != 200) {
					JOptionPane.showMessageDialog(mainFrame, "API Reponse Code: " + response.second, "API Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				System.out.println(response.first);
				System.out.println(response.second);

				Search search = new Search(response.first);

				HashSet<Object> propableArtists = new HashSet<>();
				for (SearchTrack track : search.getTracks()) {
					propableArtists.add(track.getArtist());
				}

				artist = (String) JOptionPane.showInputDialog(mainFrame, "Select Artist", "", JOptionPane.QUESTION_MESSAGE, null, propableArtists.toArray(), 0);

				mainFrame.currentSong.getTrack().setArtist(artist);
			}

			// Make an API call using title and artist (LastFmMethods.Track.getInfo)
			try {
				response = api.GET(LastFmMethods.Track.getInfo, LastFmMethods.Track.tags(artist, title));
			} catch (IOException | InterruptedException ex) {
				Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
				return;
			}

			if (response.second != 200) {
				JOptionPane.showMessageDialog(mainFrame, "API Reponse Code: " + response.second, "API Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			System.out.println(response.first);

			if ("{\"error\":6,\"message\":\"Track not found\",\"links\":[]}".equals(response.first)) {
				JOptionPane.showMessageDialog(mainFrame, "Track not found", "API Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Update the Song
			mainFrame.currentSong.setTrack(new Track(response.first));

			// Scrape for the Album if not scraped already
			if (mainFrame.currentSong.getTrack().getAlbum() != null && !mainFrame.currentSong.getTrack().getAlbum().isBlank()) {

				Album album = Queries.selectAlbum(mainFrame.currentSong.getTrack().getAlbum(), artist);

				if (album == null) {
					try {
						response = api.GET(LastFmMethods.Album.getInfo, LastFmMethods.Album.tags(artist, mainFrame.currentSong.getTrack().getAlbum()));
					} catch (IOException | InterruptedException ex) {
						System.err.println("Album scrape fail");
					}

					album = new Album(response.first);
					Queries.insertAlbum(album);
				}
			}

			// Scrape for the Artist if not scraped already
			Artist artistO = Queries.selectArtist(artist);

			if (artistO == null) {
				try {
					response = api.GET(LastFmMethods.Artist.getInfo, LastFmMethods.Artist.tags(artist));
				} catch (IOException | InterruptedException ex) {
					System.err.println("Artist scrape fail");
				}

				artistO = new Artist(response.first);
				Queries.insertArtist(artistO);
			}

			Queries.updateSong(mainFrame.currentSong);
			Queries.updateScraped(true, mainFrame.currentSong.getAbsolutePath());
			JOptionPane.showMessageDialog(mainFrame, "Scrape Completed", "Success", JOptionPane.INFORMATION_MESSAGE);

			sort(mainFrame);
			Backend.selectSong(mainFrame, mainFrame.currentIndex);
			refreshList(mainFrame);
			mainFrame.getSongsList().setSelectedIndex(mainFrame.currentIndex);
		}
	}

}
