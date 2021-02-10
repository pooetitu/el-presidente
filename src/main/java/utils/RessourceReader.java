package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RessourceReader {
    public static String getContentStringFromRessource(String fileName) {
        return new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(RessourceReader.class.getClassLoader().getResourceAsStream(fileName)), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
    }

    public static File getFileFromRessource(String fileName) throws URISyntaxException {
        ClassLoader classLoader = RessourceReader.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource != null) {
            return new File(resource.toURI());
        }
        return null;
    }

    public static List<File> getFilesList(String path) throws URISyntaxException {
        File folder = getFileFromRessource(path);
        if (folder != null) {
            File[] files = folder.listFiles();
            if (files != null) {
                return Arrays.asList(files);
            }
        }
        return null;
    }
}
