package com.presidente.display.controller;

import com.presidente.display.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class PauseMenuController {
    public VBox vBox;

    public void leaveMenu() {
        Pane parentPane = ((Pane) vBox.getParent());
        parentPane.getChildren().remove(vBox);
        if (parentPane.getChildren().isEmpty()) {
            parentPane.setVisible(false);
        }
    }

    public void saveGame() throws IOException {
        FXMLLoader loader = App.loadFXML("menu/save_game");
        Pane parentPane = ((Pane) vBox.getParent());
        VBox newLoadedPane = loader.load();
        parentPane.getChildren().add(newLoadedPane);
        parentPane.setVisible(true);
    }

    public void loadGame() throws IOException {
        FXMLLoader loader = App.loadFXML("menu/load_game");
        Pane parentPane = ((Pane) vBox.getParent());
        VBox newLoadedPane = loader.load();
        parentPane.getChildren().add(newLoadedPane);
        parentPane.setVisible(true);
    }

    public void leaveToMainMenu() throws IOException {
        App.setRoot("main_menu");
    }
}
