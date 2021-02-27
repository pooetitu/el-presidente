package com.presidente.display.controller.game;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class YearEndMenuController {
    public Button buyFoodButton;
    public Button corruptButton;
    public Button continuePlaying;
    public VBox vBox;
    private GameController gameController;

    public void setController(GameController gameController) {
        this.gameController = gameController;
    }

    public void continuePlaying() {
        Pane parentPane = ((Pane) vBox.getParent());
        parentPane.getChildren().remove(vBox);
        if (parentPane.getChildren().isEmpty()) {
            parentPane.setVisible(false);
        }
        gameController.yearEnded();
    }

    public void corruptFaction() throws IOException {
        ((CorruptionMenuController) gameController.setPane("game/corruption_menu", gameController.gamePane).getController()).setController(gameController);
    }

    public void buyFood() throws IOException {
        ((FoodBuyMenuController) gameController.setPane("game/food_buy_menu", gameController.gamePane).getController()).setController(gameController);
    }
}
