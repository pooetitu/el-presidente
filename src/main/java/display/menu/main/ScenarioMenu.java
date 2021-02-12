package display.menu.main;

import display.MenuDisplay;
import game.Island;
import utils.ScenarioLoader;

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
