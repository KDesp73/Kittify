/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import kdesp73.databridge.connections.DatabaseConnection;
import kdesp73.databridge.helpers.QueryBuilder;
import kdesp73.musicplayer.db.Database;
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

        addToDatabase();
    }

    private void addToDatabase() {
        DatabaseConnection db = Database.connection();
        QueryBuilder builder = new QueryBuilder();
        for (Mp3File file : list) {
            boolean dontAdd = false;
            ResultSet rs = db.executeQuery(builder.select("path").from("Songs").build());
            try {
                while (rs.next()) {
                    if (rs.getString(1).equals(file.getAbsolutePath())) {
                        dontAdd = true;
                        break;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(SongsList.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(!dontAdd)
                db.executeUpdate(builder.insertInto("Songs").columns("path", "title").values(file.getAbsolutePath().replaceAll(Pattern.quote("\'"), "\'\'"), file.getTrack().getName().replaceAll(Pattern.quote("\'"), "\'\'")).build());
        }
        db.close();
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
        
        addToDatabase();
    }
    
    public void addSong(Mp3File file) {
        list.add(file);
    }

    public ArrayList<Mp3File> getSongs() {
        return list;
    }

    public void sortByName() {
        list.sort((o1, o2) -> o1.getTrack().getName().compareTo(o2.getTrack().getName()));
    }
    public void sortByArtist() {
        list.sort((o1, o2) -> o1.getArtist().getName().compareTo(o2.getArtist().getName()));
    }
    public void sortByAlbum() {
        list.sort((o1, o2) -> o1.getAlbum().getName().compareTo(o2.getAlbum().getName()));
    }

    public void sortByTime() {
        list.sort((o1, o2) -> o2.getTimeOfImport().compareTo(o1.getTimeOfImport()));
    }

    @Override
    public String toString() {
        return "SongsList{" + "list=" + list + '}';
    }

}
