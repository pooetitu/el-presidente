package com.presidente.display.controller;

import com.presidente.display.App;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class GameOverController {
    public VBox vBox;

    public void switchToMainMenu() throws IOException {
        App.setRoot("main_menu");
    }

    public void setLoadPane() {
    }
}
