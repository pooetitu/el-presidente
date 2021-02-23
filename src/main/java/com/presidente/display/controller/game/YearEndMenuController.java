package com.presidente.display.controller.game;

import com.presidente.display.App;
import javafx.fxml.FXMLLoader;
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

    public void continuePlaying() throws IOException {
        Pane parentPane = ((Pane) vBox.getParent());
        parentPane.getChildren().remove(vBox);
        if (parentPane.getChildren().isEmpty()) {
            parentPane.setVisible(false);
        }
        gameController.yearEnded();
    }

    public void corruptFaction() throws IOException {
        FXMLLoader loader = App.loadFXML("game/corruption_menu");
        VBox newLoadedPane = loader.load();
        ((CorruptionMenuController) loader.getController()).setController(gameController);
        Pane parentPane = ((Pane) vBox.getParent());
        parentPane.getChildren().add(newLoadedPane);
        parentPane.setVisible(true);
    }

    public void buyFood() throws IOException {
        FXMLLoader loader = App.loadFXML("game/food_buy_menu");
        VBox newLoadedPane = loader.load();
        ((FoodBuyMenuController) loader.getController()).setController(gameController);
        Pane parentPane = ((Pane) vBox.getParent());
        parentPane.getChildren().add(newLoadedPane);
        parentPane.setVisible(true);
    }
}
