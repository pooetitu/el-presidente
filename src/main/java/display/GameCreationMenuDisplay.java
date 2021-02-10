package display;

import game.Island;
import game.Ressource;
import main.Main;
import state.GameState;
import state.State;
import utils.ScenarioLoader;

import java.io.IOException;

public class GameCreationMenuDisplay extends MenuDisplay {
    DifficultyMenuDisplay dmd;

    public GameCreationMenuDisplay(String choicesDisplay) {
        super(choicesDisplay, 2);
        dmd = new DifficultyMenuDisplay("0. Facile\n1. Normal\n2. Difficile");
    }

    @Override
    protected boolean execute(int choice) throws IOException {
        Island island = null;
        switch (choice) {
            case 0: {
                dmd.displayMenu(Main.SCANNER);
                island = ScenarioLoader.getScenarioLoader().loadIslandSandboxConfig();
                island.setDifficulty(dmd.getGameDifficulty());
                break;
            }
            case 1: {
                break;
            }
        }
        State.setActiveStateId(State.GAME_STATE_ID);
        ((GameState) State.getActiveState()).initGame(island);
        return true;
    }
}
