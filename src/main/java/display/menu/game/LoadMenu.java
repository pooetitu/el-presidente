package display.menu.game;

import display.MenuDisplay;
import game.Island;
import state.GameState;
import state.State;
import utils.GameSaver;

public class LoadMenu extends MenuDisplay {
    public LoadMenu(String choicesDisplay, int switchSize) {
        super(choicesDisplay, switchSize + 1);
    }

    @Override
    protected boolean execute(int choice) {
        Island island = GameSaver.getGameSaver().loadGame(choice);
        if (island != null) {
            ((GameState) State.getStateById(State.GAME_STATE_ID)).init(island);
            State.setActiveStateId(State.GAME_STATE_ID);
        }
        return true;
    }
}
