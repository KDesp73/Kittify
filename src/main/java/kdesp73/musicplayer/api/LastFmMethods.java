/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer.api;

/**
 *
 * @author konstantinos
 */
public final class LastFmMethods {
    public class Album {
        public static String tags(String artist, String album){
            return "&artist=" + artist + "&album=" + album;
        }
        
        public static String addTags = "album.addtags";
        public static String getInfo  = "album.getinfo";
        public static String getTags  = "album.gettags";
        public static String getTopTags  = "album.gettoptags";
        public static String removeTag  = "album.removetag";
        public static String search = "album.search";
    }

    public class Artist {
        public static String tags(String artist){
            return "&artist=" + artist;
        }

        public static String addTags = "artist.addtags";
        public static String getCorrection = "artist.getcorrection";
        public static String getInfo = "artist.getinfo";
        public static String getSimilar = "artist.getsimilar";
        public static String getTags = "artist.gettags";
        public static String getTopAlbums = "artist.gettopalbums";
        public static String getTopTags = "artist.gettoptags";
        public static String getTopTracks = "artist.gettoptracks";
        public static String removeTag =  "artist.removetag";
        public static String search =  "artist.search";
    }
    
    public class Auth {
        public static String getMobileSession = "auth.getmobilesession";
        public static String getSession = "auth.getsession";
        public static String getToken = "auth.gettoken";
    }

    public class Geo {
        public static String tags(String country) {
            return "&country=" + country;
        }

        public static String getTopArtists  = "geo.gettopartists";
        public static String getTopTracks  = "geo.gettoptracks";
    }

    public class Library {
        public static String tags(String user) {
            return "&user=" + user;
        }

        public static String getArtists = "library.getartists";
    }

    public class Tag {
        public static String tags(String tag) {
            return "&tag=" + tag;
        }

        public static String tags(String artist, String user){
            return "&artist=" + artist + "&user=" + user;
        }

        public static String getInfo = "tag.getinfo";
        public static String getSimilar = "tag.getsimilar";
        public static String getTopAlbums = "tag.gettopalbums";
        public static String getTopArtists = "tag.gettopartists";
        public static String getTopTags = "tag.gettoptags";
        public static String getTopTracks = "tag.gettoptracks";
        public static String getWeeklyChartList = "tag.getweeklychartlist";
    }

    public class Track {
        public static String tags(String artist, String track) {
            return "&artist=" + artist + "&track=" + track;
        }

        public static String tags(String track) {
            return "&track=" + track;
        }

        public static String addTags = "track.addtags";
        public static String getCorrection = "track.getcorrection";
        public static String getInfo = "track.getinfo";
        public static String getSimilar = "track.getsimilar";
        public static String getTags = "track.gettags";
        public static String getTopTags = "track.gettoptags";
        public static String love = "track.love";
        public static String removeTag =  "track.removetag";
        public static String scrobble = "track.scrobble";
        public static String search =  "track.search";
        public static String unlove = "track.unlove";
        public static String updateNowPlaying = "track.updatenowplaying";
    }

    public class User {
        public static String tags(String user) {
            return "&user=" + user;
        }

        public static String getFriends = "user.getfriends";
        public static String getInfo = "user.getinfo";
        public static String getLovedTracks = "user.getlovedtracks";
        public static String getPersonalTags = "user.getpersonaltags";
        public static String getRecentTracks = "user.getrecenttracks";
        public static String getTopAlbums = "user.gettopalbums";
        public static String getTopArtists = "user.gettopartists";
        public static String getTopTags = "user.gettoptags";
        public static String getTopTracks = "user.gettoptracks";
        public static String getWeeklyAlbumChart = "user.getweeklyalbumchart";
        public static String getWeeklyArtistChart = "user.getweeklyartistchart";
        public static String getWeeklyChartList = "user.getweeklychartlist";
        public static String getWeeklyTrackChart = "user.getweeklytrackchart";
    
    }
}
