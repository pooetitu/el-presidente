package com.presidente.utils;

import com.presidente.game.Island;
import com.presidente.game.Season;

import java.util.Arrays;
import java.util.LinkedList;

public class ScenarioLoader {
    private static ScenarioLoader scenarioLoader;
    private final String scenarioPath;
    private final LinkedList<String> scenariosPathList;
    private final GameFileParser gameFileParser;

    private ScenarioLoader() {
        gameFileParser = GameFileParser.getGameFileParser();
        this.scenariosPathList = new LinkedList<>();
        this.scenarioPath = "com/presidente/data/scenario/";
        loadScenarioList();
    }

    public static ScenarioLoader getScenarioLoader() {
        if (scenarioLoader == null) {
            scenarioLoader = new ScenarioLoader();
        }
        return scenarioLoader;
    }

    public String showScenarioList() {
        StringBuilder display = new StringBuilder();
        int counter = 0;
        for (String path : scenariosPathList) {
            display.append(counter).append(". ").append(path.replaceFirst("[.][^.]+$", "")).append("\n");
            counter++;
        }
        display.append(counter).append(". ").append("Retour");
        return display.toString();
    }

    public int getScenarioListCount() {
        return scenariosPathList.size();
    }

    private void loadScenarioList() {
        String[] files = ResourceReader.getFilesList(scenarioPath);
        if (files != null && files.length > 0) {
            scenariosPathList.clear();
            scenariosPathList.addAll(Arrays.asList(files));
        }
    }

    public Island loadScenario(int index) {
        if (index < 0 || index >= scenariosPathList.size()) {
            return null;
        }
        return gameFileParser.parseData(ResourceReader.getContentStringFromResource("/" + scenarioPath + scenariosPathList.get(index)), Island.class);
    }

    public Season[] loadSeasons() {
        Season[] seasons = new Season[4];
        for (int i = 0; i < 4; i++) {
            String saveJson = ResourceReader.getContentStringFromResource("/com/presidente/data/seasons/season_" + i + ".json");
            seasons[i] = gameFileParser.parseData(saveJson, Season.class);
        }
        return seasons;
    }

    public Island loadIslandSandboxConfig() {
        return gameFileParser.parseData(ResourceReader.getContentStringFromResource("/com/presidente/data/sandbox.json"), Island.class);
    }

    public LinkedList<String> getScenarioList() {
        return scenariosPathList;
    }
}
