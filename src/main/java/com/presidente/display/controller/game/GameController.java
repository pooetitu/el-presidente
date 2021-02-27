package com.presidente.display.controller.game;

import com.presidente.display.App;
import com.presidente.display.controller.menu.PauseMenuController;
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
    public Label globalSatisfactionLabel;
    private Island island;

    @FXML
    public void initialize() {
        factionName.setCellValueFactory(f -> new ReadOnlyObjectWrapper<>(f.getValue().getName()));
        factionSatisfaction.setCellValueFactory(f -> new ReadOnlyObjectWrapper<>(f.getValue().getSatisfaction()));
        factionSupporter.setCellValueFactory(f -> new ReadOnlyObjectWrapper<>(f.getValue().getSupporter()));
    }

    /**
     * Display is refreshed and the next turn button is disabled
     * If it is the end of the year the end of the year menu is displayed, otherwise the event menu is displayed
     */
    @FXML
    public void nextTurn() {
        nextTurnButton.setDisable(true);
        if (island.isEndOfYear()) {
            setEndOfYearPane();
        } else {
            setEventPane();
            island.newTurn();
        }
        refreshLabels();
    }

    /**
     * Display the game over menu over the menu pane
     */
    public void openMenu() {
        ((PauseMenuController) setPane("menu/pause_menu", menuPane).getController()).setController(this);
    }

    /**
     * Display the game over menu over the menu pane
     */
    public void setGameOverPane() {
        setPane("menu/game_over", menuPane);
    }

    /**
     * Display the end of the year menu over the game pane
     */
    public void setEndOfYearPane() {
        ((YearEndMenuController) setPane("game/year_end_menu", gamePane).getController()).setController(this);
    }

    /**
     * Display the event menu over the game pane
     */
    private void setEventPane() {
        ((EventMenuController) setPane("game/event_menu", gamePane).getController()).setController(this);
    }

    public FXMLLoader setPane(String scene, Pane pane) {
        FXMLLoader loader = null;
        try {
            loader = App.loadFXML(scene);
            VBox newLoadedPane = loader.load();
            pane.getChildren().add(newLoadedPane);
            pane.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loader;
    }

    /**
     * Refresh the island's data displayed
     */
    public void refreshLabels() {
        setGlobalSatisfactionLabel();
        setDateLabel();
        setMoneyLabel();
        setFoodLabel();
        setAgricultureLabel();
        setIndustryLabel();
        factionTable.refresh();
    }

    /**
     * Removes any remaining menu displayed in the game pane
     * Display is refreshed and the next turn button is enabled
     * The game over menu is displayed if necessary
     */
    public void readyForNextTurn() {
        nextTurnButton.setDisable(false);
        gamePane.getChildren().clear();
        refreshLabels();
        if (island.isGameOver()) {
            setGameOverPane();
        }
    }

    private void setDateLabel() {
        this.dateLabel.setText(Season.getSeason(island.getTurn() % 4) + " année " + island.getTurn() / 4);
    }

    private void setMoneyLabel() {
        this.moneyLabel.setText("Argent: " + island.getResource().getTreasury());
    }

    private void setFoodLabel() {
        this.foodLabel.setText("Nourriture: " + island.getResource().getFoodQuantity());
    }

    private void setAgricultureLabel() {
        this.agricultureLabel.setText("Agriculture: " + island.getAgriculture() + "%");
    }

    private void setGlobalSatisfactionLabel() {
        this.globalSatisfactionLabel.setText("Satisfaction globale: " + island.getPopulation().getGlobalSatisfaction() + "%");
    }

    private void setIndustryLabel() {
        this.industryLabel.setText("Industrie: " + island.getIndustrie() + "%");
    }

    public Island getIsland() {
        return island;
    }

    public void setIsland(Island island) {
        this.island = island;
        ObservableList<Faction> factionObservableList = FXCollections.observableArrayList();
        factionObservableList.addAll(island.getPopulation().getFactions());
        factionTable.setItems(factionObservableList);
        nextTurn();
    }

    public void closeMenu() {
        menuPane.setVisible(false);
    }

    /**
     * The island is asked to end the year
     * Display is refreshed and the next turn button is enabled
     * The event menu is displayed
     */
    public void yearEnded() {
        nextTurnButton.setDisable(true);
        island.endTheYear();
        refreshLabels();
        setEventPane();
        island.newTurn();
    }
}
