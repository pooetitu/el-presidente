package utils;

import game.Island;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedList;

public class GameSaver {
    private static GameSaver gameSaver;
    private final String savePath;
    private final LinkedList<File> saveFileList;
    private final GameFileParser gameFileParser;

    private GameSaver() {
        gameFileParser = GameFileParser.getGameFileParser();
        saveFileList = new LinkedList<>();
        savePath = "./save/";
        loadSaveList();
    }

    public static GameSaver getGameSaver() {
        if (gameSaver == null) {
            gameSaver = new GameSaver();
        }
        return gameSaver;
    }

    private void loadSaveList() {
        saveFileList.clear();
        File[] files = new File(savePath).listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));
        if (files != null && files.length > 0) {
            saveFileList.clear();
            saveFileList.addAll(Arrays.asList(files));
        }
    }

    public void createSaveFile(String fileName, Island island) throws IOException {
        File newFile = new File(savePath + fileName + ".json");
        if (newFile.createNewFile()) {
            saveFileList.add(newFile);
        }
        saveGame(island, saveFileList.indexOf(newFile));
    }

    public void saveGame(Island island, int index) throws IOException {
        if (index < 0 || index >= saveFileList.size()) {
            return;
        }
        FileWriter fileWriter = new FileWriter(saveFileList.get(index));
        fileWriter.write(gameFileParser.dataToJson(island));
        fileWriter.flush();
        fileWriter.close();
    }

    public String showSaveList() {
        loadSaveList();
        StringBuilder display = new StringBuilder();
        int counter = 0;
        for (File file : saveFileList) {
            display.append(counter).append(". ").append(file.getName().replaceFirst("[.][^.]+$", "")).append("\n");
            counter++;
        }
        display.append(counter).append(". ").append("Retour");
        return display.toString();
    }

    public int getSaveListCount() {
        return saveFileList.size();
    }

    public Island loadGame(int index) throws IOException {
        if (index < 0 || index >= saveFileList.size()) {
            return null;
        }
        String saveJson = Files.readString(saveFileList.get(index).toPath(), StandardCharsets.UTF_8);
        return (Island) gameFileParser.parseData(saveJson);
    }

}
