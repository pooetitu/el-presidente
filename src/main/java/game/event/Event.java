package game.event;

public class Event {
    private final String description;
    private final Event nextEvent;
    private final EventChoice[] choices;

    public Event(String description, Event nextEvent, EventChoice[] choices) {
        this.description = description;
        this.nextEvent = nextEvent;
        this.choices = choices;
    }
}
