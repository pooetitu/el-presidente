package com.presidente.display.menu.main;

import com.presidente.display.MenuDisplay;
import com.presidente.game.GameDifficulty;

public class DifficultyMenu extends MenuDisplay {

    private GameDifficulty gameDifficulty;

    public DifficultyMenu(String choicesDisplay) {
        super(choicesDisplay, 3);
    }

    public GameDifficulty getGameDifficulty() {
        return gameDifficulty;
    }

    @Override
    protected boolean execute(int choice) {
        switch (choice) {
            case 0: {
                gameDifficulty = GameDifficulty.EASY;
                break;
            }
            case 1: {
                gameDifficulty = GameDifficulty.NORMAL;
                break;
            }
            case 2: {
                gameDifficulty = GameDifficulty.HARD;
                break;
            }
        }
        return true;
    }
}
