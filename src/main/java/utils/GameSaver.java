package utils;

import game.Island;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

public class GameSaver {
    private static GameSaver gameSaver;
    private final String savePath;
    private final LinkedList<File> saveFileList;
    private final GameFileParser gameFileParser;

    private GameSaver() {
        gameFileParser = GameFileParser.getGameFileParser();
        saveFileList = new LinkedList<>();
        savePath = "./";
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
        saveFileList.addAll(Arrays.asList(Objects.requireNonNull(new File(savePath).listFiles((dir, name) -> name.toLowerCase().endsWith(".json")))));
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
        for (File file: saveFileList) {
            display.append(counter).append(". ").append(file.getName().replaceFirst("[.][^.]+$", "")).append("\n");
            counter++;
        }
        display.append(counter).append(". ").append(" Retour");
        return display.toString();
    }

    public int getSaveListCount() {
        return saveFileList.size();
    }

    public Island loadGame(int index) throws IOException {
        if (index < 0 || index >= saveFileList.size()) {
            return null;
        }
        String saveJson = Files.readString(Paths.get(saveFileList.get(index).getPath()), StandardCharsets.UTF_8);
        Island island = (Island) gameFileParser.parseData(saveJson);
        System.out.println(island.getRessources().getFood());
        return island;
    }

}
