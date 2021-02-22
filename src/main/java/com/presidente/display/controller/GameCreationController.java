package com.presidente.display.controller;

import com.presidente.display.App;
import com.presidente.display.controller.game.GameController;
import com.presidente.game.GameDifficulty;
import com.presidente.game.Island;
import com.presidente.utils.ScenarioLoader;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class GameCreationController {
    @FXML
    public ListView<String> scenarioListView;
    @FXML
    public ChoiceBox<GameDifficulty> difficultyChoiceBox;
    @FXML
    public Button startGameButton;
    @FXML
    private CheckBox sandboxCheckBox;

    @FXML
    public void startGame() throws IOException {
        Island island;
        if (sandboxCheckBox.isSelected()) {
            island = ScenarioLoader.getScenarioLoader().loadIslandSandboxConfig();
        } else {
            island = ScenarioLoader.getScenarioLoader().loadScenario(scenarioListView.getSelectionModel().getSelectedIndex());
        }
        island.setDifficulty(difficultyChoiceBox.getSelectionModel().getSelectedItem());
        ((GameController) App.setRoot("game").getController()).setIsland(island);
    }

    @FXML
    private void switchToMainMenu() throws IOException {
        App.setRoot("main_menu");
    }

    @FXML
    public void toggleSandbox() {
        scenarioListView.setDisable(sandboxCheckBox.isSelected());
        updateStartGameButton();
    }

    @FXML
    public void initialize() {
        for (GameDifficulty difficulty : GameDifficulty.values()) {
            difficultyChoiceBox.getItems().add(difficulty);
        }
        scenarioListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        scenarioListView.getItems().addAll(ScenarioLoader.getScenarioLoader().getScenarioList());
    }

    public void updateStartGameButton() {
        startGameButton.setDisable(!((scenarioListView.getSelectionModel().getSelectedItem() != null ||
                sandboxCheckBox.isSelected()) &&
                difficultyChoiceBox.getSelectionModel().getSelectedItem() != null));
    }
}
