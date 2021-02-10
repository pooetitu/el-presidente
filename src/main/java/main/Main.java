package main;

import state.GameState;
import state.MenuState;
import state.State;
import utils.ScenarioLoader;

import java.util.Scanner;

public class Main {
    public final static Scanner SCANNER = new Scanner(System.in);

    private static void initStates() {
        State.addState(new MenuState(State.MENU_STATE_ID));
        State.addState(new GameState(State.GAME_STATE_ID));
    }

    public static void main(String[] args) throws Exception {
        initStates();
        while (true) {
            State.getActiveState().run();
        }
    }
}
