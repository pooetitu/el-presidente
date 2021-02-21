package com.presidente.display.controller;

import com.presidente.display.App;
import com.presidente.game.Faction;
import com.presidente.game.Island;
import com.presidente.game.Season;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/* TODO add game over support
 *   rework display aesthetics*/
public class GameController {
    public Label moneyLabel;
    public Label foodLabel;
    public Label agricultureLabel;
    public Label industryLabel;
    public Label dateLabel;
    public Button nextTurnButton;
    public TableColumn<Faction, Integer> factionSupporter;
    public TableColumn<Faction, Integer> factionSatisfaction;
    public TableColumn<Faction, String> factionName;
    public TableView<Faction> factionTable;
    public Button openMenuButton;
    public Pane menuPane;
    public Pane gamePane;
    private Island island;

    @FXML
    public void initialize() {
        factionName.setCellValueFactory(f -> new ReadOnlyObjectWrapper<>(f.getValue().getName()));
        factionSatisfaction.setCellValueFactory(f -> new ReadOnlyObjectWrapper<>(f.getValue().getSatisfaction()));
        factionSupporter.setCellValueFactory(f -> new ReadOnlyObjectWrapper<>(f.getValue().getSupporter()));
    }

    @FXML
    public void nextTurn() throws IOException {
        nextTurnButton.setDisable(true);
        refreshLabels();
        if (island.isEndOfYear()) {
            setEndOfYearPane();
            return;
        }
        setEventPane();
        island.newTurn();
    }

    public void setBuyFoodPane() throws IOException {
        gamePane.getChildren().clear();
        FXMLLoader loader = App.loadFXML("game/food_buy_menu");
        VBox newLoadedPane = loader.load();
        ((FoodBuyMenuController) loader.getController()).setController(this);
        gamePane.getChildren().add(newLoadedPane);
        gamePane.setVisible(true);
    }

    public void setCorruptPane() throws IOException {
        gamePane.getChildren().clear();
        FXMLLoader loader = App.loadFXML("game/corruption_menu");
        VBox newLoadedPane = loader.load();
        ((CorruptionMenuController) loader.getController()).setController(this);
        gamePane.getChildren().add(newLoadedPane);
        gamePane.setVisible(true);
    }

    public void setEndOfYearPane() throws IOException {
        gamePane.getChildren().clear();
        FXMLLoader loader = App.loadFXML("game/year_end_menu");
        VBox newLoadedPane = loader.load();
        ((YearEndMenuController) loader.getController()).setController(this);
        gamePane.getChildren().add(newLoadedPane);
        gamePane.setVisible(true);
    }

    private void setEventPane() throws IOException {
        gamePane.getChildren().clear();
        FXMLLoader loader = App.loadFXML("game/event_menu");
        VBox newLoadedPane = loader.load();
        ((EventMenuController) loader.getController()).setController(this);
        gamePane.getChildren().add(newLoadedPane);
        gamePane.setVisible(true);
    }

    public void setIsland(Island island) throws IOException {
        this.island = island;
        ObservableList<Faction> factionObservableList = FXCollections.observableArrayList();
        factionObservableList.addAll(island.getPopulation().getFactions());
        factionTable.setItems(factionObservableList);
        nextTurn();
    }

    public void refreshLabels() {
        setDateLabel();
        setMoneyLabel();
        setFoodLabel();
        setAgricultureLabel();
        setIndustryLabel();
        factionTable.refresh();
    }

    public void readyForNextTurn() throws IOException {
        nextTurnButton.setDisable(false);
        gamePane.getChildren().clear();
        refreshLabels();
    }

    private void setDateLabel() {
        this.dateLabel.setText(Season.getSeason(island.getTurn() % 4) + " année " + island.getTurn() / 4);
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

    public Island getIsland() {
        return island;
    }

    public void openMenu() throws IOException {
        menuPane.getChildren().clear();
        FXMLLoader loader = App.loadFXML("game/pause_menu");
        VBox newLoadedPane = loader.load();
        ((PauseMenuController) loader.getController()).setController(this);
        menuPane.getChildren().add(newLoadedPane);
        menuPane.setVisible(true);
    }

    public void closeMenu() {
        menuPane.setVisible(false);
    }

    public void yearEnded() throws IOException {
        nextTurnButton.setDisable(true);
        island.endTheYear();
        setEventPane();
        island.newTurn();
    }
}
