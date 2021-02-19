package com.presidente.display.menu.main;

import com.presidente.display.MenuDisplay;
import com.presidente.display.menu.game.LoadMenu;
import com.presidente.utils.GameSaver;

public class MainMenu extends MenuDisplay {
    private final GameSaver gameSaver;

    public MainMenu(String choicesDisplay) {
        super(choicesDisplay, 3);
        gameSaver = GameSaver.getGameSaver();
    }

    @Override
    protected boolean execute(int choice) {
        switch (choice) {
            case 0: {
                GameCreationMenu gcd = new GameCreationMenu("0. Mode SandBox\n1. Mode Scénario");
                return gcd.displayMenu();
            }
            case 1: {
                LoadMenu loadMenu = new LoadMenu(gameSaver.showSaveList(), gameSaver.getSaveListCount());
                loadMenu.displayMenu();
                break;
            }
            case 2: {
                System.exit(0);
            }
        }
        return true;
    }
}
