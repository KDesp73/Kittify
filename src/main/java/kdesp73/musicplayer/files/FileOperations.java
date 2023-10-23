/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kdesp73.musicplayer.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author konstantinos
 */
public class FileOperations {
    public static HashSet<String> acceptedExtensions = new HashSet<>();

    
    public static String getDirectoryFromPath(String path){
        if (path.lastIndexOf('/') < 0) {
            return null;
        }
        return path.substring(0, path.lastIndexOf('/')) + "/";
    }

    public static String getFilenameFromPath(String path) {
        if (path.lastIndexOf('/') < 0) {
            return null;
        }
        return path.substring(path.lastIndexOf('/') + 1, path.length());
    }

    public static String getJustFilenameFromPath(String path) {
        if (path.lastIndexOf('/') < 0 || path.isEmpty() || path.isBlank() || path.lastIndexOf('.') < path.lastIndexOf('/')) {
            return null;
        }
        return path.substring(path.lastIndexOf('/') + 1, path.lastIndexOf('.'));
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
            List<String>pathlist = paths
                    .map(path -> Files.isDirectory(path) ? path.toString() + '/' : path.toString())
                    .collect(Collectors.toList());

            for (String path : pathlist) {
                if (path.contains(".")) {
                    if(acceptedExtensions.contains(FileOperations.getExtensionFromPath(path))) {
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
        for(String path : paths){
            filenames.add(FileOperations.getFilenameFromPath(path));
        }

        return filenames;
    }

    

}
