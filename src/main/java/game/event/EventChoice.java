package game.event;

import game.event.effect.EventEffect;

public class EventChoice {
    private final String description;
    private final EventEffect[] effects;

    public EventChoice(String description, EventEffect[] effects) {
        this.description = description;
        this.effects = effects;
    }

    public String display() {
        return null;
    }
}
