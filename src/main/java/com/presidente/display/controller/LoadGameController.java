package com.presidente.display.controller;

import com.presidente.display.App;
import com.presidente.game.Island;
import com.presidente.utils.GameSaver;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class LoadGameController {
    public VBox vBox;
    public ListView<String> saveList;
    public Button loadButton;

    public void loadSave() throws IOException {
        Island island = GameSaver.getGameSaver().loadGame(saveList.getSelectionModel().getSelectedIndex());
        ((GameController) App.setRoot("game").getController()).setIsland(island);
    }

    public void backToPrevious() {
        Pane parentPane = ((Pane) vBox.getParent());
        parentPane.getChildren().remove(vBox);
        if (parentPane.getChildren().isEmpty()) {
            parentPane.setVisible(false);
        }
    }

    public void initialize() {
        saveList.getItems().addAll(GameSaver.getGameSaver().getSaveFileListName());
        saveList.setOnMouseClicked(click -> {
            loadButton.setDisable(false);
            if (click.getClickCount() == 2) {
                try {
                    loadSave();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
