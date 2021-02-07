package game;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import game.event.Event;

import java.util.ArrayList;
import java.util.Random;

@XStreamAlias("season")
public class Season {
    @XStreamImplicit(itemFieldName = "event")
    private final ArrayList<Event> events;

    public Season() {
        events = new ArrayList<>();
    }

    public Event getRandomEvent() {
        Random rand = new Random();
        return events.get(rand.nextInt(events.size()));
    }

    public void addEvent(Event newEvent) {
        events.add(newEvent);
    }
}
