/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer;

import javax.swing.UIManager;
import kdesp73.musicplayer.gui.MainFrame;
import kdesp73.musicplayer.tray.Tray;

/**
 *
 * @author konstantinos
 */
public class Main {

	public static void main(String[] args) {
		System.setProperty("sun.java2d.uiScale", "1");
		try {
//			UIManager.setLookAndFeel(new com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneDarkIJTheme());
			UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
		} catch (Exception ex) {
			System.err.println("Failed to initialize LaF");
		}

		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				MainFrame frame = MainFrame.create();
				frame.setVisible(true);

				new Tray(frame);
			}
		});
	}

}
