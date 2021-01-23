package game.event.effect;

import game.Island;

public abstract class EventEffect {
    private String description;
    private int amount;

    public abstract void applyEffect(Island island);
}
