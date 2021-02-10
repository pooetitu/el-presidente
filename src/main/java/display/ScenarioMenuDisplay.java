package display;

import game.Island;
import utils.ScenarioLoader;

import java.io.IOException;

public class ScenarioMenuDisplay extends MenuDisplay {
    private Island island;

    public ScenarioMenuDisplay(String choicesDisplay) {
        super(choicesDisplay, 0);
    }

    @Override
    protected boolean execute(int choice) throws IOException {
        island = ScenarioLoader.getScenarioLoader().loadScenario(choice);
        return true;
    }
}
