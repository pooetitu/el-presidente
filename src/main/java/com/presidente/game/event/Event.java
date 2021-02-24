package com.presidente.game.event;

import com.presidente.game.Island;

public class Event {
    /**
     * The description of the event
     */
    private final String description;
    /**
     * The event following
     */
    private final Event nextEvent;
    /**
     * The list of choices available for this event
     */
    private final EventChoice[] choices;

    public Event(String description, Event nextEvent, EventChoice[] choices) {
        this.description = description;
        this.nextEvent = nextEvent;
        this.choices = choices;
    }

    /**
     * @param island      The island on which the effects will be applied
     * @param choiceIndex The index of the event choice to be applied
     */
    public void applyChoice(Island island, int choiceIndex) {
        if (choiceIndex >= 0 && choiceIndex < choices.length) {
            choices[choiceIndex].applyEffects(island);
        }
    }

    public Event getNextEvent() {
        return nextEvent;
    }

    public EventChoice[] getChoices() {
        return choices;
    }

    public String getDescription() {
        return description;
    }
}
