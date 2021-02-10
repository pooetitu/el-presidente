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
        saveFileList.addAll(Arrays.asList(Objects.requireNonNull(new File(savePath).listFiles((dir, name) -> name.toLowerCase().endsWith(".sav")))));
    }

    public void createSaveFile(String fileName, Island island) throws IOException {
        File newFile = new File(savePath + fileName + ".sav");
        if (newFile.createNewFile()) {
            saveFileList.add(newFile);
        }
        saveGame(island, saveFileList.indexOf(newFile));
    }

    public void saveGame(Island island, int index) throws IOException {
        FileWriter fileWriter = new FileWriter(saveFileList.get(index));
        fileWriter.write(gameFileParser.dataToJson(island));
        fileWriter.flush();
    }

    public String showSaveList() {
        loadSaveList();
        StringBuilder display = new StringBuilder();
        for (int i = 0; i < saveFileList.size(); i++) {
            display.append(i).append(". ").append(saveFileList.get(i).getName().replaceFirst("[.][^.]+$", "")).append("\n");
        }
        return display.toString();
    }

    public Island loadGame(int index) throws IOException {
        String saveJson = Files.readString(Paths.get(saveFileList.get(index).getPath()), StandardCharsets.UTF_8);
        Island island = (Island) gameFileParser.parseData(saveJson);
        System.out.println(island.getRessources().getFood());
        return island;
    }

}
