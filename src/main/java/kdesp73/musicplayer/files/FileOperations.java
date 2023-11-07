/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer.files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author konstantinos
 */
public class FileOperations {

	private static String os = System.getProperty("os.name").toLowerCase();
	private static char slash = (os.contains("windows")) ? '\\' : '/';
	public static HashSet<String> acceptedExtensions = new HashSet<>();

	public static String getDirectoryFromPath(String path) {
		if (path.lastIndexOf(slash) < 0) {
			return null;
		}
		return path.substring(0, path.lastIndexOf('/')) + slash;
	}

	public static String getFilenameFromPath(String path) {
		if (path.lastIndexOf(slash) < 0) {
			return null;
		}
		return path.substring(path.lastIndexOf(slash) + 1, path.length());
	}

	public static String getJustFilenameFromPath(String path) {
		if (path.lastIndexOf(slash) < 0 || path.isEmpty() || path.isBlank() || path.lastIndexOf('.') < path.lastIndexOf('/')) {
			return null;
		}
		return path.substring(path.lastIndexOf(slash) + 1, path.lastIndexOf('.'));
	}

	public static String getExtensionFromPath(String path) {
		if (path.lastIndexOf('.') < 0) {
			return null;
		}
		return path.substring(path.lastIndexOf('.') + 1, path.length());
	}

	public static int getSize(String path) {
		return -1;
	}

	public static int getDuration(String path) {
		return -1;
	}

	public static List<String> findFiles(String directory) throws IOException {
		List<String> p = new ArrayList<>();
		try (Stream<Path> paths = Files.walk(Path.of(directory), 10)) {
			List<String> pathlist = paths
					.map(path -> Files.isDirectory(path) ? path.toString() + '/' : path.toString())
					.collect(Collectors.toList());

			for (String path : pathlist) {
				if (path.contains(".")) {
					if (acceptedExtensions.contains(FileOperations.getExtensionFromPath(path))) {
						p.add(path);
					}
				}
			}

		} catch (IOException e) {
		}
		return p;
	}

	public static List<String> findFiles(String directory, HashSet<String> extensions) throws IOException {
		List<String> p = new ArrayList<>();
		try (Stream<Path> paths = Files.walk(Path.of(directory), 10)) {
			List<String> pathlist = paths
					.map(path -> Files.isDirectory(path) ? path.toString() + slash : path.toString())
					.collect(Collectors.toList());

			for (String path : pathlist) {
				if (path.contains(".")) {
					if (extensions.contains(FileOperations.getExtensionFromPath(path))) {
						p.add(path);
					}
				}
			}

		} catch (IOException e) {
		}
		return p;
	}

	public static List<String> getFilenames(List<String> paths) {
		List<String> filenames = new ArrayList<>();
		for (String path : paths) {
			filenames.add(FileOperations.getFilenameFromPath(path));
		}

		return filenames;
	}
	
	public static String readFile(String path){
		String content = "";
		try {
            // Create a FileReader to read the file
            FileReader fileReader = new FileReader(path);

            // Create a BufferedReader to efficiently read the file line by line
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content += line + "\n";
            }

            // Close the BufferedReader and FileReader when done
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return content;
	}

}
