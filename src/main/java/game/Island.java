package game;

import com.thoughtworks.xstream.annotations.XStreamImplicit;
import game.event.Event;
import utils.ScenarioLoader;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Island {
    private final Ressource ressource;
    private final Population population;
    @XStreamImplicit(itemFieldName = "event")
    private Queue<Event> eventsQueue;
    private GameDifficulty difficulty;
    @XStreamImplicit(itemFieldName = "season")
    private Season[] seasons;
    private int agriculture;
    private int industrie;

    // New sandbox game constructor
    public Island(int agriculture, int industrie, Ressource ressource) throws IOException {
        this.agriculture = agriculture;
        this.industrie = industrie;
        this.ressource = ressource;
        this.eventsQueue = new LinkedList<>();
        this.population = new Population();
        this.population.populate();
        this.seasons = ScenarioLoader.getScenarioLoader().loadSeasons();
    }

    // New sandbox game constructor with predefined difficulty
    public Island(int agriculture, int industrie, GameDifficulty difficulty, Ressource ressource) throws IOException {
        this.agriculture = agriculture;
        this.industrie = industrie;
        this.difficulty = difficulty;
        this.ressource = ressource;
        this.eventsQueue = new LinkedList<>();
        this.population = new Population();
        this.population.populate();
        this.seasons = ScenarioLoader.getScenarioLoader().loadSeasons();
    }

    public void init() {
        seasons = ScenarioLoader.getScenarioLoader().loadSeasons();
        if (eventsQueue == null) {
            eventsQueue = new LinkedList<>();
        }
    }

    public void corruptFaction(int factionIndex) {
        if (ressource.getTreasury() <= 0) return;
        int corruptCost = ressource.getTreasury() - population.corruptFaction(factionIndex);
        ressource.setTreasury(corruptCost);
    }

    public int getAgriculture() {
        return agriculture;
    }

    public void setAgriculture(int agriculture) {
        if (this.industrie + agriculture >= 100)
            this.agriculture = 100 - this.industrie;
        else
            this.agriculture = agriculture;
    }

    public int getIndustrie() {
        return industrie;
    }

    public void setIndustrie(int industrie) {
        if (this.agriculture + industrie >= 100)
            this.industrie = 100 - this.agriculture;
        else
            this.industrie = industrie;
    }

    public Event getNextEvent(int currentSeasonIndex) {
        if (!eventsQueue.isEmpty()) {
            return eventsQueue.remove();
        }
        Event event = seasons[currentSeasonIndex].getRandomEvent();
        addNextEventToQueue(event.getNextEvent());
        return event;
    }

    private void addNextEventToQueue(Event event) {
        if (event != null) {
            eventsQueue.add(event);
            addNextEventToQueue(event.getNextEvent());
        }
    }

    public Population getPopulation() {
        return population;
    }

    public Ressource getRessources() {
        return ressource;
    }

    public GameDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(GameDifficulty difficulty) {
        this.difficulty = difficulty;
    }


    @Override
    public String toString() {
        return ressource + "\n" +
                String.format("%-21s%s", "agriculture: " + agriculture + "%",
                        "industrie: " + industrie + "%") + "\n" + "global satisfaction: " + population.getGlobalSatisfaction();
    }
}
