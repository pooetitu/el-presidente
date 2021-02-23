package com.presidente.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class ResourceReader {
    public static String getContentStringFromResource(String fileName) {
        return new BufferedReader(
                new InputStreamReader(getStreamFromResource(fileName), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
    }

    public static InputStream getStreamFromResource(String fileName) {
        return ResourceReader.class.getResourceAsStream(fileName);
    }

    static String[] getFilesList(String path) {
        try {
            URL dirURL = ResourceReader.class.getClassLoader().getResource(path);
            if (dirURL != null && dirURL.getProtocol().equals("file")) {
                return new File(dirURL.toURI()).list();
            }
            if (dirURL != null && dirURL.getProtocol().equals("jar")) {
                String jarPath = dirURL.getPath().substring(5, dirURL.getPath().indexOf("!"));
                JarFile jar = new JarFile(URLDecoder.decode(jarPath, StandardCharsets.UTF_8));
                Enumeration<JarEntry> entries = jar.entries();
                Set<String> result = new HashSet<>();
                while (entries.hasMoreElements()) {
                    String name = entries.nextElement().getName();
                    if (name.startsWith(path)) {
                        String entry = name.substring(path.length());
                        int checkSubdirectory = entry.indexOf("/");
                        if (checkSubdirectory == 0) {
                            entry = entry.substring(0, checkSubdirectory);
                        }
                        if (!entry.isBlank()) {
                            result.add(entry);
                        }
                    }
                }
                return result.toArray(new String[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
