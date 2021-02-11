package display.menu.game;

import display.MenuDisplay;
import game.Island;
import state.GameState;
import state.State;
import utils.GameSaver;

import java.io.IOException;

public class LoadMenu extends MenuDisplay {
    public LoadMenu(String choicesDisplay, int switchSize) {
        super(choicesDisplay, switchSize);
    }

    @Override
    protected boolean execute(int choice) throws IOException {
        Island island = GameSaver.getGameSaver().loadGame(choice);
        if (island != null) {
            ((GameState) State.getStateById(State.GAME_STATE_ID)).initGame(island);
            State.setActiveStateId(State.GAME_STATE_ID);
            return true;
        }
        return false;
    }
}
