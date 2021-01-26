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

    public String display() {
        StringBuilder display = new StringBuilder(description);
        for (EventChoice choice : choices)
            display.append("\n").append(choice.display());
        return display.toString();
    }
}
