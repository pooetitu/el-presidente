package display;

import game.Island;
import main.Main;
import state.GameState;
import state.State;
import utils.ScenarioLoader;

import java.io.IOException;
import java.net.URISyntaxException;

public class GameCreationMenuDisplay extends MenuDisplay {
    DifficultyMenuDisplay difficultyMenuDisplay;
    ScenarioMenuDisplay scenarioMenuDisplay;

    public GameCreationMenuDisplay(String choicesDisplay) {
        super(choicesDisplay, 2);
        difficultyMenuDisplay = new DifficultyMenuDisplay("0. Facile\n1. Normal\n2. Difficile");
    }

    @Override
    protected boolean execute(int choice) throws IOException, URISyntaxException {
        Island island = null;
        switch (choice) {
            case 0: {
                difficultyMenuDisplay.displayMenu(Main.SCANNER);
                island = ScenarioLoader.getScenarioLoader().loadIslandSandboxConfig();
                island.setDifficulty(difficultyMenuDisplay.getGameDifficulty());
                break;
            }
            case 1: {
                scenarioMenuDisplay = new ScenarioMenuDisplay(ScenarioLoader.getScenarioLoader().showScenarioList());
                scenarioMenuDisplay.setSwitchSize(ScenarioLoader.getScenarioLoader().getScenarioListCount());
                if (!scenarioMenuDisplay.displayMenu(Main.SCANNER)) return false;
                break;
            }
        }
        State.setActiveStateId(State.GAME_STATE_ID);
        ((GameState) State.getActiveState()).initGame(island);
        return true;
    }
}
