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
        StringBuilder display = new StringBuilder(description);
        for (EventEffect effect : effects)
            display.append("\n").append(effect.display());
        return display.toString();
    }
}
