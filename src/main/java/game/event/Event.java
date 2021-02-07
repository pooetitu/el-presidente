package game.event;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import game.Island;

@XStreamAlias("event")
public class Event {
    private final String description;
    private final Event nextEvent;

    @XStreamImplicit(itemFieldName = "choice")
    private final EventChoice[] choices;

    public Event(String description, Event nextEvent, EventChoice[] choices) {
        this.description = description;
        this.nextEvent = nextEvent;
        this.choices = choices;
    }

    public String display() {
        StringBuilder display = new StringBuilder(description);
        int counter = 1;
        for (EventChoice choice : choices) {
            display.append("\n").append(counter).append(". ").append(choice.display());
            counter++;
        }
        return display.toString();
    }

    public void applyChoice(Island island, int choiceIndex) {
        if (choiceIndex >= 0 && choiceIndex < choices.length) {
            choices[choiceIndex].applyEffects(island);
        }
    }

    public int getChoicesCount() {
        return choices.length;
    }

    public Event getNextEvent() {
        return nextEvent;
    }
}
