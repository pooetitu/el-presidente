package game.event;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import game.Island;
import game.event.effect.EventEffect;

import java.util.Arrays;
import java.util.Iterator;


@XStreamAlias("event-choice")
public class EventChoice {
    private final String description;
    @XStreamImplicit(itemFieldName = "effect")
    private final EventEffect[] effects;

    public EventChoice(String description, EventEffect[] effects) {
        this.description = description;
        this.effects = effects;
    }

    public String display(double effectRatio) {
        StringBuilder display = new StringBuilder(description).append("\neffets: ");
        Iterator<EventEffect> eventEffectIterator = Arrays.stream(effects).iterator();
        while (eventEffectIterator.hasNext()) {
            display.append(eventEffectIterator.next().display(effectRatio));
            if (eventEffectIterator.hasNext()) {
                display.append(", ");
            }
        }
        return display.toString();
    }

    public void applyEffects(Island island) {
        for (EventEffect effect : effects) {
            effect.applyEffect(island);
        }
    }
}
