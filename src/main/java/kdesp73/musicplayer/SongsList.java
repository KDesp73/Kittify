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
        for(int i = 0; i < list.size(); i++){
            for(int j = i+1; j < list.size(); j++){
                if(list.get(i).getJustName().compareTo(list.get(j).getJustName()) > 0){
                    Mp3File temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }

    public void sortByTime() {
        for(int i = 0; i < list.size(); i++){
            for(int j = i+1; j < list.size(); j++){
                if(list.get(i).getTimeOfImport() > list.get(j).getTimeOfImport()){
                    Mp3File temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "SongsList{" + "list=" + list + '}';
    }
    
    
}
