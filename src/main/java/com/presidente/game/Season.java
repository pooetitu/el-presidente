package com.presidente.game;

import com.presidente.game.event.Event;

import java.util.ArrayList;
import java.util.Random;

public class Season {
    /**
     * An array mapping each season with its name
     */
    private static final String[] seasonNames = {"Printemps", "Été", "Automne", "Hiver"};
    /**
     * The list of the events contained in the season
     */
    private final ArrayList<Event> events;

    public Season() {
        events = new ArrayList<>();
    }

    /**
     * Get the name of the season by its index
     *
     * @param index The index of the season name to be returned
     * @return The name of the season as a String
     */
    public static String getSeason(int index) {
        return seasonNames[index];
    }

    /**
     * @return A random event is returned from the season's events list
     */
    public Event getRandomEvent() {
        Random rand = new Random();
        return events.get(rand.nextInt(events.size()));
    }
}
