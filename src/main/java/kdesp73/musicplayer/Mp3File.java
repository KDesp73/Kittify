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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import kdesp73.musicplayer.files.FileOperations;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

/**
 *
 * @author konstantinos
 */
public class Mp3File extends File {

	// Player
	private Player player;
	private boolean isPlaying = false;
	private int pauseTime = 0;

	// File
	private int durationSeconds;
	private String timeOfImport;
	private String extension;
	private String coverPath;

	// API
	private Track track = new Track();

	public Mp3File(String title, String artist, String album, String path) {
		super(path);

		this.track.setName(title);
		this.track.setArtist(artist);
		this.track.setAlbum(album);
	}

	public Mp3File(String pathname) {
		super(pathname);

		this.extension = FileOperations.getExtensionFromPath(this.getAbsolutePath());
		this.timeOfImport = this.calculateTimeOfImport();

		this.track.setName(FileOperations.getJustFilenameFromPath(pathname));
		this.coverPath = (System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/assets/album-image-placeholder.png");
	}

	public void play() {
		String filename = this.getAbsolutePath();
		try {
			FileInputStream fis = new FileInputStream(filename);
			BufferedInputStream bis = new BufferedInputStream(fis);
			player = new Player(bis);

//            fis.close();
//            bis.close();
		} catch (FileNotFoundException | JavaLayerException e) {
			System.out.println("Problem playing file " + filename);
			System.out.println(e);
		} catch (IOException ex) {
			Logger.getLogger(Mp3File.class.getName()).log(Level.SEVERE, null, ex);
		}

		new Thread() {
			public void run() {
				try {
					if (pauseTime > 0) {
						player.play(pauseTime);
					} else {
						player.play();
					}
//                    player.play();

				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}.start();
		isPlaying = true;

	}

	public void pause() {
		if (isPlaying) {
			pauseTime = calculateFrameFromTime(getCurrentTime());
			player.close();
			isPlaying = false;
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
		pauseTime = calculateFrameFromTime(seconds);
		play();
	}

	private int getCurrentTime() {
		int milli = player.getPosition();
		int seconds = (int) (milli / 1000) % 60;

		return seconds;
	}

	private int calculateFrameFromTime(int seconds) {
		try {
			Bitstream bitstream = new Bitstream(new FileInputStream(this.getAbsolutePath()));
			int frame = 0;
			long currentTime = 0;
			while (currentTime < seconds * 1000) { // Convert seconds to milliseconds
				bitstream.readFrame(); // Skip the frame
				currentTime += bitstream.readFrame().ms_per_frame();
				frame++;
			}
			return frame;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void getDurationInSeconds() {

	}

	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getTimeOfImport() {
		return this.timeOfImport;
	}

	public int getDurationSeconds() {
		return durationSeconds;
	}

	public void setDurationSeconds(int durationSeconds) {
		this.durationSeconds = durationSeconds;
	}

	public String getCoverPath() {
		return coverPath;
	}

	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}
	
	

	private String calculateTimeOfImport() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		return formatter.format(date);
	}

	@Override
	public String toString() {
		return "Mp3File{" + "player=" + player + ", isPlaying=" + isPlaying + ", pauseTime=" + pauseTime + ", durationSeconds=" + durationSeconds + ", timeOfImport=" + timeOfImport + ", extension=" + extension + ", coverPath=" + coverPath + ", track=" + track + '}';
	}



}
