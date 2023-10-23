/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer;

import dorkbox.systemTray.SystemTray;

/**
 *
 * @author konstantinos
 */
public class Tray {

    public static void main(String[] args) {
        SystemTray tray = SystemTray.get("Music Player");
        
        tray.setEnabled(true);
    }
}
