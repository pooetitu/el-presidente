package com.presidente.display.controller;

import com.presidente.display.App;
import com.presidente.game.GameDifficulty;
import com.presidente.game.Island;
import com.presidente.game.Resource;
import com.presidente.utils.ScenarioLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.io.IOException;

public class GameCreationController {
    @FXML
    public ListView<String> scenarioListView;
    @FXML
    private CheckBox sandboxCheckBox;

    @FXML
    public void startGame() throws IOException {
        FXMLLoader loader = App.setRoot("game");
        ((GameController) loader.getController()).setIsland(new Island(15, 15, GameDifficulty.NORMAL, new Resource(10, 10)));
    }

    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("main_menu");
    }

    @FXML
    public void toggleSandbox() {
        scenarioListView.setDisable(sandboxCheckBox.isSelected());
    }

    @FXML
    public void initialize() {
        scenarioListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        scenarioListView.getItems().addAll(ScenarioLoader.getScenarioLoader().getScenarioList());
    }
}
