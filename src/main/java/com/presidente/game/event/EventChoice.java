package com.presidente.game.event;

import com.presidente.game.Island;
import com.presidente.game.event.effect.EventEffect;

import java.util.Arrays;
import java.util.Iterator;


public class EventChoice {
    private final EventEffect[] effects;
    private String description;

    public EventChoice() {
        effects = new EventEffect[0];
    }

    public EventChoice(String description, EventEffect[] effects) {
        this.description = description;
        this.effects = effects;
    }

    /**
     * Builds a String containing the description of the choice followed by the different effects applied by this choice
     *
     * @param effectRatio The ratio applied to the effect to adjust the difficulty
     * @return The String display of the choice
     */
    public String display(double effectRatio) {
        StringBuilder display = new StringBuilder(description);
        if (effects.length > 0) {
            display.append("\neffets: ");
        }
        Iterator<EventEffect> eventEffectIterator = Arrays.stream(effects).iterator();
        while (eventEffectIterator.hasNext()) {
            display.append(eventEffectIterator.next().display(effectRatio));
            if (eventEffectIterator.hasNext()) {
                display.append(", ");
            }
        }
        return display.toString();
    }

    /**
     * Apply each effect on the given Island
     *
     * @param island The Island on which the effects are applied
     */
    public void applyEffects(Island island) {
        for (EventEffect effect : effects) {
            effect.applyEffect(island);
        }
    }
}
