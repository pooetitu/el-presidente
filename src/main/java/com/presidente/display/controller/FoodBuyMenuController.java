package com.presidente.display.controller;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.io.IOException;

public class FoodBuyMenuController {
    public Label buyMessage;
    public Slider quantitySlider;
    public Label quantityLabel;
    private GameController gameController;

    public void returnToEndOfYearMenu() throws IOException {
        gameController.setEndOfYearPane();
    }

    public void setController(GameController gameController) {
        this.gameController = gameController;

        int maxPurchasableFood = gameController.getIsland().getResource().purchasableMaximumFoodAmount();
        String foodBuyMessage = String.format(buyMessage.getText(), maxPurchasableFood);
        buyMessage.setText(foodBuyMessage);
        quantitySlider.setMax(maxPurchasableFood);
        quantityLabel.textProperty().bind(Bindings.format("%.0f", quantitySlider.valueProperty()));
    }

    public void buyFood() throws IOException {
        gameController.getIsland().getResource().buyFood((int) quantitySlider.getValue());
        returnToEndOfYearMenu();
        gameController.refreshLabels();
    }
}
