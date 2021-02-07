package main;

import game.GameDifficulty;
import game.Island;
import game.Ressource;
import state.GameState;
import state.MenuState;
import state.State;
import utils.GameLoader;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public final static Scanner SCANNER = new Scanner(System.in);

    private static void initStates() {
        State.addState(new MenuState(State.MENU_STATE_ID));
        State.addState(new GameState(State.GAME_STATE_ID));
    }

    public static void main(String[] args) throws Exception {
        System.out.println(Arrays.toString(GameLoader.getGameLoaderInstance().loadSeasons()));
        Island island = new Island(15, 15, GameDifficulty.NORMAL, new Ressource(1000, 10));
        initStates();
        while (true) {
            State.getActiveState().run();
        }
    }
}
