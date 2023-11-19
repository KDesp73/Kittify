/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer.backend;

import dorkbox.desktop.Desktop;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import kdesp73.musicplayer.api.API;
import kdesp73.musicplayer.api.Album;
import kdesp73.musicplayer.api.Artist;
import kdesp73.musicplayer.api.LastFmMethods;
import kdesp73.musicplayer.api.Pair;
import kdesp73.musicplayer.api.Search;
import kdesp73.musicplayer.api.SearchTrack;
import kdesp73.musicplayer.db.Queries;
import kdesp73.musicplayer.files.FileOperations;
import kdesp73.musicplayer.files.Images;
import kdesp73.musicplayer.gui.EditSongInfoFrame;
import kdesp73.musicplayer.gui.GUIMethods;
import kdesp73.musicplayer.gui.MainFrame;
import kdesp73.musicplayer.gui.Tag;
import kdesp73.musicplayer.gui.ThemesFrame;
import kdesp73.musicplayer.gui.WrapLayout;
import kdesp73.musicplayer.player.Mp3Player;
import kdesp73.musicplayer.songs.Mp3File;
import kdesp73.musicplayer.songs.SongsList;
import kdesp73.themeLib.Theme;
import kdesp73.themeLib.ThemeCollection;
import kdesp73.themeLib.YamlFile;

/**
 *
 * @author konstantinos
 */
public class Backend {

	private static MainFrame mainFrame;

	public static String cleanTextContent(String text) {
		// strips off all non-ASCII characters
		text = text.replaceAll("[^\\x00-\\x7F]", "");

		// erases all the ASCII control characters
		text = text.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");

		// removes non-printable characters from Unicode
		text = text.replaceAll("\\p{C}", "");

		text = text.replaceAll("[^ -~]", "");

		text = text.replaceAll("[^\\p{ASCII}]", "");

		text = text.replaceAll("\\\\x\\p{XDigit}{2}", "");

		text = text.replaceAll("\\n", "");

		text = text.replaceAll("[^\\x20-\\x7e]", "");
		return text.trim();
	}

	public static void setMainFrame(MainFrame mainFrame) {
		Backend.mainFrame = mainFrame;
		UIFunctionality.setMainFrame(mainFrame);
	}

	public static void setup(JFrame frame) {
		if (frame instanceof MainFrame) {
			// Frame settings
			mainFrame.setLocationRelativeTo(null);
			mainFrame.getRootPane().requestFocus();
			mainFrame.setIconImage(new ImageIcon(Images.icon32png).getImage());
			mainFrame.setTitle(MainFrame.TITLE);
			mainFrame.setMinimumSize(mainFrame.getPreferredSize());

			// Component settings
			mainFrame.getSongsList().setFixedCellHeight(35);
			mainFrame.getAlbumTracksList().setFixedCellHeight(35);
			mainFrame.getAlbumTracksList().setFocusable(false);
			mainFrame.getSortComboBox().setSelectedItem(Queries.selectSortBy());
			mainFrame.getCentralPanel().setBackground(mainFrame.getCentralPanel().getParent().getBackground());
			mainFrame.getBackgroundSplitPane().setPreferredSize(new Dimension(mainFrame.getPreferredSize().width, mainFrame.getPreferredSize().height - 24));
			mainFrame.getBackgroundSplitPane().setDividerLocation(0.25);
			mainFrame.getBackgroundSplitPane().setOneTouchExpandable(true);
			mainFrame.getBackgroundSplitPane().setResizeWeight(0.5);
			setupTagsPanel(mainFrame.getTagsContainer());

			updateSongs(frame);
			sort(frame);

			if (!mainFrame.list.getSongs().isEmpty()) {
				mainFrame.currentIndex = (mainFrame.list.searchSongPath(Queries.selectLastPlayed()) < 0) ? 0 : mainFrame.list.searchSongPath(Queries.selectLastPlayed());
				mainFrame.currentSong = mainFrame.list.getSongs().get(mainFrame.currentIndex);
			} else {
				setDefaultSongInfo(frame);
				setDefaultSongAdditionalInfo(frame);
			}

			mainFrame.player = new Mp3Player(mainFrame.currentIndex, mainFrame.list.getPaths());
			mainFrame.player.setFrame(mainFrame);

			selectSong(mainFrame, mainFrame.currentIndex);

			mainFrame.scrapeAtStart = Queries.selectScrapeAtStart();
			mainFrame.getScrapeAtStartMenuItem().setSelected(mainFrame.scrapeAtStart);
			mainFrame.downloadCovers = Queries.selectDownloadCoverByDefault();
			mainFrame.getDownloadCoverMenuItem().setSelected(mainFrame.downloadCovers);

			if (mainFrame.scrapeAtStart) {
				mainFrame.list.scrapeSongs();
			}

			// Timers
			Timer checkSliderTimer = new Timer(100, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (mainFrame.getSlider().getValue() == mainFrame.getSlider().getMaximum()) {
						Backend.resetSlider(frame);
						UIFunctionality.nextAction(mainFrame);
					}
				}
			});

			mainFrame.getSlider().addChangeListener((ChangeEvent e) -> {
				if (!checkSliderTimer.isRunning()) {
					checkSliderTimer.start();
				}
			});

			// Every 2 seconds check for updates in any of the imported directories
			Timer refreshTimer = new Timer(2000, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					UIFunctionality.refreshDirectories(frame);
				}
			});

			refreshTimer.start();

			// Before closing
			mainFrame.addWindowListener(new WindowAdapter() {

				@Override
				public void windowClosing(WindowEvent e) {
					if (mainFrame.currentSong != null) {
						Queries.updateLastPlayed(mainFrame.currentSong.getAbsolutePath());

					}
					Queries.updateVolume(mainFrame.getVolumeSlider().getValue());

					refreshTimer.stop();

				}
			});

			// Key Listeners
			mainFrame.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					if (e.getKeyChar() == ' ') {
						UIFunctionality.togglePlayPause(mainFrame);
					}
				}
			});

			mainFrame.getSongsList().addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					if (e.getKeyChar() == ' ') {
						mainFrame.getSongsList().ensureIndexIsVisible(mainFrame.currentIndex);
						mainFrame.getSongsList().setSelectedIndex(mainFrame.currentIndex);
						mainFrame.getRootPane().requestFocus();
						UIFunctionality.togglePlayPause(mainFrame);
					}
				}
			});

			// Set mode
			String mode = Queries.selectMode();
			setMode(mainFrame, mode);
			GUIMethods.setFontFamilyRecursively(mainFrame, "sans-serif", Font.PLAIN);

			// Set volume
			mainFrame.volume = Queries.selectVolume();
			mainFrame.getVolumeSlider().setValue(mainFrame.volume);
			mainFrame.player.setVolume(mainFrame.volume, mainFrame.getVolumeSlider().getMaximum());

			// Load icons as necessary
			loadIcons(frame);

			mainFrame.shuffleOn = Queries.selectShuffle();
			mainFrame.repeatOn = Queries.selectRepeat();

			if (mainFrame.shuffleOn) {
				loadIcon(mainFrame.getShuffleLabel(), Images.shuffleBlue, new Dimension(20, 20));
			} else {
				loadIcon(mainFrame.getShuffleLabel(), (mode.equals("Dark") ? Images.shuffleWhite : Images.shuffle), new Dimension(20, 20));
			}

			if (mainFrame.repeatOn) {
				loadIcon(mainFrame.getRepeatLabel(), Images.repeatBlue, new Dimension(20, 20));
			} else {
				loadIcon(mainFrame.getRepeatLabel(), (mode.equals("Dark") ? Images.repeatWhite : Images.repeat), new Dimension(20, 20));
			}
		}
	}

	public static void addExtensions() {
		FileOperations.acceptedExtensions.add("mp3");
//		FileOperations.acceptedExtensions.add("wav");
//		FileOperations.acceptedExtensions.add("mpeg");
	}

	public static void updateSongs(JFrame frame) {
		addExtensions();
		initList(frame);

		sort(frame);
		refreshList(frame);
	}

	public static int randomInt(JFrame frame) {
		if (frame instanceof MainFrame) {
			if (mainFrame.list.getSongs().isEmpty()) {
				return -1;
			}

			int num;

			do {
				num = ThreadLocalRandom.current().nextInt(0, mainFrame.list.getSongs().size());
			} while (((MainFrame) frame).currentIndex == num);

			return num;
		}

		return -1;
	}

	public static String sort(JFrame frame) {
		if (frame instanceof MainFrame) {

			int selected = mainFrame.getSortComboBox().getSelectedIndex();

			if (mainFrame.list == null) {
				return null;
			}

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
				case 3 -> {
					mainFrame.list.sortByTime();
					refreshList(mainFrame, "Newest");
					return "Newest";
				}
				default -> {
					return null;
				}
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

			if (mainFrame.player != null) {
				mainFrame.player.setPlaylist(mainFrame.list.getPaths());
			}
		}
	}

	public static void refreshList(JFrame frame) {
		if (frame instanceof MainFrame) {
			String sortBy = Queries.selectSortBy();

			if (sortBy == null) {
				return;
			}

			DefaultListModel listModel = new DefaultListModel();
			for (Mp3File song : mainFrame.list.getSongs()) {
				String listText = "";
				switch (sortBy) {
					case "Name":
					case "Newest":
						listText = (song.getTrack().getArtist() == null || song.getTrack().getArtist().isBlank())
								? song.getTrack().getName()
								: song.getTrack().getName() + " - " + song.getTrack().getArtist();
						break;
					case "Artist":
						listText = song.getTrack().getArtist() + " - " + song.getTrack().getName();
						break;
					case "Album":
						listText = song.getTrack().getAlbum() + " - " + song.getTrack().getName();
						break;
					default:
						System.err.println("Invalid Input");
						break;

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
					case "Name":
					case "Newest":
						listModel.addElement(song.getTrack().getName() + " - " + song.getTrack().getArtist());
						break;
					case "Artist":
						listModel.addElement(song.getTrack().getArtist() + " - " + song.getTrack().getName());
						break;
					case "Album":
						listModel.addElement(song.getTrack().getAlbum() + " - " + song.getTrack().getName());
						break;
					default:
						System.err.println("Invalid input");
						break;
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
			updateAdditionalSongInfo(frame, index);

			mainFrame.currentSong = mainFrame.list.getSongs().get(index);
			mainFrame.currentIndex = index;

			mainFrame.player.setSong(index);

			mainFrame.getSongsList().setSelectedIndex(index);
			mainFrame.getSongsList().ensureIndexIsVisible(index);

			mainFrame.getSlider().setMaximum((int) (mainFrame.currentSong.getDurationInSeconds()));
		}

	}

	public static void updateAdditionalSongInfo(JFrame frame) {
		if (frame instanceof MainFrame) {
			updateAdditionalSongInfo(frame, mainFrame.getSongsList().getSelectedIndex());
		}
	}

	public static void updateAdditionalSongInfo(JFrame frame, int index) {
		if (frame instanceof MainFrame) {
			if (mainFrame.getArtistNameLabel().getMouseListeners().length > 0) {
				mainFrame.getArtistNameLabel().removeMouseListener(mainFrame.getArtistNameLabel().getMouseListeners()[0]);
			}
			if (mainFrame.getAlbumNameLabel().getMouseListeners().length > 0) {
				mainFrame.getAlbumNameLabel().removeMouseListener(mainFrame.getAlbumNameLabel().getMouseListeners()[0]);
			}

			String artistName = mainFrame.list.getSongs().get(index).getTrack().getArtist();
			String albumName = mainFrame.list.getSongs().get(index).getTrack().getAlbum();

			Artist artist = Queries.selectArtist(artistName);
			Album album = Queries.selectAlbum(albumName, artistName);

			MouseListener artistLabelMouseListener;
			MouseListener albumLabelMouseListener;

			// ARTIST
			if (artist != null) {
				mainFrame.getArtistNameLabel().setText(artistName);

				String content = artist.getContent();

				if (content != null && !content.isBlank()) {
					String link = content.substring(content.indexOf("<a href=\"") + "<a href=\"".length(), content.indexOf("\"", content.indexOf("<a href=\"") + "<a href=\"".length()));

					artistLabelMouseListener = new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							try {
								Desktop.browseURL(link);
							} catch (IOException ex) {
								Logger.getLogger(Backend.class.getName()).log(Level.SEVERE, null, ex);
							}
						}
					};

					mainFrame.getArtistNameLabel().addMouseListener(artistLabelMouseListener);

					mainFrame.getArtistNameLabel().setCursor(new Cursor(Cursor.HAND_CURSOR));

					content = content.substring(0, content.indexOf("<a href"));
					content = content.trim();
				}

				mainFrame.getArtistContentTextArea().setText(content);
				mainFrame.getArtistContentTextArea().setCaretPosition(0);

				addTags(artist);

				try {
					GUIMethods.loadImage(mainFrame.getArtistImageLabel(), GUIMethods.imageFromURL(artist.getImage()));
				} catch (java.lang.NullPointerException e) {
					GUIMethods.loadImage(mainFrame.getArtistImageLabel(), Images.artistPlaceholder);
				}
			} else {
				Backend.setDefaultArtistAdditionalInfo(frame);
			}

			// ALBUM
			if (album != null) {

				mainFrame.getAlbumNameLabel().setText(albumName);
				DefaultListModel listModel = new DefaultListModel();
				for (String track : album.getTracks()) {
					listModel.addElement(track);
				}

				mainFrame.getAlbumTracksList().setModel(listModel);

				String content = album.getContent();

				if (content != null && !content.isBlank()) {
					String link = content.substring(content.indexOf("<a href=\"") + "<a href=\"".length(), content.indexOf("\"", content.indexOf("<a href=\"") + "<a href=\"".length()));

					albumLabelMouseListener = new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							try {
								Desktop.browseURL(link);
							} catch (IOException ex) {
								Logger.getLogger(Backend.class.getName()).log(Level.SEVERE, null, ex);
							}
						}
					};

					mainFrame.getAlbumNameLabel().addMouseListener(albumLabelMouseListener);
					mainFrame.getAlbumNameLabel().setCursor(new Cursor(Cursor.HAND_CURSOR));

					content = content.substring(0, content.indexOf("<a href"));
					content = content.trim();
				}

				mainFrame.getAlbumContentTextArea().setText(content);
				mainFrame.getAlbumContentTextArea().setCaretPosition(0);

//				try {
//					GUIMethods.loadImage(mainFrame.getAlbumCoverInfoLabel(), GUIMethods.imageFromURL(Queries.selectAlbumCover(albumName, artistName)));
//				} catch (java.lang.NullPointerException e) {
//					GUIMethods.loadImage(mainFrame.getAlbumCoverInfoLabel(), Images.albumPlaceholder);
//				}
				if (!album.getName().isBlank() && !album.getName().equals("Unknown Album")) {

					String coverURL = Queries.selectAlbumCover(album.getName(), album.getArtist());
					String coverPath = Queries.selectLocalCoverPath(album.getName(), album.getArtist());

					if (coverPath != null && !coverPath.isBlank()) {
						GUIMethods.loadImage(mainFrame.getAlbumCoverInfoLabel(), coverPath);
						return;
					} else if (coverURL != null && !coverURL.isBlank()) {
						BufferedImage image = GUIMethods.imageFromURL(coverURL);
						if (image != null) {
							GUIMethods.loadImage(mainFrame.getAlbumCoverInfoLabel(), image);
							return;
						}
					}
				}

				try {
					GUIMethods.loadImage(mainFrame.getAlbumCoverInfoLabel(), GUIMethods.resizeImage(ImageIO.read(new File(Images.albumPlaceholder)), 300, 300));
				} catch (IOException ex) {
					Logger.getLogger(Backend.class.getName()).log(Level.SEVERE, null, ex);
				}
			} else {
				Backend.setDefaultAlbumAdditionalInfo(frame);
			}

		}
	}

	public static void addTags(Artist artist) {
		if (artist == null) {
			return;
		}

		mainFrame.getTagsContainer().removeAll();
		mainFrame.getTagsContainer().repaint();
		Theme theme;
		if (Queries.selectTheme().equals("Dark")) {
			theme = new Theme(new YamlFile(System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/themes/dark.yml"));
		} else {
			theme = new Theme(new YamlFile(System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/themes/light.yml"));
		}

		for (String tag : artist.getTags()) {
			mainFrame.getTagsContainer().add(new Tag(tag.trim().replace("\n", ""), theme.getExtra(0), theme.getFg()));
		}
	}

	public static void setDefaultSongAdditionalInfo(JFrame frame) {
		if (frame instanceof MainFrame) {
			setDefaultArtistAdditionalInfo(frame);
			setDefaultAlbumAdditionalInfo(frame);
		}
	}

	public static void setDefaultAlbumAdditionalInfo(JFrame frame) {
		if (frame instanceof MainFrame) {
			mainFrame.getAlbumNameLabel().setText("Album");
			mainFrame.getAlbumContentTextArea().setText("");
//			mainFrame.getAlbumTracksList().removeAll();
			mainFrame.getAlbumTracksList().setModel(new DefaultListModel<>());
			GUIMethods.loadImage(mainFrame.getAlbumCoverInfoLabel(), Images.albumPlaceholder);

		}
	}

	public static void setDefaultArtistAdditionalInfo(JFrame frame) {
		if (frame instanceof MainFrame) {
			mainFrame.getArtistNameLabel().setText("Artist");
			mainFrame.getArtistContentTextArea().setText("");
			GUIMethods.loadImage(mainFrame.getArtistImageLabel(), Images.artistPlaceholder);
			mainFrame.getTagsContainer().removeAll();
			mainFrame.getTagsContainer().repaint();
		}
	}

	public static void setDefaultSongInfo(JFrame frame) {
		if (frame instanceof MainFrame) {
			mainFrame.getTrackLabel().setText("Title");
			mainFrame.getArtistLabel().setText("Artist");
			mainFrame.getTimeLabel().setText("00:00");
			mainFrame.getDurationLabel().setText("00:00");

			try {
				GUIMethods.loadImage(mainFrame.getAlbumImageLabel(), GUIMethods.resizeImage(ImageIO.read(new File(Images.albumPlaceholder)), 420, 420));
			} catch (IOException ex) {
				Logger.getLogger(Backend.class.getName()).log(Level.SEVERE, null, ex);
			}
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
			mainFrame.getDurationLabel().setText(Backend.secondsToMinutes((int) mainFrame.list.getSongs().get(index).getDurationInSeconds()));

			Queries.updateSong(mainFrame.list.getSongs().get(index));

			if (cover != null && cover.isBlank()) {
				Queries.updateLocalCoverPath(album, artist, cover);
			}

			// If album has been scraped load album cover
			if (album != null && !album.isBlank() && !album.equals("Unknown Album")) {

				String coverURL = Queries.selectAlbumCover(album, artist);
				String coverPath = Queries.selectLocalCoverPath(album, artist);

				if (coverPath != null && !coverPath.isBlank()) {
					try {
						GUIMethods.loadImage(mainFrame.getAlbumImageLabel(), GUIMethods.resizeImage(ImageIO.read(new File(coverPath)), 420, 420));
					} catch (IIOException ex) {
						try {
							GUIMethods.loadImage(mainFrame.getAlbumImageLabel(), GUIMethods.resizeImage(ImageIO.read(new File(Images.albumPlaceholder)), 420, 420));
						} catch (IOException ex1) {
							Logger.getLogger(Backend.class.getName()).log(Level.SEVERE, null, ex1);
						}
					} catch (IOException ex) {
						try {
							GUIMethods.loadImage(mainFrame.getAlbumImageLabel(), GUIMethods.resizeImage(ImageIO.read(new File(Images.albumPlaceholder)), 420, 420));
						} catch (IOException ex1) {
							Logger.getLogger(Backend.class.getName()).log(Level.SEVERE, null, ex1);
						}
					}
					return;
				} else if (coverURL != null && !coverURL.isBlank()) {
					BufferedImage image = GUIMethods.imageFromURL(coverURL);
					if (image != null) {
						GUIMethods.loadImage(mainFrame.getAlbumImageLabel(), GUIMethods.resizeImage(image, 420, 420));
						return;
					}
				}
			}

			try {
				GUIMethods.loadImage(mainFrame.getAlbumImageLabel(), GUIMethods.resizeImage(ImageIO.read(new File(Images.albumPlaceholder)), 420, 420));
			} catch (IOException ex) {
				Logger.getLogger(Backend.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public static void selectSong(JFrame frame) {
		if (frame instanceof MainFrame) {
			selectSong(frame, mainFrame.getSongsList().getSelectedIndex());
		}
	}

	public static void downloadAlbumCover(JFrame frame) {
		if (frame instanceof MainFrame) {
			Mp3File selectedSong = ((MainFrame) frame).list.getSongs().get(((MainFrame) frame).getSongsList().getSelectedIndex());

			String localCover = Queries.selectLocalCoverPath(selectedSong.getTrack().getAlbum(), selectedSong.getTrack().getArtist());
			String coverURL = Queries.selectAlbumCover(selectedSong.getTrack().getAlbum(), selectedSong.getTrack().getArtist());

			if (localCover != null && !localCover.isBlank()) {
				File coverFile = new File(localCover);

				if (coverFile.exists()) {
					JOptionPane.showMessageDialog(mainFrame, "Album already has a local image");
					return;
				}
			}
			
			if(coverURL == null){
				System.err.println("Null URL");
				return;
			}

			// Download image
			String imagePath = mainFrame.getProject_path() + "/covers/" + selectedSong.getTrack().getAlbum() + " - " + selectedSong.getTrack().getArtist() + ".png";
			try {
				GUIMethods.downloadImage(new URL(coverURL), imagePath);
			} catch (MalformedURLException ex) {
				Logger.getLogger(Backend.class.getName()).log(Level.SEVERE, null, ex);
				System.err.println("Invalid URL:" + coverURL);
			} catch (IOException ex) {
				Logger.getLogger(Backend.class.getName()).log(Level.SEVERE, null, ex);
				System.err.println("Downloading image failed");
			}

			Queries.updateLocalCoverPath(selectedSong.getTrack().getAlbum(), selectedSong.getTrack().getArtist(), imagePath);
			JOptionPane.showMessageDialog(mainFrame, "Downloaded image at covers/ directory");
		}

	}

	public static void downloadAlbumCover(Mp3File file) {
		String localCover = Queries.selectLocalCoverPath(file.getTrack().getAlbum(), file.getTrack().getArtist());
		String coverURL = Queries.selectAlbumCover(file.getTrack().getAlbum(), file.getTrack().getArtist());

		if (localCover != null && !localCover.isBlank()) {
			File coverFile = new File(localCover);

			if (coverFile.exists()) {
//				JOptionPane.showMessageDialog(mainFrame, "Album already has a local image");
				System.err.println("Album already has a local image");
				return;
			}
		}

		// Download image
		String imagePath = mainFrame.getProject_path() + "/covers/" + file.getTrack().getAlbum() + " - " + file.getTrack().getArtist() + ".png";
		try {
			GUIMethods.downloadImage(new URL(coverURL), imagePath);
		} catch (MalformedURLException ex) {
			System.err.println("Invalid URL: " + coverURL);
			System.err.println("Downloading image failed");
//			ex.printStackTrace();
		} catch (IOException ex) {
			System.err.println("Downloading image failed");
//			ex.printStackTrace();
		}

		Queries.updateLocalCoverPath(file.getTrack().getAlbum(), file.getTrack().getArtist(), imagePath);
//		JOptionPane.showMessageDialog(mainFrame, "Downloaded image at covers/ directory");

	}

	public static void editAction(JFrame frame) {
		if (frame instanceof MainFrame) {
			if (!MainFrame.editSongFrame.isShowing()) {
				MainFrame.editSongFrame = new EditSongInfoFrame(mainFrame);
				MainFrame.editSongFrame.setVisible(true);
			}
		}
	}

	private static String scrapeTrack(String title, String artist) {
		Pair<String, Integer> response = null;
		try {
			response = new API().GET(LastFmMethods.Track.getInfo, LastFmMethods.Track.tags(artist, title));
		} catch (IOException | InterruptedException ex) {
			return null;
		}

		if (response == null) {
			return null;
		}

		return response.first;
	}

	private static String scrapeArtist(String artist) {
		boolean artistExistsInDB = (Queries.selectArtist(artist) != null);
		Pair<String, Integer> response = null;

		if (!artistExistsInDB) {
			try {
				response = new API().GET(LastFmMethods.Artist.getInfo, LastFmMethods.Artist.tags(artist));
			} catch (IOException | InterruptedException ex) {
				System.err.println("Artist scrape fail");
				return null;
			}
		}

		if (response != null) {
			Queries.insertArtist(new Artist(response.first));
			return response.first;
		}

		return null;
	}

	private static String scrapeAlbum(String album, String artist) {
		boolean albumExistsInDB = (Queries.selectAlbum(artist) != null);
		Pair<String, Integer> response = null;

		if (!albumExistsInDB) {
			try {
				response = new API().GET(LastFmMethods.Album.getInfo, LastFmMethods.Album.tags(artist, album));
			} catch (IOException | InterruptedException ex) {
				System.err.println("Artist scrape fail");
				return null;
			}
		}
		if (response != null) {
			Queries.insertAlbum(new Album(response.first));
			return response.first;
		}

		return null;
	}

	public static void scrapeAction(JFrame frame) {
		if (frame instanceof MainFrame) {

			API api = new API();
			String title = mainFrame.list.getSongs().get(mainFrame.getSongsList().getSelectedIndex()).getTrack().getName();
			String artist = mainFrame.list.getSongs().get(mainFrame.getSongsList().getSelectedIndex()).getTrack().getArtist();
//			String album = mainFrame.list.getSongs().get(mainFrame.getSongsList().getSelectedIndex()).getTrack().getAlbum();

			Pair<String, Integer> response = null;

			// If artist is not specified select one
			if (artist == null || "unknown artist".equals(artist.toLowerCase())) {
				try {
					response = api.GET(LastFmMethods.Track.search, LastFmMethods.Track.tags(title));

				} catch (IOException | InterruptedException ex) {
					Logger.getLogger(MainFrame.class
							.getName()).log(Level.SEVERE, null, ex);
					return;
				}

				if (response.second != 200) {
					JOptionPane.showMessageDialog(mainFrame, "API Reponse Code: " + response.second, "API Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				Search search = new Search(response.first);

				HashSet<Object> propableArtists = new HashSet<>();
				for (SearchTrack track : search.getTracks()) {
					propableArtists.add(track.getArtist());
				}

				artist = (String) JOptionPane.showInputDialog(mainFrame, "Select Artist", "", JOptionPane.QUESTION_MESSAGE, null, propableArtists.toArray(), 0);
			}

//			scrapeArtist(artist);
			Mp3File song = mainFrame.list.getSongs().get(mainFrame.getSongsList().getSelectedIndex());
			song.setArtist(artist);

			song.selfScrape();

//			Queries.updateSong(song);
//			Queries.updateScraped(true, song.getAbsolutePath());
			JOptionPane.showMessageDialog(mainFrame, "Scrape Completed", "Success", JOptionPane.INFORMATION_MESSAGE);

			// UPDATE UI ACCORDINGLY
			initList(mainFrame);
			sort(mainFrame);
			mainFrame.player.playlist = mainFrame.list.getPaths();

			int index = mainFrame.list.searchSongName(song.getTrack().getName());

			mainFrame.getSongsList().setSelectedIndex(index);
			mainFrame.getSongsList().ensureIndexIsVisible(index);

			boolean scrapingSelectedSong = mainFrame.currentSong == null || mainFrame.currentSong.getAbsolutePath().equals(song.getAbsolutePath());

			if (scrapingSelectedSong) {
				selectSong(mainFrame, index);
			} else {
				System.out.println("Scraped song that is not selected");
			}

			if (mainFrame.downloadCovers) {
				System.out.println("Downloading cover...");
				Backend.downloadAlbumCover(song);
			}
		}
	}

	public static void setMode(JFrame frame, String mode) {
		switch (mode) {
			case "Light" -> {
				try {
					UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
					setTheme(frame, System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/themes/" + "light.yml");

				} catch (UnsupportedLookAndFeelException ex) {
					Logger.getLogger(Backend.class
							.getName()).log(Level.SEVERE, null, ex);
				}
			}

			case "Dark" -> {
				try {
//					UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarkLaf());
					UIManager.setLookAndFeel(new com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneDarkIJTheme());
					setTheme(frame, System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/themes/" + "dark.yml");

				} catch (UnsupportedLookAndFeelException ex) {
					Logger.getLogger(ThemesFrame.class
							.getName()).log(Level.SEVERE, null, ex);
				}
			}
			default -> {
				System.err.println("Invalid input");
			}
		}
		SwingUtilities.updateComponentTreeUI(frame);
		Queries.updateTheme(mode);
		loadIcons(frame);

		if (frame instanceof MainFrame && ((MainFrame) frame).currentSong != null) {
			addTags(Queries.selectArtist(((MainFrame) frame).currentSong.getTrack().getArtist()));

		}
	}

	public static void setTheme(JFrame frame, String path) {
		if (!new File(path).isFile()) {
			return;
		}

		ThemeCollection.applyTheme(frame, new Theme(new YamlFile(path)));
	}

	public static String getVolumeRegion(int volume) {
		if (volume <= 100 && volume >= 60) {
			return "high";
		} else if (volume < 60 && volume >= 30) {
			return "low";
		} else if (volume < 30 && volume >= 1) {
			return "off";
		} else if (volume == 0) {
			return "x";
		}

		return "low";
	}

	public static String getVolumeRegion(JFrame frame) {
		if (frame instanceof MainFrame) {
			int volume = ((MainFrame) frame).volume;

			if (volume < 100 && volume >= 60) {
				return "high";
			} else if (volume < 59 && volume >= 30) {
				return "low";
			} else if (volume < 29 && volume >= 1) {
				return "off";
			} else if (volume == 0) {
				return "x";
			}
		}
		return "low";
	}

	public static void loadIcon(JLabel label, String path, Dimension dimension) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(path));

		} catch (IOException ex) {
			Logger.getLogger(Backend.class
					.getName()).log(Level.SEVERE, null, ex);
		}

		GUIMethods.loadImage(label, GUIMethods.resizeImage(image, dimension.width, dimension.height));
	}

	public static void loadIcon(JLabel label, String path, int size, String dim) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(path));

		} catch (IOException ex) {
			Logger.getLogger(Backend.class
					.getName()).log(Level.SEVERE, null, ex);
		}

		if (dim.equals("w")) {
			GUIMethods.loadImage(label, GUIMethods.resizeImageWidth(image, size));
		} else if (dim.equals("h")) {
			GUIMethods.loadImage(label, GUIMethods.resizeImageHeight(image, size));
		} else {
			System.err.println("dim parameter must be 'w' or 'h'");
		}
	}

	public static void loadVolumeIcon(JFrame frame, int volume) {
		if (frame instanceof MainFrame) {

			String mode = Queries.selectTheme();
			switch (Backend.getVolumeRegion(volume)) {
				case "high" ->
					Backend.loadIcon(((MainFrame) frame).getVolumeToggleLabel(), ((mode.equals("Dark") ? Images.volumeHighWhite : Images.volumeHigh)), 31, "w");
				case "low" ->
					Backend.loadIcon(((MainFrame) frame).getVolumeToggleLabel(), ((mode.equals("Dark") ? Images.volumeLowWhite : Images.volumeLow)), 25, "w");
				case "off" ->
					Backend.loadIcon(((MainFrame) frame).getVolumeToggleLabel(), ((mode.equals("Dark") ? Images.volumeOffWhite : Images.volumeOff)), 25, "w");
				case "x" ->
					Backend.loadIcon(((MainFrame) frame).getVolumeToggleLabel(), ((mode.equals("Dark") ? Images.volumeXMarkWhite : Images.volumeXMark)), 28, "w");
				default ->
					throw new AssertionError();
			}
		}
	}

	public static void loadIcons(JFrame frame) {
		if (frame == null) {
			return;
		}

		if (frame instanceof MainFrame) {
			if (Queries.selectTheme().equals("Dark")) {
				loadIcon(((MainFrame) frame).getPlayPauseLabel(), Images.playWhite, new Dimension(40, 40));
				loadIcon(((MainFrame) frame).getNextLabel(), Images.nextWhite, new Dimension(30, 30));
				loadIcon(((MainFrame) frame).getPrevLabel(), Images.prevWhite, new Dimension(30, 30));
//				loadIcon(((MainFrame) frame).getShuffleLabel(), Images.shuffleWhite, new Dimension(20, 20));
//				loadIcon(((MainFrame) frame).getRepeatLabel(), Images.repeatWhite, new Dimension(20, 20));
			} else {
				loadIcon(((MainFrame) frame).getPlayPauseLabel(), Images.play, new Dimension(40, 40));
				loadIcon(((MainFrame) frame).getNextLabel(), Images.next, new Dimension(30, 30));
				loadIcon(((MainFrame) frame).getPrevLabel(), Images.prev, new Dimension(30, 30));
//				loadIcon(((MainFrame) frame).getShuffleLabel(), Images.shuffle, new Dimension(20, 20));
//				loadIcon(((MainFrame) frame).getRepeatLabel(), Images.repeat, new Dimension(20, 20));
			}
			loadVolumeIcon(frame, Queries.selectVolume());
		}
	}

	public static void changeVolume(JFrame frame) {
		if (frame instanceof MainFrame) {
			int value = mainFrame.getVolumeSlider().getValue();
			mainFrame.volume = value;
			Backend.loadVolumeIcon(frame, value);

			if (!mainFrame.player.isPlaying()) {
				return;
			}

			mainFrame.player.setVolume(value, mainFrame.getVolumeSlider().getMaximum());
		}
	}

	public static void changeVolume(JFrame frame, int value) {
		if (frame instanceof MainFrame) {
			mainFrame.volume = value;
			Backend.loadVolumeIcon(frame, value);

			if (!mainFrame.player.isPlaying()) {
				return;
			}

			mainFrame.player.setVolume(value, mainFrame.getVolumeSlider().getMaximum());
		}
	}

	public static void resetSlider(JFrame frame) {
		if (frame instanceof MainFrame) {
			((MainFrame) frame).getSlider().setValue(0);
			((MainFrame) frame).getTimeLabel().setText("00:00");
		}
	}

	public static void resetPlayer(JFrame frame) {
		if (frame instanceof MainFrame) {
			try {
				GUIMethods.loadImage(mainFrame.getPlayPauseLabel(), GUIMethods.resizeImage(ImageIO.read(new File((Queries.selectMode()).equals("Dark") ? Images.playWhite : Images.play)), 40, 40));

			} catch (IOException ex) {
				Logger.getLogger(Backend.class
						.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public static void setupTagsPanel(JPanel panel) {
		panel.setLayout(new WrapLayout(WrapLayout.LEFT));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
	}

	private static List<String> findMissingFiles(List<String> list1, List<String> list2) {
		List<String> missingFiles = new ArrayList<>();
		for (String path : list1) {
			if (!list2.contains(path)) {
				missingFiles.add(path);
			}
		}
		return missingFiles;
	}

	public static void checkDirectoriesForChanges() {
		ArrayList<String> directories = Queries.selectDirectories();
		ArrayList<String> databasePaths = Queries.selectPaths();
		ArrayList<String> localPaths = new ArrayList<>();
		List<String> individualFiles = Queries.selectFiles();

		for (String dir : directories) {
			ArrayList<String> dirFIles = null;
			try {
				dirFIles = (ArrayList<String>) FileOperations.findFiles(dir);

			} catch (IOException ex) {
				Logger.getLogger(Backend.class
						.getName()).log(Level.SEVERE, null, ex);
			}

			if (dirFIles != null) {
				localPaths.addAll(dirFIles);
			}
		}
		
		localPaths.addAll(individualFiles);

		if (databasePaths.size() == localPaths.size()) {
			return;
		}

//		Collections.sort(databasePaths);
//		Collections.sort(localPaths);
		List<String> extraPathsInDatabase = findMissingFiles(databasePaths, localPaths);

		if (!extraPathsInDatabase.isEmpty()) {
			for (String extraPath : extraPathsInDatabase) {
				Queries.deleteSong(extraPath);
				System.out.println("Deleted \"" + extraPath + "\" from Database");
			}
		}

	}
	
	public static SongsList advancedSearch(SongsList list, String text) {
		text = text.toLowerCase();

		HashSet<Mp3File> matchingFiles = new HashSet<>();
		SongsList newList = new SongsList();

		for (Mp3File file : list.getSongs()) {
			String title = file.getTrack().getName().toLowerCase();
			String artist = file.getTrack().getArtist().toLowerCase();
			String album = file.getTrack().getAlbum().toLowerCase();

			if (title.contains(text)) {
				matchingFiles.add(file);
			}
			if (artist.contains(text)) {
				matchingFiles.add(file);
			}
			if (album.contains(text)) {
				matchingFiles.add(file);
			}
		}

		newList.getSongs().addAll(matchingFiles);

		return newList;
	}

}
