/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kdesp73.musicplayer.files.FileOperations;

/**
 *
 * @author konstantinos
 */
public class SongsList {

    private ArrayList<Mp3File> list = new ArrayList<>();

    public SongsList(SongsList copy) {
        this.list = copy.list;
    }

    public SongsList(List<String> paths) {
        for (String path : paths) {
            try {
                List<String> dirs = FileOperations.findFiles(path);
                for (String dir : dirs) {
                    list.add(new Mp3File(dir));
                }

            } catch (IOException ex) {
                Logger.getLogger(SongsList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public SongsList(List<String> paths, List<String> files) {
        for (String path : paths) {
            try {
                List<String> dirs = FileOperations.findFiles(path);
                for (String dir : dirs) {
                    list.add(new Mp3File(dir));
                }
            } catch (IOException ex) {
                Logger.getLogger(SongsList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (String file : files) {
            list.add(new Mp3File(file));
        }
    }

    public void addSong(Mp3File file) {
        list.add(file);
    }

    public ArrayList<Mp3File> getSongs() {
        return list;
    }

    public void sortByName() {
        list.sort((o1, o2) -> o1.getJustName().compareTo(o2.getJustName()));
    }

    public void sortByTime() {
        list.sort((o1, o2) -> o2.getTimeOfImport().compareTo(o1.getTimeOfImport()));
    }

    @Override
    public String toString() {
        return "SongsList{" + "list=" + list + '}';
    }

}
