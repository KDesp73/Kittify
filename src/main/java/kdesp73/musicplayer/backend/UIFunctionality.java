/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer.backend;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
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
			Queries.updateSortBy(Backend.sort(mainFrame));

			mainFrame.player.playlist = ((MainFrame) frame).list.getPaths();
		}
	}

	public static void editDirectoriesMenuItemActionPerformed(JFrame frame) {
		if (frame instanceof MainFrame) {
			new EditDirectories(mainFrame).setVisible(true);
		}
	}

	public static void editFilesMenuItemActionPerformed(JFrame frame) {
		if (frame instanceof MainFrame) {
			new EditFiles(mainFrame).setVisible(true);
		}
	}

	public static void nextButtonActionPerformed(JFrame frame) {
		if (frame instanceof MainFrame) {
			int index = mainFrame.getSongsList().getSelectedIndex();

			if (index == mainFrame.list.getSongs().size() - 1) {
				return;
			}

			mainFrame.player.timer.stop();
			mainFrame.player.next();
			Backend.selectSong(mainFrame, index + 1);

			mainFrame.getPlayButton().setText("Pause");
		}
	}

	public static void playSong(JFrame frame) {
		if (frame instanceof MainFrame) {

			mainFrame.player.timer.stop();

			Backend.selectSong(mainFrame);

			mainFrame.player.play(mainFrame.getSongsList().getSelectedIndex());
			mainFrame.getPlayButton().setText("Pause");
		}
	}

	public static void playButtonMouseClicked(JFrame frame) {
		if (frame instanceof MainFrame) {
			mainFrame.currentSong = mainFrame.list.getSongs().get(mainFrame.currentIndex);
			if (mainFrame.player.isPlaying()) {
				mainFrame.player.pause();
				mainFrame.getPlayButton().setText("Play");
			} else {
				mainFrame.player.play();
				mainFrame.getPlayButton().setText("Pause");
			}
		}
	}

	public static void songsListKeyPressed(JFrame frame, KeyEvent evt) {
		if (evt.getKeyChar() == '\n') {
			playSong(frame);
		}
	}

	public static void prevButtonMouseClicked(JFrame frame) {
		if (frame instanceof MainFrame) {
			int index = mainFrame.getSongsList().getSelectedIndex();

			if (index == 0) {
				return;
			}

			mainFrame.player.timer.stop();
			mainFrame.player.prev();
			Backend.selectSong(mainFrame, index - 1);

			mainFrame.getPlayButton().setText("Pause");
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

}
