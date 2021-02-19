package com.presidente.display.controller;

import com.presidente.display.App;
import com.presidente.game.GameDifficulty;
import com.presidente.game.Island;
import com.presidente.game.Resource;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.io.IOException;

public class LoadGameController {
    @FXML
    public TableView<String> saveList;

    @FXML
    public void loadGame() throws IOException {
        ((GameController) App.setRoot("game").getController()).setIsland(new Island(15, 15, GameDifficulty.NORMAL, new Resource(10, 10)));
    }

    @FXML
    public void switchToMainMenu() throws IOException {
        App.setRoot("main_menu");
    }
}
