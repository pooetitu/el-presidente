package com.presidente.game;

import com.presidente.game.event.Event;

import java.util.ArrayList;
import java.util.Random;

public class Season {
    private final ArrayList<Event> events;

    public Season() {
        events = new ArrayList<>();
    }

    public static String getSeason(int index) {
        String season = "";
        switch (index) {
            case 0: {
                season = "printemps";
                break;
            }
            case 1: {
                season = "été";
                break;
            }
            case 2: {
                season = "automne";
                break;
            }
            case 3: {
                season = "hiver";
                break;
            }
        }
        return season;
    }

    public Event getRandomEvent() {
        Random rand = new Random();
        return events.get(rand.nextInt(events.size()));
    }
}
