package com.presidente.display;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    /**
     * Loads a fxml file and sets it as the root scene
     *
     * @param fxml The name and directory in which the file is located
     * @return the loader
     */
    public static FXMLLoader setRoot(String fxml) throws IOException {
        FXMLLoader loader = loadFXML(fxml);
        scene.setRoot(loader.load());
        return loader;
    }

    /**
     * Loads a fxml file
     *
     * @param fxml The name and directory in which the file is located
     * @return An instance of FXMLLoader
     */
    public static FXMLLoader loadFXML(String fxml) {
        return new FXMLLoader(App.class.getResource("/com/presidente/gui/" + fxml + ".fxml"));
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("main_menu").load(), 800, 600);
        stage.setTitle("El Presidente");
        stage.getIcons().add(new Image(App.class.getResource("/com/presidente/gui/images/icon.png").toString()));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}