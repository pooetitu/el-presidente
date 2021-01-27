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
        int counter = 1;
        for (EventChoice choice : choices) {
            display.append("\n").append(counter).append(". ").append(choice.display());
            counter++;
        }
        return display.toString();
    }
}
