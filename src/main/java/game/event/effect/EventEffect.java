package game.event.effect;

import game.Island;

public abstract class EventEffect {
    private final String description;
    private final double amount;

    public EventEffect(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    public abstract void applyEffect(Island island);

    public String display() {
        return null;
    }
}
