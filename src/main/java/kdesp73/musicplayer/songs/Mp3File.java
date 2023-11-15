/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer.songs;

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
import kdesp73.musicplayer.api.API;
import kdesp73.musicplayer.api.Album;
import kdesp73.musicplayer.api.Artist;
import kdesp73.musicplayer.api.LastFmMethods;
import kdesp73.musicplayer.api.Pair;
import kdesp73.musicplayer.backend.Backend;
import kdesp73.musicplayer.db.Queries;
import kdesp73.musicplayer.gui.MainFrame;

/**
 *
 * @author konstantinos
 */
public class Mp3File extends File {
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
		this.coverPath = null;
		this.timeOfImport = this.calculateTimeOfImport();
		this.extension = FileOperations.getExtensionFromPath(this.getAbsolutePath());

		if (metadata == null) {
			return;
		}

		if (title == null || title.isBlank()) {

			String metadataTitle = (String) metadata.get("title");
			if (metadataTitle != null && !metadataTitle.isBlank()) {
				this.track.setName(metadataTitle);
			}
		}

		if (artist == null || artist.isBlank() || artist.toLowerCase().equals("unknown artist") || artist.toLowerCase().equals("άγνωστος καλλιτέχνης")) {

			String metadataArtist = (String) metadata.get("artist");
			if (metadataArtist != null && !metadataArtist.isBlank()) {
				this.track.setArtist(metadataArtist);
			}
		}

		if (album == null || album.isBlank() || album.toLowerCase().equals("unknown album") || album.toLowerCase().equals("άγνωστο άλμπουμ")) {

			String metadataAlbum = (String) metadata.get("album");
			if (metadataAlbum != null && !metadataAlbum.isBlank()) {
				this.track.setAlbum(metadataAlbum);
			}
		}
	}
	public Mp3File(String title, String artist, String album, String time_of_import, String path) {
		super(path);

		this.track.setName(title);
		this.track.setArtist(artist);
		this.track.setAlbum(album);
		this.metadata = getMetadata();
		this.coverPath = null;
		this.timeOfImport = time_of_import;
		this.extension = FileOperations.getExtensionFromPath(this.getAbsolutePath());

		if (metadata == null) {
			return;
		}

		if (title == null || title.isBlank()) {

			String metadataTitle = (String) metadata.get("title");
			if (metadataTitle != null && !metadataTitle.isBlank()) {
				this.track.setName(metadataTitle);
			}
		}

		if (artist == null || artist.isBlank() || artist.toLowerCase().equals("unknown artist") || artist.toLowerCase().equals("άγνωστος καλλιτέχνης")) {

			String metadataArtist = (String) metadata.get("artist");
			if (metadataArtist != null && !metadataArtist.isBlank()) {
				this.track.setArtist(metadataArtist);
			}
		}

		if (album == null || album.isBlank() || album.toLowerCase().equals("unknown album") || album.toLowerCase().equals("άγνωστο άλμπουμ")) {

			String metadataAlbum = (String) metadata.get("album");
			if (metadataAlbum != null && !metadataAlbum.isBlank()) {
				this.track.setAlbum(metadataAlbum);
			}
		}
	}

	public Mp3File(String pathname) {
		super(pathname);

		this.extension = FileOperations.getExtensionFromPath(this.getAbsolutePath());
		this.timeOfImport = this.calculateTimeOfImport();
		this.track.setName(FileOperations.getJustFilenameFromPath(pathname));
//		this.track.setName(Backend.cleanTextContent(FileOperations.getJustFilenameFromPath(pathname)));
		this.coverPath = null;
		this.metadata = getMetadata();

		if (metadata == null) {
			return;
		}

		String metadataTitle = (String) metadata.get("title");
		String metadataArtist = (String) metadata.get("artist");
		String metadataAlbum = (String) metadata.get("album");

		if (metadataTitle != null && !metadataTitle.isBlank()) {
			this.track.setName(metadataTitle);
		}

		if (metadataArtist != null && !metadataArtist.isBlank()) {
			this.track.setArtist(metadataArtist);
		}

		if (metadataAlbum != null && !metadataAlbum.isBlank()) {
			this.track.setAlbum(metadataAlbum);
		}
	}

	// =============METADATA =============
	public HashMap<String, Object> getMetadata() {
		File inputFile = new File(this.getAbsolutePath());

		if (!inputFile.exists()) {
			return null;
		}

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
		m.put("bit-rate", audioHeader.getBitRate());
		m.put("sample-rate", audioHeader.getSampleRate());
		m.put("channel-count", audioHeader.getChannelCount());

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
			return 0;
		}

		return (long) metadata.get("duration");
	}

	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}
	
	public void setName(String name){
		this.track.setName(name);
		setMetadata(FieldKey.TITLE, name);
	}
	
	public void setArtist(String artist){
		this.track.setArtist(artist);
		setMetadata(FieldKey.ARTIST, artist);
	}
	
	public void setAlbum(String album){
		this.track.setAlbum(album);
		setMetadata(FieldKey.ALBUM, album);
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

	public int getDurationSeconds() {
		return durationSeconds;
	}

	public void setDurationSeconds(int durationSeconds) {
		this.durationSeconds = durationSeconds;
	}

	public boolean isScraped() {
		return Queries.selectScraped(this.getAbsolutePath());
	}
	
	public void setScraped(boolean scraped){
		Queries.updateScraped(scraped, this.getAbsolutePath());
	}

	private String calculateTimeOfImport() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		return formatter.format(date);
	}

	public void selfScrape() {
		if(isScraped()) return;
		
		Mp3File file = this;

		API api = new API();
		String artist = file.getTrack().getArtist();
		String title = file.getTrack().getName();
		String album = file.getTrack().getAlbum();

		Pair<String, Integer> response = null;

		if (artist == null || "Unknown Artist".equals(artist)) {
			return;
		}

		// Make an API call using title and artist (LastFmMethods.Track.getInfo)
		try {
			response = api.GET(LastFmMethods.Track.getInfo, LastFmMethods.Track.tags(artist, title));
		} catch (IOException | InterruptedException ex) {
			Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
			return;
		}

		if (response.second != 200) {
			return;
		}

		System.out.println(response.first);

		if ("{\"error\":6,\"message\":\"Track not found\",\"links\":[]}".equals(response.first)) {
			setScraped(false);
			return;
		}

		// Update the Song
		Track track = new Track(response.first);

		if(album != null && !album.isBlank() && (track.getAlbum() == null || track.getAlbum().isBlank())){
			file.setAlbum(album);
		}
		
		file.setTrack(track);
		Queries.updateSong(file);
			
		// Scrape for the Album if not scraped already
		if (file.getTrack().getAlbum() != null && !file.getTrack().getAlbum().isBlank()) {
			Album albumO = Queries.selectAlbum(file.getTrack().getAlbum(), artist);
			
			if (albumO == null) {
				try {
					response = api.GET(LastFmMethods.Album.getInfo, LastFmMethods.Album.tags(artist, file.getTrack().getAlbum()));
				} catch (IOException | InterruptedException ex) {
					System.err.println("Album scrape fail");
				}

				if (response != null) {
					albumO = new Album(response.first);
					Queries.insertAlbum(albumO);
				}
			}
		}

		// Scrape for the Artist if not scraped already
		Artist artistO = Queries.selectArtist(artist);

		if (artistO == null) {
			try {
				response = api.GET(LastFmMethods.Artist.getInfo, LastFmMethods.Artist.tags(artist));
			} catch (IOException | InterruptedException ex) {
				System.err.println("Album scrape fail");
			}

			if (response != null) {
				artistO = new Artist(response.first);
				Queries.insertArtist(artistO);
			}
		}
		
		setScraped(true);
		Queries.updateScraped(true, file.getAbsolutePath());
	}

	@Override
	public String toString() {
		return "Mp3File{" + "durationSeconds=" + durationSeconds + ", timeOfImport=" + timeOfImport + ", extension=" + extension + ", coverPath=" + coverPath + ", metadata=" + metadata + ", track=" + track + '}';
	}

	
}
