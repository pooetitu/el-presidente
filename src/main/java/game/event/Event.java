package game.event;

import game.Island;

public class Event {
    private final String description;
    private final Event nextEvent;

    private final EventChoice[] choices;

    public Event(String description, Event nextEvent, EventChoice[] choices) {
        this.description = description;
        this.nextEvent = nextEvent;
        this.choices = choices;
    }

    public String display(double effectRatio) {
        StringBuilder display = new StringBuilder(description);
        int counter = 0;
        for (EventChoice choice : choices) {
            display.append("\n").append(counter).append(". ").append(choice.display(effectRatio));
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
