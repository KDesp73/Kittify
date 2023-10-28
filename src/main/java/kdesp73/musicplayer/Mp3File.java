/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer;

import kdesp73.musicplayer.api.Track;
import ealvatag.audio.AudioFile;
import ealvatag.audio.AudioFileIO;
import ealvatag.audio.AudioHeader;
import ealvatag.audio.exceptions.CannotReadException;
import ealvatag.audio.exceptions.CannotWriteException;
import ealvatag.audio.exceptions.InvalidAudioFrameException;
import ealvatag.tag.FieldDataInvalidException;
import ealvatag.tag.FieldKey;
import ealvatag.tag.NullTag;
import ealvatag.tag.Tag;
import ealvatag.tag.TagException;
import ealvatag.tag.UnsupportedFieldException;
import java.io.File;
import java.io.IOException;
import kdesp73.musicplayer.files.FileOperations;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javazoom.jl.player.advanced.AdvancedPlayer;

/**
 *
 * @author konstantinos
 */
public class Mp3File extends File {

	// Player
	private AdvancedPlayer player;
	private boolean isPlaying = false;
	private int pausedOnFrame = 0;
	private int currentFrame = 0;

	// File
	private int durationSeconds;
	private String timeOfImport;
	private String extension;
	private String coverPath;
	private HashMap<String, Object> metadata;

	// API
	private Track track = new Track();
	
	public Mp3File(String title, String artist, String album, String path) {
		super(path);

		this.track.setName(title);
		this.track.setArtist(artist);
		this.track.setAlbum(album);
		this.metadata = getMetadata();
		this.coverPath = (System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/assets/album-image-placeholder.png");
		this.timeOfImport = this.calculateTimeOfImport();
		this.extension = FileOperations.getExtensionFromPath(this.getAbsolutePath());

	}

	public Mp3File(String pathname) {
		super(pathname);

		this.extension = FileOperations.getExtensionFromPath(this.getAbsolutePath());
		this.timeOfImport = this.calculateTimeOfImport();
		this.track.setName(FileOperations.getJustFilenameFromPath(pathname));
		this.coverPath = (System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/assets/album-image-placeholder.png");
		this.metadata = getMetadata();
		
		if(metadata == null) return;
		
		String metadataTitle = (String) metadata.get("title");
		String metadataArtist = (String) metadata.get("artist");
		String metadataAlbum = (String) metadata.get("album");
		
		if(metadataTitle != null && !metadataTitle.isBlank()){
			this.track.setName(metadataTitle);
		}
		
		if(metadataArtist != null && !metadataArtist.isBlank()){
			this.track.setArtist(metadataArtist);
		}
		
		if(metadataAlbum != null && !metadataAlbum.isBlank()){
			this.track.setAlbum(metadataAlbum);
		}
		
	}

	// =============METADATA =============
	public HashMap<String, Object> getMetadata() {
		File inputFile = new File(this.getAbsolutePath());
		
		if(!inputFile.exists()) return null;
		
		AudioFile audioFile = null;
		try {
			audioFile = AudioFileIO.read(inputFile);
		} catch (CannotReadException | IOException | TagException | InvalidAudioFrameException ex) {
			Logger.getLogger(Mp3File.class.getName()).log(Level.SEVERE, null, ex);
		}

		if (audioFile == null) {
			return null;
		}

		final AudioHeader audioHeader = audioFile.getAudioHeader();
		Tag tag = audioFile.getTag().or(NullTag.INSTANCE);

		HashMap<String, Object> m = new HashMap<>();

		m.put("title", tag.getValue(FieldKey.TITLE).or(""));
		m.put("artist", tag.getValue(FieldKey.ARTIST).or(""));
		m.put("album", tag.getValue(FieldKey.ALBUM).or(""));
		m.put("duration", audioHeader.getDuration(TimeUnit.SECONDS, false));

		return m;
	}

	public void setMetadata(FieldKey fk, String value) {
		File inputFile = new File(this.getAbsolutePath());
		AudioFile audioFile = null;
		try {
			audioFile = AudioFileIO.read(inputFile);
		} catch (CannotReadException | IOException | TagException | InvalidAudioFrameException ex) {
			Logger.getLogger(Mp3File.class.getName()).log(Level.SEVERE, null, ex);
		}

		if (audioFile == null) {
			return;
		}

		Tag tag = audioFile.getTag().or(NullTag.INSTANCE);

		try {
			tag.setField(fk, value);
		} catch (IllegalArgumentException | UnsupportedFieldException | FieldDataInvalidException ex) {
			Logger.getLogger(Mp3File.class.getName()).log(Level.SEVERE, null, ex);
		}

		try {
			audioFile.save();
		} catch (CannotWriteException ex) {
			Logger.getLogger(Mp3File.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public long getDurationInSeconds() {
		if (metadata == null) {
			System.err.println("Metadata is null");
			return -1;
		}

		return (long) metadata.get("duration");
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
		return "Mp3File{" + "player=" + player + ", isPlaying=" + isPlaying + ", pauseTime=" + pausedOnFrame + ", durationSeconds=" + durationSeconds + ", timeOfImport=" + timeOfImport + ", extension=" + extension + ", coverPath=" + coverPath + ", track=" + track + '}';
	}

}
