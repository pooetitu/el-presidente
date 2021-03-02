package com.presidente.display.controller.game;

import com.presidente.game.Faction;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CorruptionMenuController {
    public Label quantityLabel;
    public Slider quantitySlider;
    public Label corruptionMessage;
    public Button corruptButton;
    public VBox vBox;
    private GameController gameController;
    private Faction faction;

    public void setController(GameController gameController) {
        this.gameController = gameController;
        gameController.factionTable.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                corruptButton.setDisable(false);
                quantitySlider.setDisable(false);
                faction = gameController.factionTable.getSelectionModel().getSelectedItem();
                int maximumPurchasable = gameController.getIsland().getMaximumPurchasableCorruption((gameController.getIsland().getPopulation().getFactionIndex(faction.getName())));
                corruptionMessage.setText(String.format("Corrompre la faction %s (Prix unitaire: %d, Maximum achetable: %d)", faction.getName(), faction.getCorruptionCost(), maximumPurchasable));
                quantitySlider.setMax(maximumPurchasable);
                quantitySlider.setDisable(maximumPurchasable <= 0);
                quantityLabel.textProperty().bind(Bindings.format("%.0f", quantitySlider.valueProperty()));
            }
        });
    }

    public void corruptFaction() {
        gameController.getIsland().corruptFaction(gameController.getIsland().getPopulation().getFactionIndex(faction.getName()), (int) Math.round(quantitySlider.getValue()));
        returnToEndOfYearMenu();
        gameController.refreshLabels();
    }

    public void returnToEndOfYearMenu() {
        Pane parentPane = ((Pane) vBox.getParent());
        parentPane.getChildren().remove(vBox);
        gameController.factionTable.setOnMouseClicked(null);
        if (parentPane.getChildren().isEmpty()) {
            parentPane.setVisible(false);
        }
    }
}
