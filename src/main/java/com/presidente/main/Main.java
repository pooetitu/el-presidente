package com.presidente.main;

import com.presidente.state.GameState;
import com.presidente.state.MenuState;
import com.presidente.state.State;

import java.util.Scanner;

public class Main {
    public final static Scanner SCANNER = new Scanner(System.in);

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
