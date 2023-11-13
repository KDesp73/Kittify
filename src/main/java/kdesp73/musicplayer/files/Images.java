/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer.files;

import java.util.regex.Pattern;

/**
 *
 * @author konstantinos
 */
public class Images {
	private static final String assets = System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/assets/";
	
	public static String albumPlaceholder = assets + "album-image-placeholder.png";
	public static String artistPlaceholder = assets + "artist-image-placeholder.png";
	
	public static String prev = assets + "backward-step-solid.png";
	public static String prevBlue = assets + "backward-step-solid-blue.png";
	public static String prevWhite = assets + "backward-step-solid-white.png";
	
	public static String next = assets + "forward-step-solid.png";
	public static String nextBlue = assets + "forward-step-solid-blue.png";
	public static String nextWhite = assets + "forward-step-solid-white.png";
	
	public static String play = assets + "circle-play-solid.png";
	public static String playWhite = assets + "circle-play-solid-white.png";

	public static String pause = assets + "circle-pause-solid.png";
	public static String pauseWhite = assets + "circle-pause-solid-white.png";
	
	public static String repeat = assets + "repeat-solid.png";
	public static String repeatBlue = assets + "repeat-solid-blue.png";
	public static String repeatWhite = assets + "repeat-solid-white.png";
	
	public static String shuffle = assets + "shuffle-solid.png";
	public static String shuffleBlue = assets + "shuffle-solid-blue.png";
	public static String shuffleWhite = assets + "shuffle-solid-white.png";
	
	public static String volumeHigh = assets + "volume-high-solid.png";
	public static String volumeHighBlue = assets + "volume-high-solid-blue.png";
	public static String volumeHighWhite = assets + "volume-high-solid-white.png";
	
	public static String volumeLow = assets + "volume-low-solid.png";
	public static String volumeLowBlue = assets + "volume-low-solid-blue.png";
	public static String volumeLowWhite = assets + "volume-low-solid-white.png";
	
	public static String volumeOff = assets + "volume-off-solid.png";
	public static String volumeOffBlue = assets + "volume-off-solid-blue.png";
	public static String volumeOffWhite = assets + "volume-off-solid-white.png";
	
	public static String volumeXMark = assets + "volume-xmark-solid.png";
	public static String volumeXMarkBlue = assets + "volume-xmark-solid-blue.png";
	public static String volumeXMarkWhite = assets + "volume-xmark-solid-white.png";
	
	public static String developer = assets + "code-solid.png";
	public static String developerWhite = assets + "code-solid-white.png";
	
	public static String mail = assets + "envelope-solid.png";
	public static String mailWhite = assets + "envelope-solid-white.png";
	
	public static String github = assets + "github.png";
	public static String githubWhite = assets + "github-white.png";
	
	public static String note = assets + "itunes-note.png";
	public static String noteWhite = assets + "itunes-note-white.png";
	
	public static String icon32 = assets + "icon32.ico";
	public static String icon16 = assets + "icon16.ico";
	public static String icon32png = assets + "icon32.png";
	public static String icon16png = assets + "icon16.png";
}
