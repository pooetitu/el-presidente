package com.presidente.display.controller;

import javafx.scene.control.Button;

import java.io.IOException;

public class YearEndMenuController {
    public Button buyFoodButton;
    public Button corruptButton;
    public Button continuePlaying;
    private GameController gameController;

    public void setController(GameController gameController) {
        this.gameController = gameController;
    }

    public void continuePlaying() throws IOException {
        gameController.yearEnded();
    }

    public void corruptFaction() throws IOException {
        gameController.setCorruptPane();
    }

    public void buyFood() throws IOException {
        gameController.setBuyFoodPane();
    }
}
