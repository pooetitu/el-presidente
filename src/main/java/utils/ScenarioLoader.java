package utils;

import game.Island;
import game.Season;

import java.io.File;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

public class ScenarioLoader {
    private static ScenarioLoader scenarioLoader;
    private final String scenarioPath;
    private final LinkedList<File> scenarioFileList;
    private final GameFileParser gameFileParser;

    private ScenarioLoader() {
        gameFileParser = GameFileParser.getGameFileParser();
        this.scenarioFileList = new LinkedList<>();
        this.scenarioPath = "data/scenario";
    }

    public static ScenarioLoader getScenarioLoader() {
        if (scenarioLoader == null) {
            scenarioLoader = new ScenarioLoader();
        }
        return scenarioLoader;
    }

    public String showScenarioList() throws URISyntaxException {
        loadScenarioList();
        StringBuilder display = new StringBuilder();
        for (int i = 0; i < scenarioFileList.size(); i++) {
            display.append(i).append(". ").append(scenarioFileList.get(i).getName().replaceFirst("[.][^.]+$", "")).append("\n");
        }
        return display.toString();
    }

    public int getScenarioListCount() {
        return scenarioFileList.size();
    }

    private void loadScenarioList() throws URISyntaxException {
        List<File> files = RessourceReader.getFilesList(scenarioPath);
        if (files != null && !files.isEmpty()) {
            scenarioFileList.clear();
            scenarioFileList.addAll(files);
        }
    }

    public Season[] loadSeasons() {
        Season[] seasons = new Season[4];
        for (int i = 0; i < 4; i++) {
            String saveJson = RessourceReader.getContentStringFromRessource("data/seasons/season_" + i + ".json");
            seasons[i] = (Season) gameFileParser.parseData(saveJson);
        }
        return seasons;
    }

    public Island loadIslandSandboxConfig() {
        Island island = (Island) gameFileParser.parseData(RessourceReader.getContentStringFromRessource("data/sandbox.json"));
        island.init();
        return island;
    }

}
