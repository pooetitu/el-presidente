package com.presidente.game.event;

import com.presidente.game.Island;

import java.util.Arrays;

/**
 * An event which will affect the Island
 */
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
     * Applies the choice at the chosen index on the given Island instance
     *
     * @param island      The island on which the effects will be applied
     * @param choiceIndex The index of the event choice to be applied
     */
    public void applyChoice(Island island, int choiceIndex) {
        if (choiceIndex >= 0 && choiceIndex < choices.length) {
            choices[choiceIndex].applyEffects(island);
        }
    }

    /**
     * Returns the instance of the following Event if it exists
     *
     * @return An instance of Event
     */
    public Event getNextEvent() {
        return nextEvent;
    }

    /**
     * Filters the choices of the Event with the current game difficulty and returns a filtered array of choices
     *
     * @param island An instance of Island of which the difficulty will be applied
     * @return The list of selectable choices
     */
    public EventChoice[] getChoices(Island island) {
        return Arrays.stream(choices)
                .filter(c -> c.getDifficultyThreshold() == null || c.getDifficultyThreshold().compareTo(island.getDifficulty()) > 0)
                .toArray(EventChoice[]::new);
    }

    public String getDescription() {
        return description;
    }
}
