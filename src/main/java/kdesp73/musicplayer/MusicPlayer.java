/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package kdesp73.musicplayer;

import javax.swing.DefaultListModel;
import kdesp73.musicplayer.db.Queries;
import kdesp73.musicplayer.files.FileOperations;

/**
 *
 * @author konstantinos
 */
public class MusicPlayer {
    static SongsList list;

    private static void refreshSongs() {
        try {
            list = new SongsList(Queries.getDirectories(), Queries.getFiles());
        } catch (java.lang.StringIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FileOperations.acceptedExtensions.add("mp3");
        FileOperations.acceptedExtensions.add("wav");
        FileOperations.acceptedExtensions.add("mpeg");
        refreshSongs();

        
        for (Mp3File file : list.getSongs()) {
            System.out.println(file.getName());
        }
        
        list.sortByName();
        
        for (Mp3File file : list.getSongs()) {
            System.out.println(file.getName());
        }
        
    }
}
