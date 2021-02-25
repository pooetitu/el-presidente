package com.presidente.utils;

import com.presidente.game.Island;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedList;

public class GameSaver {
    /**
     * The path in which the game will be saved
     */
    private static final String SAVE_PATH = "./save/";
    /**
     * The unique instance of this singleton class
     */
    private static GameSaver gameSaver;
    /**
     * The list of save files
     */
    private final LinkedList<File> saveFileList;
    /**
     * A reference to the JSON parser
     */
    private final JsonParser jsonParser;

    private GameSaver() {
        jsonParser = JsonParser.getGameFileParser();
        saveFileList = new LinkedList<>();
        loadSaveList();
    }

    /**
     * Creates an instance if it is not instantiated and returns the instance
     *
     * @return The unique instance of this object
     */
    public static GameSaver getInstance() {
        if (gameSaver == null) {
            gameSaver = new GameSaver();
        }
        return gameSaver;
    }

    /**
     * Loads to saveFileList every json files listed in the save repertory in the game dir
     */
    private void loadSaveList() {
        saveFileList.clear();
        File[] files = new File(SAVE_PATH).listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));
        if (files != null && files.length > 0) {
            saveFileList.clear();
            saveFileList.addAll(Arrays.asList(files));
        }
    }

    /**
     * Creates a save file if it doesn't exist and saves the file content in the file
     *
     * @param island   The instance of Island to be saved
     * @param fileName The name of the file to be created
     */
    public void createSaveFile(Island island, String fileName) {
        try {
            File newFile = new File(SAVE_PATH + fileName + ".json");
            if (newFile.createNewFile()) {
                saveFileList.add(newFile);
            }
            saveGame(island, saveFileList.indexOf(newFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save the given Island instance in the selected save file
     *
     * @param island The instance of the Island to be saved
     * @param index  The index of the save file to be overwritten
     */
    public void saveGame(Island island, int index) {
        try {
            if (index < 0 || index >= saveFileList.size()) {
                return;
            }
            FileWriter fileWriter = new FileWriter(saveFileList.get(index));
            fileWriter.write(jsonParser.dataToJson(island));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the content of a chosen file and parses it's content
     *
     * @param index The index of the file to be read in the saveFileList
     * @return An instance of the island saved in the file chosen
     */
    public Island loadGame(int index) {
        Island island = null;
        try {
            if (index < 0 || index >= saveFileList.size()) {
                return null;
            }
            String saveJson = Files.readString(saveFileList.get(index).toPath(), StandardCharsets.UTF_8);
            island = jsonParser.parseData(saveJson, Island.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return island;
    }

    /**
     * Formats every name of saveFileList's files and adds it to a returned list
     *
     * @return A LinkedList of save file names
     */
    public LinkedList<String> getSaveFileListName() {
        loadSaveList();
        LinkedList<String> fileNames = new LinkedList<>();
        for (File file : saveFileList) {
            fileNames.add(file.getName().replaceFirst("[.][^.]+$", ""));
        }
        return fileNames;
    }
}
