package com.presidente.display.menu.game;

import com.presidente.display.MenuDisplay;
import com.presidente.game.Island;
import com.presidente.utils.GameSaver;

public class ChooseSaveMenu extends MenuDisplay {
    private final Island island;

    public ChooseSaveMenu(String choicesDisplay, int switchSize, Island island) {
        super(choicesDisplay, switchSize + 1);
        this.island = island;
    }

    @Override
    protected boolean execute(int choice) {
        GameSaver.getGameSaver().saveGame(island, choice);
        return true;
    }
}