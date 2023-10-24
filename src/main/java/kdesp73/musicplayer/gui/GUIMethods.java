package kdesp73.musicplayer.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.swing.JLabel;
import org.imgscalr.Scalr;

import kdesp73.themeLib.*;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;

public class GUIMethods {

    public static void loadImage(JLabel imageLabel, String dir) {
        imageLabel.setIcon(new ImageIcon(dir));
    }
    public static void loadImage(JLabel imageLabel, BufferedImage image) {
        imageLabel.setIcon(new ImageIcon(image));
    }

    private static boolean openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean openWebpage(URL url) {
        try {
            return openWebpage(url.toURI());
        } catch (URISyntaxException e) {
        }
        return false;
    }

    public static void changeFont(Component component, int fontSize, String font) {
        component.setFont(new Font(font, Font.PLAIN, fontSize));
    }

    public static void changeGlobalFont(Container container, int size, String font) {
        for(Component child : container.getComponents()){
            if(child instanceof JLabel){
                GUIMethods.changeFont(child, size, font);
            }
        }
    }

    public static String rgbToHex(int R, int G, int B) {
        return String.format("#%02x%02x%02x", R, G, B);
    }

    public static String rgbToHex(Color c) {
        return String.format("%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
    }

    public static Color hexToColor(String hex) {
        hex = "#" + hex;
        return Color.decode(hex);
    }

    public static BufferedImage resizeImageWidth(BufferedImage originalImage, int targetWidth) {
        return Scalr.resize(originalImage, targetWidth);
    }

    public static BufferedImage resizeImageHeight(BufferedImage originalImage, int targetWidth) {
        return Scalr.resize(originalImage, targetWidth);
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        return Scalr.resize(originalImage, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, targetWidth, targetHeight,
                Scalr.OP_ANTIALIAS);
    }

    public static ThemeCollection getThemes() {
        ThemeCollection themeCollection = new ThemeCollection();
        themeCollection.loadThemes(new File(System.getProperty("user.dir").replaceAll(Pattern.quote("\\"), "/") + "/themes/"));

        return themeCollection;
    }
}
