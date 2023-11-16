package kdesp73.musicplayer.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.JLabel;
import org.imgscalr.Scalr;

import kdesp73.themeLib.*;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import kdesp73.musicplayer.files.FileOperations;
import com.github.rjeschke.txtmark.Processor;

public class GUIMethods {

	public static void renderMarkdown(JEditorPane editor, String path) {
		String html = Processor.process(FileOperations.readFile(path));
		editor.setContentType("text/html");
		editor.setText(html);
	}

	public static BufferedImage imageFromURL(String imageUrl) {
		try {
			URL url = null;
			try {
				url = new URL(imageUrl);
			} catch (java.net.MalformedURLException e) {
				System.err.println("Issue with url");
				return null;
			} catch (java.lang.IllegalArgumentException e) {
				System.err.println("Image is JPEG");
				return null;
			}

			BufferedImage image = null;
			try {
				image = ImageIO.read(url);

			} catch (javax.imageio.IIOException e) {
				System.err.println("No internet Connection. Can't Download image from URL");
				return null;
			} catch (java.lang.IllegalArgumentException e) {
				System.err.println("Image is JPEG");
				return null;
			}

			if (image != null) {
				return image;
			} else {
				System.out.println("Failed to load the image from the URL.");
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (java.lang.IllegalArgumentException e) {
			return null;
		}
	}

	public static void downloadImage(URL url, String path) throws IOException {
		System.out.println("Downloading in: \"" + path + "\"");

		try {
			InputStream inputStream = url.openStream();
			Files.copy(inputStream, Paths.get(path), StandardCopyOption.REPLACE_EXISTING);
			System.out.println("Image downloaded and saved to " + path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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

	public static void setFontRecursively(Component component, Font newFont) {
		if (component instanceof Container) {
			Container container = (Container) component;
			Component[] children = container.getComponents();
			for (Component child : children) {
				setFontRecursively(child, newFont);
			}
		}
		component.setFont(newFont);
	}

	public static void setFontFamilyRecursively(Component component, String fontFamily, int fontStyle) {
		Font currentFont = component.getFont();
		Font newFont = new Font(fontFamily, fontStyle, currentFont.getSize());

		if (component instanceof Container) {
			Container container = (Container) component;
			Component[] children = container.getComponents();
			for (Component child : children) {
				setFontFamilyRecursively(child, fontFamily, fontStyle);
			}
		}
		component.setFont(newFont);
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
