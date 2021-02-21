package com.presidente.display.controller;

import com.presidente.display.App;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainMenuController {

    public VBox eventChoiceVBox;
    public Label eventLabel;

    @FXML
    private void buttonLeaveAction() {
        System.exit(0);
    }

    @FXML
    private void switchToGameCreationMenu() throws IOException {
        App.setRoot("game_creation");
    }

    @FXML
    private void switchToLoadSaveMenu() throws IOException {
        App.setRoot("load_game");
    }

}
