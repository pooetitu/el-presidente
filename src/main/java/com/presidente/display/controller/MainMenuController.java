package com.presidente.display.controller;

import com.presidente.display.App;
import javafx.fxml.FXML;

import java.io.IOException;

public class MainMenuController {

    @FXML
    private void buttonLeaveAction() {
        System.exit(0);
    }

    @FXML
    private void switchToGameCreationMenu() throws IOException {
        App.setRoot("game_creator");
    }

    @FXML
    private void switchToLoadSaveMenu() throws IOException {
        App.setRoot("save_load");
    }

}
