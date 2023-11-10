package kdesp73.musicplayer.tray;

import java.awt.event.ActionListener;
import dorkbox.systemTray.Menu;
import dorkbox.systemTray.MenuItem;
import dorkbox.util.CacheUtil;

import dorkbox.systemTray.SystemTray;
import javax.swing.JSeparator;
import kdesp73.musicplayer.backend.UIFunctionality;
import kdesp73.musicplayer.gui.MainFrame;

public class Tray {

	private MainFrame frame;
	private SystemTray systemTray;
	private ActionListener actionListener;

	public Tray(MainFrame frame) {
		this.frame = frame;

		SystemTray.DEBUG = true;

		CacheUtil.clear("MusicPlayerTray");

		systemTray = SystemTray.get("MusicPlayerTray");
		if (systemTray == null) {
			throw new RuntimeException("Unable to load SystemTray!");
		}

		systemTray.setTooltip("Music Player");
//		systemTray.setImage();
		systemTray.setStatus("Stopped");

		actionListener = e -> {
			final MenuItem entry = (MenuItem) e.getSource();

			entry.setCallback(null);
			systemTray.getMenu().remove(entry);
			entry.remove();
		};

		Menu menu = systemTray.getMenu();
		
		MenuItem playPause = new MenuItem(frame.player.isPlaying() ? "Pause" : "Play", e -> {
			final MenuItem item = (MenuItem) e.getSource();
			if (!frame.player.isPlaying()) {
				item.setText("Pause");
				systemTray.setStatus("Playing");
			} else {
				item.setText("Play");
				systemTray.setStatus("Paused");
			}
			UIFunctionality.togglePlayPause(frame);

		});
		
		menu.add(playPause);

		menu.add(new MenuItem("Next", e -> {
			systemTray.setStatus("Playing");
			UIFunctionality.nextAction(frame);
			playPause.setText("Pause");
		}));

		menu.add(new MenuItem("Previous", e -> {
			systemTray.setStatus("Playing");
			UIFunctionality.prevAction(frame);
			playPause.setText("Pause");
		}));

		menu.add(new MenuItem("Mute", e -> {
			final MenuItem item = (MenuItem) e.getSource();
			if (!frame.volumeOn) {
				item.setText("Mute");
			} else {
				item.setText("Unmute");
			}
			UIFunctionality.volumeToggle(frame);
		}));

		menu.add(new JSeparator());
//		menu.add(new MenuItem("About", e -> {
//			try {
//				Desktop.browseURL("https://git.dorkbox.com/dorkbox/SystemTray");
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//		}));

		menu.add(new MenuItem("Quit", e -> {
			systemTray.shutdown();
			frame.player.stop();
			frame.dispose();
			System.exit(0);
		})).setShortcut('q'); // case does not matter

	}
	
	public void setPlayPause(){
		((MenuItem) systemTray.getMenu().getFirst()).setText(frame.player.isPlaying() ? "Pause" : "Play");
	}

}
