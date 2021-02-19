package com.presidente.display.menu.main;

import com.presidente.display.MenuDisplay;
import com.presidente.game.Island;
import com.presidente.utils.ScenarioLoader;

public class ScenarioMenu extends MenuDisplay {
    private Island island;

    public ScenarioMenu(String choicesDisplay) {
        super(choicesDisplay, 0);
    }

    @Override
    protected boolean execute(int choice) {
        island = ScenarioLoader.getScenarioLoader().loadScenario(choice);
        return true;
    }

    public Island getIsland() {
        return island;
    }
}
