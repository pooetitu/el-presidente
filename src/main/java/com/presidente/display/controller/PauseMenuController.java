package com.presidente.display.controller;

import com.presidente.display.App;

import java.io.IOException;

public class PauseMenuController {
    private GameController gameController;

    public void setController(GameController gameController) {
        this.gameController = gameController;
    }

    public void leaveMenu() {
        gameController.closeMenu();
    }

    public void saveGame() {
    }

    public void loadGame() {
    }

    public void leaveToMainMenu() throws IOException {
        App.setRoot("main_menu");
    }
}
