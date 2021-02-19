package utils;

import game.Island;
import game.Season;

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
        this.scenarioPath = "data/scenario/";
    }

    public static ScenarioLoader getScenarioLoader() {
        if (scenarioLoader == null) {
            scenarioLoader = new ScenarioLoader();
        }
        return scenarioLoader;
    }

    public String showScenarioList() {
        loadScenarioList();
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
        Island island = (Island) gameFileParser.parseData(ResourceReader.getContentStringFromResource(scenarioPath + scenariosPathList.get(index)));
        island.init();
        return island;
    }

    public Season[] loadSeasons() {
        Season[] seasons = new Season[4];
        for (int i = 0; i < 4; i++) {
            String saveJson = ResourceReader.getContentStringFromResource("data/seasons/season_" + i + ".json");
            seasons[i] = (Season) gameFileParser.parseData(saveJson);
        }
        return seasons;
    }

    public Island loadIslandSandboxConfig() {
        Island island = (Island) gameFileParser.parseData(ResourceReader.getContentStringFromResource("data/sandbox.json"));
        island.init();
        return island;
    }

}
