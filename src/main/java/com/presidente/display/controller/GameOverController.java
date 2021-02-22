package com.presidente.display.controller;

import com.presidente.display.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class GameOverController {
    public VBox vBox;

    public void switchToMainMenu() throws IOException {
        App.setRoot("main_menu");
    }

    public void setLoadPane() throws IOException {
        FXMLLoader loader = App.loadFXML("menu/load_game");
        Pane parentPane = ((Pane) vBox.getParent());
        VBox newLoadedPane = loader.load();
        parentPane.getChildren().add(newLoadedPane);
        parentPane.setVisible(true);
    }
}
