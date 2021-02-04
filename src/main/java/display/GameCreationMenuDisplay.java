package display;

import game.GameDifficulty;
import game.Island;
import game.Ressource;
import main.Main;
import state.GameState;
import state.State;

public class GameCreationMenuDisplay extends MenuDisplay {

    public GameCreationMenuDisplay(String choicesDisplay) {
        super(choicesDisplay, 2);
    }

    @Override
    protected boolean execute(int choice) {
        Island island = null;
        switch (choice) {
            case 0: {
                island = new Island(15,15,new Ressource(15,15));
                break;
            }
            case 1: {
                break;
            }
        }
        State.setActiveStateId(State.GAME_STATE_ID);
        ((GameState) State.getActiveState()).initGame(island);
        State.getActiveState().run();
        return true;
    }
}
