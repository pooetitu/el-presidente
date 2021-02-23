package com.presidente.game;

import com.presidente.game.event.Event;

import java.util.ArrayList;
import java.util.Random;

public class Season {
    private static final String[] seasonNames = {"Printemps", "Été", "Automne", "Hiver"};
    private final ArrayList<Event> events;
    private static final String[] seasonNames = {"Printemps", "Été", "Automne", "Hiver"};

    public Season() {
        events = new ArrayList<>();
    }

    public static String getSeason(int index) {
        return seasonNames[index];
    }

    public Event getRandomEvent() {
        Random rand = new Random();
        return events.get(rand.nextInt(events.size()));
    }
}
