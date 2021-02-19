package com.presidente.display.controller;

import com.presidente.game.Faction;
import com.presidente.game.Island;
import com.presidente.game.Season;
import com.presidente.game.event.Event;
import com.presidente.game.event.EventChoice;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class GameController {
    public ListView<Faction> factionListView;
    public Label moneyLabel;
    public Label foodLabel;
    public Label agricultureLabel;
    public Label industryLabel;
    public Label dateLabel;
    public Label eventLabel;
    public VBox eventChoiceVBox;
    public VBox eventVBox;
    public Button nextTurnButton;
    private Island island;

    @FXML
    public void initialize() {
        factionListView.setMouseTransparent(true);
    }

    @FXML
    public void nextTurn() {
        nextTurnButton.setDisable(true);
        eventVBox.setVisible(true);
        Event event = island.getNextEvent();
        eventLabel.setText(event.getDescription());
        for (EventChoice eventChoice : event.getChoices()) {
            Label label = new Label(eventChoice.display(island.getDifficulty().getEffectRatio()));
            label.setOnMouseReleased(e -> {
                eventChoice.applyEffects(island);
                eventVBox.setVisible(false);
                eventChoiceVBox.getChildren().clear();
                nextTurnButton.setDisable(false);
            });
            label.setWrapText(true);
            label.setAlignment(Pos.CENTER);
            label.setTextAlignment(TextAlignment.CENTER);
            eventChoiceVBox.getChildren().add(label);
        }
        island.newTurn();
        setLabels();
    }

    public void setIsland(Island island) {
        this.island = island;
        System.out.println(island);
    }

    private void setLabels() {
        setDateLabel();
        setMoneyLabel();
        setFoodLabel();
        setAgricultureLabel();
        setIndustryLabel();
        setFactionListView();
    }

    private void setFactionListView() {
        factionListView.getItems().clear();
        factionListView.getItems().addAll(island.getPopulation().getFactions());

    }

    private void setDateLabel() {
        this.dateLabel.setText(Season.getSeason(island.getTurn()) + " année " + island.getTurn() / 4);
    }

    private void setMoneyLabel() {
        this.moneyLabel.setText("Argent: " + island.getResource().getTreasury());
    }

    private void setFoodLabel() {
        this.foodLabel.setText("Nourriture: " + island.getResource().getFood());
    }

    private void setAgricultureLabel() {
        this.agricultureLabel.setText("Agriculture: " + island.getAgriculture() + "%");
    }

    private void setIndustryLabel() {
        this.industryLabel.setText("Agriculture: " + island.getIndustrie() + "%");
    }
}
