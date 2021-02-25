package com.presidente.display.controller.menu;

import com.presidente.display.controller.game.GameController;
import com.presidente.utils.GameSaver;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SaveGameController {
    public VBox vBox;
    public ListView<String> saveList;
    public TextField saveNameField;
    public Button overwriteSaveButton;
    public Button createSaveButton;
    private GameController gameController;

    public void initialize() {
        saveList.setOnMouseClicked(e -> overwriteSaveButton.setDisable(false));
        saveList.getItems().addAll(GameSaver.getInstance().getSaveFileListName());
        saveNameField.textProperty().addListener((observable, oldValue, newValue) -> createSaveButton.setDisable(saveNameField.getText().equals("")));
    }

    public void overwriteSave() {
        GameSaver.getInstance().saveGame(gameController.getIsland(), saveList.getSelectionModel().getSelectedIndex());
        gameController.closeMenu();
    }

    public void createSave() {
        GameSaver.getInstance().createSaveFile(gameController.getIsland(), saveNameField.getText());
        gameController.closeMenu();
    }

    public void backToPrevious() {
        Pane parentPane = ((Pane) vBox.getParent());
        parentPane.getChildren().remove(vBox);
        if (parentPane.getChildren().isEmpty()) {
            parentPane.setVisible(false);
        }
    }

    public void setController(GameController gameController) {
        this.gameController = gameController;
    }
}
