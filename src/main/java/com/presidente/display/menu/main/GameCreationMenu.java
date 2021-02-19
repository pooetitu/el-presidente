package com.presidente.display.menu.main;

import com.presidente.display.MenuDisplay;
import com.presidente.game.Island;
import com.presidente.state.GameState;
import com.presidente.state.State;
import com.presidente.utils.ScenarioLoader;

public class GameCreationMenu extends MenuDisplay {
    private final DifficultyMenu difficultyMenuDisplay;
    private final ScenarioMenu scenarioMenuDisplay;

    public GameCreationMenu(String choicesDisplay) {
        super(choicesDisplay, 2);
        difficultyMenuDisplay = new DifficultyMenu("0. Facile\n1. Normal\n2. Difficile");
        scenarioMenuDisplay = new ScenarioMenu(ScenarioLoader.getScenarioLoader().showScenarioList());
        scenarioMenuDisplay.setSwitchSize(ScenarioLoader.getScenarioLoader().getScenarioListCount());
    }

    @Override
    protected boolean execute(int choice) {
        Island island = null;
        switch (choice) {
            case 0: {
                island = ScenarioLoader.getScenarioLoader().loadIslandSandboxConfig();
                break;
            }
            case 1: {
                scenarioMenuDisplay.displayMenu();
                island = scenarioMenuDisplay.getIsland();
                break;
            }
        }
        difficultyMenuDisplay.displayMenu();
        if (island == null) {
            return false;
        }
        island.setDifficulty(difficultyMenuDisplay.getGameDifficulty());
        island.init();
        State.setActiveStateId(State.GAME_STATE_ID);
        ((GameState) State.getActiveState()).init(island);
        return true;
    }
}
