package com.presidente.display.controller;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class SaveGameController {
    public ListView<String> saveList;
    public TextField saveNameField;
    public Button overwriteSaveButton;
    public Button createSaveButton;
    private GameController gameController;

    public void setSaveName() {
        createSaveButton.setDisable(saveNameField.getText().equals(""));
    }

    public void overwriteSave() {
        gameController.closeMenu();
    }

    public void createSave() {
        gameController.closeMenu();
    }

    public void backToPrevious() {
        gameController.closeMenu();
    }

    public void setController(GameController gameController) {
        this.gameController = gameController;
    }
}
