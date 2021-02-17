module com.presidente {
    requires xstream;
    opens com.presidente.game;
    opens com.presidente.game.event;
    opens com.presidente.game.event.effect;
    opens com.presidente.game.event.effect.calculation;

    exports com.presidente.display;
}