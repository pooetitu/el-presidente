package com.presidente.display.controller;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class FoodBuyMenuController {
    public Label buyMessage;
    public Slider quantitySlider;
    public Label quantityLabel;
    public VBox vBox;
    private GameController gameController;

    public void returnToEndOfYearMenu() {
        Pane parentPane = ((Pane) vBox.getParent());
        parentPane.getChildren().remove(vBox);
        if (parentPane.getChildren().isEmpty()) {
            parentPane.setVisible(false);
        }
    }

    public void setController(GameController gameController) {
        this.gameController = gameController;
        int maxPurchasableFood = gameController.getIsland().getResource().purchasableMaximumFoodAmount();
        String foodBuyMessage = String.format(buyMessage.getText(), maxPurchasableFood);
        buyMessage.setText(foodBuyMessage);
        quantitySlider.setMax(maxPurchasableFood);
        quantityLabel.textProperty().bind(Bindings.format("%.0f", quantitySlider.valueProperty()));
    }

    public void buyFood() {
        gameController.getIsland().getResource().buyFood((int) quantitySlider.getValue());
        returnToEndOfYearMenu();
        gameController.refreshLabels();
    }
}
