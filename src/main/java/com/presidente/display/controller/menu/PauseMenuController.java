package com.presidente.display.controller.menu;

import com.presidente.display.App;
import com.presidente.display.controller.game.GameController;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class PauseMenuController {
    public VBox vBox;
    private GameController gameController;

    public void leaveMenu() {
        Pane parentPane = ((Pane) vBox.getParent());
        parentPane.getChildren().remove(vBox);
        if (parentPane.getChildren().isEmpty()) {
            parentPane.setVisible(false);
        }
    }

    public void saveGame() {
        ((SaveGameController) gameController.setPane("menu/save_game", gameController.menuPane).getController()).setController(gameController);
    }

    public void loadGame() {
        gameController.setPane("menu/load_game", gameController.menuPane);
    }

    public void leaveToMainMenu() throws IOException {
        App.setRoot("main_menu");
    }

    public void setController(GameController gameController) {
        this.gameController = gameController;
    }
}
