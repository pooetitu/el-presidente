package com.presidente.display.controller;

import com.presidente.display.App;
import javafx.fxml.FXML;

import java.io.IOException;

public class MainMenuController {
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
