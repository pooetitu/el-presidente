package com.presidente.utils;

import com.presidente.game.Island;
import com.presidente.game.Season;

import java.util.Arrays;
import java.util.LinkedList;

public class ScenarioLoader {
    /**
     * The scenario files repertory
     */
    private final static String SCENARIO_PATH = "com/presidente/data/scenario/";
    /**
     * The unique instance of this singleton class
     */
    private static ScenarioLoader scenarioLoader;
    /**
     * A list of scenario file names
     */
    private final LinkedList<String> scenarioList;
    /**
     * A reference to the JSON parser
     */
    private final JsonParser jsonParser;

    private ScenarioLoader() {
        jsonParser = JsonParser.getGameFileParser();
        this.scenarioList = new LinkedList<>();
        loadScenarioList();
    }

    /**
     * Creates an instance if it is not instantiated and returns the instance
     *
     * @return The unique instance of this object
     */
    public static ScenarioLoader getInstance() {
        if (scenarioLoader == null) {
            scenarioLoader = new ScenarioLoader();
        }
        return scenarioLoader;
    }

    /**
     * Loads to scenariosPathList every json file path listed in the scenario resource repertory
     */
    private void loadScenarioList() {
        String[] files = ResourceReader.getFilesList(SCENARIO_PATH);
        if (files != null && files.length > 0) {
            scenarioList.clear();
            scenarioList.addAll(Arrays.asList(files));
        }
    }

    /**
     * Loads the selected scenario
     *
     * @param index The index of the chosen scenario in the scenariosPathList
     * @return The instance of the loaded scenario
     */
    public Island loadScenario(int index) {
        if (index < 0 || index >= scenarioList.size()) {
            return null;
        }
        return jsonParser.parseData(ResourceReader.getContentStringFromResource("/" + SCENARIO_PATH + scenarioList.get(index)), Island.class);
    }

    /**
     * Loads each season file loads the instances in an array and returns it
     *
     * @return The array of the parsed seasons
     */
    public Season[] loadSeasons() {
        Season[] seasons = new Season[4];
        for (int i = 0; i < 4; i++) {
            String saveJson = ResourceReader.getContentStringFromResource("/com/presidente/data/seasons/season_" + i + ".json");
            seasons[i] = jsonParser.parseData(saveJson, Season.class);
        }
        return seasons;
    }

    /**
     * @return An instance of Island with sandbox settings
     */
    public Island getIslandSandboxInstance() {
        return jsonParser.parseData(ResourceReader.getContentStringFromResource("/com/presidente/data/sandbox.json"), Island.class);
    }

    /**
     * Formats every name of scenarioList's files and adds it to a list
     *
     * @return A LinkedList of scenario file names
     */
    public LinkedList<String> getScenarioList() {
        LinkedList<String> fileNames = new LinkedList<>();
        for (String fileName : scenarioList) {
            fileNames.add(fileName.replaceFirst("[.][^.]+$", ""));
        }
        return fileNames;
    }
}
