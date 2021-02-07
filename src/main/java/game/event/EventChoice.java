package game.event;

import com.thoughtworks.xstream.annotations.XStreamImplicit;
import game.Island;
import game.event.effect.EventEffect;

public class EventChoice {
    private final String description;
    @XStreamImplicit(itemFieldName = "effect")
    private final EventEffect[] effects;

    public EventChoice(String description, EventEffect[] effects) {
        this.description = description;
        this.effects = effects;
    }

    public String display() {
        StringBuilder display = new StringBuilder(description).append("\neffets: ");
        for (EventEffect effect : effects)
            display.append(effect.display());
        return display.toString();
    }

    public void applyEffects(Island island) {
        for (EventEffect effect : effects) {
            effect.applyEffect(island);
        }
    }
}
