module com.presidente {
    requires xstream;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.presidente.game to xstream;
    opens com.presidente.game.event to xstream;
    opens com.presidente.game.event.effect to xstream;
    opens com.presidente.game.event.effect.calculation to xstream;

    opens com.presidente.display to javafx.fxml;

    exports com.presidente.display;
}