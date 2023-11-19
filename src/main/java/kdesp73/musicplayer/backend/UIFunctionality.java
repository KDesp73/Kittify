/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer.backend;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileNameExtensionFilter;
import kdesp73.musicplayer.db.Queries;
import kdesp73.musicplayer.files.Images;
import kdesp73.musicplayer.gui.EditDirectoriesFrame;
import kdesp73.musicplayer.gui.EditFilesFrame;
import kdesp73.musicplayer.gui.GUIMethods;
import kdesp73.musicplayer.gui.MainFrame;

/**
 *
 * @author konstantinos
 */
public class UIFunctionality {

	private static String assets = System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/assets/";

	private static MainFrame mainFrame;

	public static void setMainFrame(MainFrame mainFrame) {
		UIFunctionality.mainFrame = mainFrame;
	}

	public static void addFile(JFrame frame) {
		if (frame instanceof MainFrame) {
			String dir = "";
			JFileChooser fc = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Sound Files", "mp3");

			fc.setFileFilter(filter);

			int choide = fc.showOpenDialog(mainFrame);

			if (choide == JFileChooser.APPROVE_OPTION) {
				File SelectedFile = fc.getSelectedFile();
				dir = SelectedFile.getPath();
			}

			if (choide == JFileChooser.CANCEL_OPTION) {
				return;
			}

			Queries.insertFile(dir);

			Backend.initList(mainFrame);
			Backend.updateSongs(mainFrame);
			Backend.refreshList(mainFrame);

			mainFrame.player.playlist = mainFrame.list.getPaths();
		}
	}

	public static void addDirectory(JFrame frame) {
		if (frame instanceof MainFrame) {
			String dir = "";
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			int choice = fc.showOpenDialog(mainFrame);

			if (choice == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fc.getSelectedFile();
				dir = selectedFile.getPath();
			}

			if (choice == JFileChooser.CANCEL_OPTION) {
				return;
			}

			Queries.insertDirectory(dir);

			Backend.initList(frame);
			Backend.sort(frame);
			Backend.refreshList(frame);

			mainFrame.player.playlist = mainFrame.list.getPaths();
		}
	}

	public static void sortComboBoxActionPerformed(JFrame frame) {
		if (frame instanceof MainFrame) {
			String sortedBy = Backend.sort(mainFrame);

			if (sortedBy == null) {
				return;
			}

			Queries.updateSortBy(sortedBy);

			mainFrame.player.playlist = ((MainFrame) frame).list.getPaths();
		}
	}

	public static void editDirectories(JFrame frame) {
		if (frame instanceof MainFrame) {
			mainFrame.player.stop();
			Backend.setDefaultSongInfo(frame);
			Backend.resetSlider(frame);
			Backend.setDefaultSongAdditionalInfo(frame);
			
			if (!MainFrame.editDirectoriesFrame.isShowing()) {
				MainFrame.editDirectoriesFrame = new EditDirectoriesFrame(mainFrame);
				MainFrame.editDirectoriesFrame.setVisible(true);
			}
		}
	}

	public static void editFiles(JFrame frame) {
		if (frame instanceof MainFrame) {
			mainFrame.player.stop();
			Backend.setDefaultSongInfo(frame);
			Backend.resetSlider(frame);
			Backend.setDefaultSongAdditionalInfo(frame);
			
			if (!MainFrame.editFilesFrame.isShowing()) {
				MainFrame.editFilesFrame = new EditFilesFrame(mainFrame);
				MainFrame.editFilesFrame.setVisible(true);
			}
		}
	}

	public static void nextAction(JFrame frame) {
		if (mainFrame.list.getSongs().isEmpty()) {
			return;
		}

		if (frame instanceof MainFrame) {
			int index = mainFrame.getSongsList().getSelectedIndex();
			Backend.resetSlider(frame);

			if (mainFrame.repeatOn) {
				if (mainFrame.player.timer != null) {
					try {
						mainFrame.player.timer.stop();
					} catch (UnsupportedOperationException e) {
						mainFrame.player.timer.interrupt();
						System.err.println("Cant stop TimerThread");
					}
				}
				mainFrame.player.stop();
				mainFrame.player.play();
				return;
			}

			if (mainFrame.shuffleOn) {
				mainFrame.currentIndex = Backend.randomInt(frame);
				playSong(mainFrame, mainFrame.currentIndex);
				return;
			}

			if (index == mainFrame.list.getSongs().size() - 1 || mainFrame.currentIndex == mainFrame.list.getSongs().size() - 1) {
				mainFrame.currentIndex = -1;
				index = -1;
			}

			if (mainFrame.player.timer != null) {
				try {
					mainFrame.player.timer.stop();
				} catch (UnsupportedOperationException e) {
					mainFrame.player.timer.interrupt();
					System.err.println("Cant stop TimerThread");
				}
			}
			mainFrame.player.stop();
			mainFrame.player.next();
			Backend.selectSong(mainFrame, index + 1);

//			mainFrame.getPlayButton().setText("Pause");
			Backend.loadIcon(mainFrame.getPlayPauseLabel(), assets + ((Queries.selectTheme().equals("Light")) ? "circle-pause-solid.png" : "circle-pause-solid-white.png"), new Dimension(40, 40));

		}
	}

	public static void playSong(JFrame frame) {
		if (frame instanceof MainFrame) {
			mainFrame.currentIndex = mainFrame.getSongsList().getSelectedIndex();
			mainFrame.currentSong = mainFrame.list.getSongs().get(mainFrame.currentIndex);
			Backend.resetSlider(mainFrame);

			mainFrame.player.stop();
			if (mainFrame.player.timer != null) {
				try {
					mainFrame.player.timer.stop();
				} catch (UnsupportedOperationException e) {
					mainFrame.player.timer.interrupt();
					System.err.println("Cant stop TimerThread");
				}
			}

			Backend.selectSong(mainFrame);

			mainFrame.player.play(mainFrame.getSongsList().getSelectedIndex());
//			mainFrame.getPlayButton().setText("Pause");
			Backend.loadIcon(mainFrame.getPlayPauseLabel(), assets + ((Queries.selectTheme().equals("Light")) ? "circle-pause-solid.png" : "circle-pause-solid-white.png"), new Dimension(40, 40));
			MainFrame.tray.setPlayPause();

		}
	}

	public static void playSong(JFrame frame, int index) {
		if (frame instanceof MainFrame) {
//			mainFrame.currentIndex = mainFrame.getSongsList().getSelectedIndex();
//			mainFrame.currentSong = mainFrame.list.getSongs().get(mainFrame.currentIndex);
			Backend.resetSlider(mainFrame);

			mainFrame.player.stop();
			if (mainFrame.player.timer != null) {
				try {
					mainFrame.player.timer.stop();
				} catch (UnsupportedOperationException e) {
					mainFrame.player.timer.interrupt();
					System.err.println("Cant stop TimerThread");
				}
			}

			Backend.selectSong(mainFrame, index);

			mainFrame.player.play(index);
//			mainFrame.getPlayButton().setText("Pause");
			Backend.loadIcon(mainFrame.getPlayPauseLabel(), assets + ((Queries.selectTheme().equals("Light")) ? "circle-pause-solid.png" : "circle-pause-solid-white.png"), new Dimension(40, 40));
			MainFrame.tray.setPlayPause();

		}
	}

	public static void togglePlayPause(JFrame frame) {
		if (frame instanceof MainFrame) {
			if (mainFrame.list.getSongs().isEmpty()) {
				return;
			}
			mainFrame.currentSong = mainFrame.list.getSongs().get(mainFrame.currentIndex);
			if (mainFrame.player.isPlaying()) {
				mainFrame.player.pause();
				Backend.loadIcon(mainFrame.getPlayPauseLabel(), assets + ((Queries.selectTheme().equals("Light")) ? "circle-play-solid.png" : "circle-play-solid-white.png"), new Dimension(40, 40));
			} else {
				mainFrame.player.play();
				Backend.loadIcon(mainFrame.getPlayPauseLabel(), assets + ((Queries.selectTheme().equals("Light")) ? "circle-pause-solid.png" : "circle-pause-solid-white.png"), new Dimension(40, 40));
			}

			MainFrame.tray.setPlayPause();
		}
	}

	public static void prevAction(JFrame frame) {
		if (frame instanceof MainFrame) {
			if (mainFrame.list.getSongs().isEmpty()) {
				return;
			}
			int index = mainFrame.getSongsList().getSelectedIndex();
			Backend.resetSlider(frame);

			if (mainFrame.repeatOn) {
				mainFrame.player.stop();
				mainFrame.player.play();
				return;
			}

			if (mainFrame.shuffleOn) {
				mainFrame.currentIndex = Backend.randomInt(frame);
				playSong(mainFrame, mainFrame.currentIndex);
				return;
			}

			if (index == 0 || mainFrame.currentIndex == 0) {
				mainFrame.currentIndex = mainFrame.list.getSongs().size();
				index = mainFrame.list.getSongs().size();
			}

			if (mainFrame.player.timer != null) {
				try {
					mainFrame.player.timer.stop();
				} catch (UnsupportedOperationException e) {
					mainFrame.player.timer.interrupt();
					System.err.println("Cant stop TimerThread");
				}
			}
			mainFrame.player.prev();
			Backend.selectSong(mainFrame, index - 1);

//			mainFrame.getPlayButton().setText("Pause");
			Backend.loadIcon(mainFrame.getPlayPauseLabel(), assets + ((Queries.selectTheme().equals("Light")) ? "circle-pause-solid.png" : "circle-pause-solid-white.png"), new Dimension(40, 40));

		}
	}

	public static void sliderMouseDragged(JFrame frame) {
		if (frame instanceof MainFrame) {
			if (mainFrame.currentSong == null) {
				mainFrame.getSlider().setEnabled(false);
				return;
			}

			long duration = mainFrame.currentSong.getDurationInSeconds();

			mainFrame.getSlider().setMaximum((int) duration + 1);

			mainFrame.getTimeLabel().setText(Backend.secondsToMinutes(mainFrame.getSlider().getValue()));
		}
	}

	public static void sliderMouseReleased(JFrame frame) {
		if (frame instanceof MainFrame) {
			int skip = mainFrame.getSlider().getValue();

			mainFrame.player.seek(skip);
		}
	}

	public static void showOptionsPopup(JFrame frame, MouseEvent evt) {
		if (frame instanceof MainFrame) {
			JPopupMenu options = new JPopupMenu();
			JMenuItem edit = new JMenuItem("Edit");
			JMenuItem scrape = new JMenuItem("Scrape");
			JMenuItem downloadAlbumCover = new JMenuItem("Download Cover");
			JMenuItem printTrack = new JMenuItem("Print Track");

			options.setCursor(new Cursor(Cursor.HAND_CURSOR));

			ActionListener menuListener = new ActionListener() {
				String selectedItem;

				@Override
				public void actionPerformed(ActionEvent event) {
					selectedItem = event.getActionCommand();
					if (null != selectedItem) {
						switch (selectedItem) {
							case "Edit" ->
								Backend.editAction(mainFrame);
							case "Scrape" ->
								Backend.scrapeAction(mainFrame);
							case "Download Cover" ->
								Backend.downloadAlbumCover(mainFrame);
							case "Print Track" -> {
								System.out.println(mainFrame.list.getSongs().get(mainFrame.getSongsList().getSelectedIndex()).getTrack());
							}
							case "Delete Info" -> {
								// TODO
							}
							default -> {
							}
						}
					}
				}
			};

			edit.addActionListener(menuListener);
			options.add(edit);

			scrape.addActionListener(menuListener);
			options.add(scrape);

			downloadAlbumCover.addActionListener(menuListener);
			options.add(downloadAlbumCover);

			if (MainFrame.DEBUG) {
				printTrack.addActionListener(menuListener);
				options.add(printTrack);
			}

			options.show(evt.getComponent(), evt.getX(), evt.getY());
		}
	}

	public static void search(JFrame frame) {
		if (frame instanceof MainFrame) {
			String search = JOptionPane.showInputDialog(mainFrame, "Search Song");

			if (search == null || search.isBlank()) {
				return;
			}

			int index = mainFrame.list.searchSongName(search);

			if (index < 0) {
				JOptionPane.showMessageDialog(mainFrame, "Song not found");
				return;
			}

			mainFrame.getSongsList().setSelectedIndex(index);
			mainFrame.getSongsList().ensureIndexIsVisible(index);
		}
	}

	public static void scrapeAll(JFrame frame) {
		if (frame instanceof MainFrame) {
			if (mainFrame.list.getSongs().isEmpty()) {
				JOptionPane.showMessageDialog(mainFrame, "No songs loaded", "Scrape Aborted", JOptionPane.WARNING_MESSAGE);
				return;
			}
			JOptionPane.showMessageDialog(mainFrame, "The scraping process has started in the background. This may take a while dependending on the number of songs imported", "Scraping Started", JOptionPane.INFORMATION_MESSAGE);
			mainFrame.list.scrapeSongs();
		}
	}

	public static void scrapeAtStartToggle(JFrame frame) {
		if (frame instanceof MainFrame) {
			boolean flag = mainFrame.getScrapeAtStartMenuItem().isSelected();

			Queries.updateScrapeAtStart(flag);
			mainFrame.scrapeAtStart = flag;
		}
	}

	public static void toggleDownloadCoverByDefault(JFrame frame){
		if (frame instanceof MainFrame) {
			boolean flag = mainFrame.getDownloadCoverMenuItem().isSelected();
			
			Queries.updateDownloadCoversByDefault(flag);
			mainFrame.downloadCovers = flag;
		}
	}
	
	public static void shuffle(JFrame frame) {
		if (frame instanceof MainFrame) {

			if (mainFrame.shuffleOn) {
//				Backend.sort(frame);
				Backend.loadIcon(mainFrame.getShuffleLabel(), assets + ((Queries.selectTheme().equals("Light")) ? "shuffle-solid.png" : "shuffle-solid-white.png"), new Dimension(20, 20));
			} else {
//				Backend.shuffleList(frame);
				Backend.loadIcon(mainFrame.getShuffleLabel(), assets + "shuffle-solid-blue.png", new Dimension(20, 20));
			}

			mainFrame.shuffleOn = !mainFrame.shuffleOn;
			Queries.updateShuffle(mainFrame.shuffleOn);
		}
	}

	public static void repeat(JFrame frame) {
		if (frame instanceof MainFrame) {
			if (mainFrame.repeatOn) {
				// Handle repeat
				Backend.loadIcon(mainFrame.getRepeatLabel(), assets + ((Queries.selectTheme().equals("Light")) ? "repeat-solid.png" : "repeat-solid-white.png"), new Dimension(20, 20));
			} else {
				// Handle repeat
				Backend.loadIcon(mainFrame.getRepeatLabel(), assets + "repeat-solid-blue.png", new Dimension(20, 20));
			}

			mainFrame.repeatOn = !mainFrame.repeatOn;
			Queries.updateRepeat(mainFrame.repeatOn);
		}
	}

	public static void prevLabelMousePressed(JFrame frame) {
		if (frame instanceof MainFrame) {
			try {
				GUIMethods.loadImage(mainFrame.getPrevLabel(), GUIMethods.resizeImage(ImageIO.read(new File(assets + "backward-step-solid-blue.png")), 30, 30));
			} catch (IOException ex) {
				Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

	}

	public static void prevLabelMouseReleased(JFrame frame) {
		if (frame instanceof MainFrame) {
			try {
				GUIMethods.loadImage(mainFrame.getPrevLabel(), GUIMethods.resizeImage(ImageIO.read(new File(assets + ((Queries.selectTheme().equals("Light")) ? "backward-step-solid.png" : "backward-step-solid-white.png"))), 30, 30));
			} catch (IOException ex) {
				Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public static void nextLabelMousePressed(JFrame frame) {
		if (frame instanceof MainFrame) {
			try {
				GUIMethods.loadImage(mainFrame.getNextLabel(), GUIMethods.resizeImage(ImageIO.read(new File(assets + "forward-step-solid-blue.png")), 30, 30));
			} catch (IOException ex) {
				Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public static void nextLabelMouseReleased(JFrame frame) {
		if (frame instanceof MainFrame) {
			try {
				GUIMethods.loadImage(mainFrame.getNextLabel(), GUIMethods.resizeImage(ImageIO.read(new File(assets + ((Queries.selectTheme().equals("Light")) ? "forward-step-solid.png" : "forward-step-solid-white.png"))), 30, 30));
			} catch (IOException ex) {
				Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public static void volumeToggle(JFrame frame) {
		if (frame instanceof MainFrame) {
			String mode = Queries.selectTheme();

			if (((MainFrame) frame).volumeOn) {
				((MainFrame) frame).player.setVolume(0, 100);
				((MainFrame) frame).getVolumeSlider().setValue(0);

				Backend.loadIcon(((MainFrame) frame).getVolumeToggleLabel(), ((mode.equals("Dark") ? Images.volumeXMarkWhite : Images.volumeXMark)), 28, "w");
				mainFrame.getVolumeToggleLabel().setToolTipText("Unmute");
			} else {
				Backend.changeVolume(frame, ((MainFrame) frame).volume);
				((MainFrame) frame).getVolumeSlider().setValue(((MainFrame) frame).volume);

				Backend.loadVolumeIcon(frame, ((MainFrame) frame).volume);
				mainFrame.getVolumeToggleLabel().setToolTipText("Mute");
			}

			((MainFrame) frame).volumeOn = !((MainFrame) frame).volumeOn;
			MainFrame.tray.setMute();

		}
	}

	public static void volumeToggleLabelMousePressed(JFrame frame) {
		if (frame instanceof MainFrame) {
			String volumeRegion = Backend.getVolumeRegion(((MainFrame) frame).volume);

			if (!((MainFrame) frame).volumeOn) {
				Backend.loadIcon(((MainFrame) frame).getVolumeToggleLabel(), Images.volumeXMarkBlue, 28, "w");
				Queries.updateVolume(0);

				return;
			}

			switch (volumeRegion) {
				case "high":
					Backend.loadIcon(((MainFrame) frame).getVolumeToggleLabel(), Images.volumeHighBlue, 31, "w");
					break;
				case "low":
					Backend.loadIcon(((MainFrame) frame).getVolumeToggleLabel(), Images.volumeLowBlue, 25, "w");
					break;
				case "off":
					Backend.loadIcon(((MainFrame) frame).getVolumeToggleLabel(), Images.volumeOffBlue, 25, "w");
					break;
				default:

					break;
			}
		}
	}

	public static void resetDatabase(JFrame frame) {
		int option = 0;

		if (!MainFrame.DEBUG) {
			option = JOptionPane.showConfirmDialog(frame, "Are you sure you want to reset your Database? This will erase all imported songs and their data", "Reset DB", JOptionPane.YES_NO_OPTION);
		}

		if (option == 0) {
			System.out.println("Clearing Database...");
			Queries.clearDatabase();
			Backend.updateSongs(frame);
			mainFrame.player.stop();
			mainFrame.player.playingIndex = 0;
			Backend.setDefaultSongInfo(frame);
			Backend.setDefaultSongAdditionalInfo(frame);
			Backend.resetSlider(frame);
			Backend.resetPlayer(frame);
			JOptionPane.showMessageDialog(frame, "Database Cleared", "Success", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public static void refreshDirectories(JFrame frame){
		if (frame instanceof MainFrame) {
			int selectedIndex = mainFrame.getSongsList().getSelectedIndex();
			if(Queries.selectDirectories().isEmpty()) return;
			
			Backend.checkDirectoriesForChanges();
			Backend.updateSongs(frame);	
			mainFrame.player.playlist = mainFrame.list.getPaths();
			mainFrame.getSongsList().setSelectedIndex(selectedIndex);
		}
	}
}
