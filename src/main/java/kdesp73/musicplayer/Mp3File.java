/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import kdesp73.musicplayer.files.FileOperations;
import java.text.SimpleDateFormat;
import java.util.Date;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author konstantinos
 */
public class Mp3File extends File {
    // Player
    private AdvancedPlayer player;
    private boolean isPlaying = false;
    private long pauseTime = 0;

    // File
    private int durationSeconds;
    private String timeOfImport;
    private String extension;
    private String name;

    // API
    private Track track;
    private Album album;
    private Artist artist;

    public Mp3File(String pathname) {
        super(pathname);

        this.extension = FileOperations.getExtensionFromPath(this.getAbsolutePath());
        this.timeOfImport = this.getTimeOfImport();
        this.name = FileOperations.getJustFilenameFromPath(pathname);
    }


    public void play() {
        String filename = this.getAbsolutePath();
        try {
            FileInputStream fis = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new AdvancedPlayer(bis);
        } catch (FileNotFoundException | JavaLayerException e) {
            System.out.println("Problem playing file " + filename);
            System.out.println(e);
        }

        // run in new thread to play in background
        new Thread() {
            public void run() {
                try {
                    player.play();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }.start();
        
        isPlaying = true;
    }

    public void pause() {
        if (isPlaying) {
            player.close();
            isPlaying = false;
            pauseTime = 0;
        }
    }

    public void stop() {
        if (isPlaying) {
            player.close();
            isPlaying = false;
            pauseTime = 0;
        }
    }

    public void seek(int seconds) {
        if (isPlaying) {
            pause();
        }
        pauseTime = seconds;
        play();
    }

    public int getDurationSeconds() {
        File file = new File(this.getAbsolutePath());
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(file);
        } catch (UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger(Mp3File.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (audioInputStream == null) {
            return -1;
        }

        AudioFormat format = audioInputStream.getFormat();
        long frames = audioInputStream.getFrameLength();
        double durationInSeconds = (frames + 0.0) / format.getFrameRate();

        this.durationSeconds = (int) durationInSeconds;
        return (int) durationInSeconds;
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

    public String getTimeOfImport() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }

    public String getJustName() {
        return name;
    }

}
