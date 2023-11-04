/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer.backend;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileNameExtensionFilter;
import kdesp73.musicplayer.db.Queries;
import kdesp73.musicplayer.gui.EditDirectories;
import kdesp73.musicplayer.gui.EditFiles;
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

	public static void addFileMenuItemActionPerformed(JFrame frame) {
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

		}
	}

	public static void addDirectoryMenuItemActionPerformed(JFrame frame) {
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

			Backend.updateSongs(mainFrame);
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

	public static void editDirectoriesMenuItemActionPerformed(JFrame frame) {
		if (frame instanceof MainFrame) {
			mainFrame.player.stop();
			new EditDirectories(mainFrame).setVisible(true);
		}
	}

	public static void editFilesMenuItemActionPerformed(JFrame frame) {
		if (frame instanceof MainFrame) {
			mainFrame.player.stop();
			new EditFiles(mainFrame).setVisible(true);
		}
	}

	public static void nextAction(JFrame frame) {
		System.out.println("Next Song");
		if (frame instanceof MainFrame) {
			int index = mainFrame.getSongsList().getSelectedIndex();

			if (index == mainFrame.list.getSongs().size() - 1) {
				return;
			}

			if (mainFrame.repeatOn) {
				mainFrame.player.stop();
				mainFrame.player.play();
				Backend.resetSlider(frame);
				return;
			}

			mainFrame.player.timer.stop();
			mainFrame.player.next();
			Backend.selectSong(mainFrame, index + 1);

//			mainFrame.getPlayButton().setText("Pause");
			Backend.loadIcon(mainFrame.getPlayPauseLabel(), assets + ((Queries.selectTheme().equals("Light")) ? "circle-pause-solid.png" : "circle-pause-solid-white.png"), new Dimension(50, 50));

		}
	}

	public static void playSong(JFrame frame) {
		if (frame instanceof MainFrame) {
			mainFrame.currentIndex = mainFrame.getSongsList().getSelectedIndex();
			mainFrame.currentSong = mainFrame.list.getSongs().get(mainFrame.currentIndex);

			mainFrame.player.stop();
			mainFrame.player.timer.stop();

			Backend.selectSong(mainFrame);

			mainFrame.player.play(mainFrame.getSongsList().getSelectedIndex());
//			mainFrame.getPlayButton().setText("Pause");
			Backend.loadIcon(mainFrame.getPlayPauseLabel(), assets + ((Queries.selectTheme().equals("Light")) ? "circle-pause-solid.png" : "circle-pause-solid-white.png"), new Dimension(50, 50));

		}
	}

	public static void togglePlayPause(JFrame frame) {
		if (frame instanceof MainFrame) {
			mainFrame.currentSong = mainFrame.list.getSongs().get(mainFrame.currentIndex);
			if (mainFrame.player.isPlaying()) {
				mainFrame.player.pause();
				Backend.loadIcon(mainFrame.getPlayPauseLabel(), assets + ((Queries.selectTheme().equals("Light")) ? "circle-play-solid.png" : "circle-play-solid-white.png"), new Dimension(50, 50));
			} else {
				mainFrame.player.play();
				Backend.loadIcon(mainFrame.getPlayPauseLabel(), assets + ((Queries.selectTheme().equals("Light")) ? "circle-pause-solid.png" : "circle-pause-solid-white.png"), new Dimension(50, 50));
			}
		}
	}

	public static void prevAction(JFrame frame) {
		if (frame instanceof MainFrame) {
			int index = mainFrame.getSongsList().getSelectedIndex();

			if (index == 0) {
				return;
			}

			if (mainFrame.repeatOn) {
				mainFrame.player.stop();
				mainFrame.player.play();
				Backend.resetSlider(frame);
				return;
			}

			mainFrame.player.timer.stop();
			mainFrame.player.prev();
			Backend.selectSong(mainFrame, index - 1);

//			mainFrame.getPlayButton().setText("Pause");
			Backend.loadIcon(mainFrame.getPlayPauseLabel(), assets + ((Queries.selectTheme().equals("Light")) ? "circle-pause-solid.png" : "circle-pause-solid-white.png"), new Dimension(50, 50));

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

			System.out.println("Skip: " + skip);

			mainFrame.player.seek(skip);
//			mainFrame.player.resume();
		}
	}

	public static void showOptionsPopup(JFrame frame, MouseEvent evt) {
		if (frame instanceof MainFrame) {
			JPopupMenu options = new JPopupMenu();
			JMenuItem edit = new JMenuItem("Edit");
			JMenuItem scrape = new JMenuItem("Scrape");
			JMenuItem downloadAlbumCover = new JMenuItem("Download Cover");

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
								Backend.downloadAlbumCoverAction(mainFrame);
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

			if (mainFrame.currentSong != null) {
				options.show(evt.getComponent(), evt.getX(), evt.getY());
			}
		}
	}

	public static void searchButtonMouseClicked(JFrame frame) {
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

//			Backend.selectSong(mainFrame, index);
			mainFrame.getSongsList().ensureIndexIsVisible(index);
		}
	}

	public static void scrapeAllMenuItemActionPerformed(JFrame frame) {
		if (frame instanceof MainFrame) {
			mainFrame.list.scrapeSongs();
		}
	}

	public static void scrapeAtStartMenuItemActionPerformed(JFrame frame) {
		if (frame instanceof MainFrame) {
			boolean flag = mainFrame.getScrapeAtStartMenuItem().isSelected();

			Queries.updateScrapeAtStart(flag);
			mainFrame.scrapeAtStart = flag;
		}
	}

	public static void shuffle(JFrame frame) {
		if (frame instanceof MainFrame) {

			if (mainFrame.shuffleOn) {
				Backend.sort(frame);
				Backend.loadIcon(mainFrame.getShuffleLabel(), assets + ((Queries.selectTheme().equals("Light")) ? "shuffle-solid.png" : "shuffle-solid-white.png"), new Dimension(30, 30));
			} else {
				Backend.shuffleList(frame);
				Backend.loadIcon(mainFrame.getShuffleLabel(), assets + "shuffle-solid-blue.png", new Dimension(30, 30));
			}

			mainFrame.shuffleOn = !mainFrame.shuffleOn;
			Queries.updateShuffle(mainFrame.shuffleOn);
		}
	}

	public static void repeat(JFrame frame) {
		if (frame instanceof MainFrame) {
			if (mainFrame.repeatOn) {
				// Handle repeat
				Backend.loadIcon(mainFrame.getRepeatLabel(), assets + ((Queries.selectTheme().equals("Light")) ? "repeat-solid.png" : "repeat-solid-white.png"), new Dimension(30, 30));
			} else {
				// Handle repeat
				Backend.loadIcon(mainFrame.getRepeatLabel(), assets + "repeat-solid-blue.png", new Dimension(30, 30));
			}

			mainFrame.repeatOn = !mainFrame.repeatOn;
			Queries.updateRepeat(mainFrame.repeatOn);
		}
	}
}
