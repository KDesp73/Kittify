/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer;

import java.io.File;
import kdesp73.musicplayer.files.FileOperations;

/**
 *
 * @author konstantinos
 */
public class Mp3File extends File {
    private int durationSeconds;
    private int timeOfImport;
    private String extension;
    private String name;
    
    private Track track;
    private Album album;
    private Artist artist;

    public Mp3File(String pathname) {
        super(pathname);
        
        this.extension = FileOperations.getExtensionFromPath(this.getAbsolutePath());
        this.timeOfImport = 0; // TODO
        this.name = FileOperations.getJustFilenameFromPath(pathname);
    }
    

    public int getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(int durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
    
    public int getTimeOfImport() {
        return timeOfImport;
    }

    public void setTimeOfImport(int timeOfImport) {
        this.timeOfImport = timeOfImport;
    }

    public String getJustName() {
        return name;
    }
    
}
