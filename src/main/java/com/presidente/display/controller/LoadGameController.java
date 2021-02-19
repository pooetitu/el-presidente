package com.presidente.display.controller;

import com.presidente.display.App;
import com.presidente.game.GameDifficulty;
import com.presidente.game.Island;
import com.presidente.game.Resource;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class LoadGameController {
    @FXML
    public TableView saveList;

    @FXML
    public void loadGame() throws IOException {
        Stage stage = (Stage) saveList.getScene().getWindow();
        stage.close();
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("game.fxml"));
            stage.setUserData(new Island(15, 15, GameDifficulty.NORMAL, new Resource(10, 10)));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
    }

    @FXML
    public void switchToMainMenu() throws IOException {
        App.setRoot("main_menu");
    }
}
