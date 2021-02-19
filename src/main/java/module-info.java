module com.presidente {
    requires com.google.gson;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.presidente.display to javafx.fxml;

    opens com.presidente.game to com.google.gson;
    opens com.presidente.game.event to com.google.gson;
    opens com.presidente.game.event.effect to com.google.gson;
    opens com.presidente.game.event.effect.calculation to com.google.gson;
    opens com.presidente.display.controller to javafx.fxml;

    exports com.presidente.display to javafx.graphics;
    exports com.presidente.display.controller to javafx.fxml;
}