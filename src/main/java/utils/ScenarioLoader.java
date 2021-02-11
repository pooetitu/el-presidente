package utils;

import game.Island;
import game.Season;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        int counter = 0;
        for (File file: scenarioFileList) {
            display.append(counter).append(". ").append(file.getName().replaceFirst("[.][^.]+$", "")).append("\n");
            counter++;
        }
        display.append(counter).append(". ").append(" Retour");
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

    public Island loadScenario(int index) throws IOException {
        if (index < 0 || index >= scenarioFileList.size()) {
            return null;
        }
        Island island = (Island) gameFileParser.parseData(Files.readString(Paths.get(scenarioFileList.get(index).getPath()), StandardCharsets.UTF_8));
        island.init();
        return island;
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
