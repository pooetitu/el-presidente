package game;

import game.event.Event;

import java.util.ArrayList;
import java.util.Random;

public class Season {
    private final ArrayList<Event> events;
    private String name;

    public Season() {
        events = new ArrayList<>();
    }

    public Event getRandomEvent() {
        Random rand = new Random();
        return events.get(rand.nextInt(events.size()));
    }
}
