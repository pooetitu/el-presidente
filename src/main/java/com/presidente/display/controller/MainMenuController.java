package com.presidente.display.controller;

import com.presidente.display.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainMenuController {
    public Pane menuPane;

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
        menuPane.getChildren().clear();
        FXMLLoader loader = App.loadFXML("menu/load_game");
        VBox newLoadedPane = loader.load();
        menuPane.getChildren().add(newLoadedPane);
        menuPane.setVisible(true);
    }


}
