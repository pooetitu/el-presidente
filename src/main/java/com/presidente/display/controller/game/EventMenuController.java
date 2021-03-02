package com.presidente.display.controller.game;

import com.presidente.game.event.Event;
import com.presidente.game.event.EventChoice;
import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class EventMenuController {
    public GameController controller;
    public Label eventLabel;
    public VBox eventChoiceVBox;

    public void setController(GameController controller) {
        this.controller = controller;
        addLabels();
    }

    private void addLabels() {
        eventChoiceVBox.setVisible(true);
        Event event = controller.getIsland().getNextEvent();
        eventLabel.setText(event.getDescription());
        for (EventChoice eventChoice : event.getChoices(controller.getIsland())) {
            eventChoiceVBox.getChildren().add(initLabel(eventChoice));
        }
    }

    private Label initLabel(EventChoice eventChoice) {
        Label label = new Label(eventChoice.display(controller.getIsland().getDifficulty().getEffectRatio()));
        label.setOnMouseReleased(e -> {
            eventChoice.applyEffects(controller.getIsland());
            eventChoiceVBox.setVisible(false);
            controller.readyForNextTurn();
        });
        label.setWrapText(true);
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);
        label.getStyleClass().add("label-choice");
        label.prefWidth(Control.USE_COMPUTED_SIZE);
        return label;
    }
}
