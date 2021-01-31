package main;

import state.GameState;
import state.MenuState;
import state.State;

public class Main {
    private static void initStates() {
        State.addState(new MenuState(State.MENU_STATE_ID));
        State.addState(new GameState(State.GAME_STATE_ID));
    }

    public static void main(String[] args) {
        initStates();
        while (true) {
            State.getActiveState().run();
        }
    }
}
