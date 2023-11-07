/*
 * Copyright 2021 dorkbox, llc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
			System.err.println("POW");
		};

		Menu menu = systemTray.getMenu();

		MenuItem playPause = new MenuItem("Play", e -> {
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

}
