package display;

import game.Island;
import main.Main;
import state.GameState;
import state.State;
import utils.ScenarioLoader;

import java.io.IOException;
import java.net.URISyntaxException;

public class GameCreationMenuDisplay extends MenuDisplay {
    private final DifficultyMenuDisplay difficultyMenuDisplay;
    private final ScenarioMenuDisplay scenarioMenuDisplay;

    public GameCreationMenuDisplay(String choicesDisplay) throws URISyntaxException {
        super(choicesDisplay, 2);
        difficultyMenuDisplay = new DifficultyMenuDisplay("0. Facile\n1. Normal\n2. Difficile");
        scenarioMenuDisplay = new ScenarioMenuDisplay(ScenarioLoader.getScenarioLoader().showScenarioList());
        scenarioMenuDisplay.setSwitchSize(ScenarioLoader.getScenarioLoader().getScenarioListCount());
    }

    @Override
    protected boolean execute(int choice) throws IOException, URISyntaxException {
        Island island = null;
        switch (choice) {
            case 0: {
                island = ScenarioLoader.getScenarioLoader().loadIslandSandboxConfig();
                break;
            }
            case 1: {
                scenarioMenuDisplay.displayMenu(Main.SCANNER);
                island = scenarioMenuDisplay.getIsland();
                break;
            }
        }
        difficultyMenuDisplay.displayMenu(Main.SCANNER);
        if (island == null) {
            return false;
        }
        island.setDifficulty(difficultyMenuDisplay.getGameDifficulty());
        island.init();
        State.setActiveStateId(State.GAME_STATE_ID);
        ((GameState) State.getActiveState()).initGame(island);
        return true;
    }
}
