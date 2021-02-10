package game;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import game.event.Event;
import utils.ScenarioLoader;

import java.io.IOException;
import java.util.LinkedList;

@XStreamAlias("island")
public class Island {
    private final Ressource ressource;
    private final Population population;
    @XStreamImplicit(itemFieldName = "event")
    private LinkedList<Event> eventsQueue;
    private GameDifficulty difficulty;
    @XStreamImplicit(itemFieldName = "season")
    private Season[] seasons;
    private int agriculture;
    private int industrie;
    private int turn;

    public Island(int agriculture, int industrie, Ressource ressource) throws IOException {
        this.turn = 0;
        this.agriculture = agriculture;
        this.industrie = industrie;
        this.ressource = ressource;
        this.eventsQueue = new LinkedList<>();
        this.population = new Population();
        this.population.populate();
        this.seasons = ScenarioLoader.getScenarioLoader().loadSeasons();
    }

    public Island(int agriculture, int industrie, GameDifficulty difficulty, Ressource ressource) throws IOException {
        this.turn = 0;
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

    public boolean isGameOver() {
        return population.getGlobalSatisfaction() < difficulty.getSatisfactionThreshold();
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
        this.agriculture = agriculture;
        if (this.agriculture < 0) this.agriculture = 0;
        if (this.industrie + this.agriculture >= 100)
            this.agriculture = 100 - this.industrie;
    }

    public int getIndustrie() {
        return industrie;
    }

    public void setIndustrie(int industrie) {
        this.industrie = industrie;
        if (this.industrie < 0) this.industrie = 0;
        if (this.agriculture + this.industrie >= 100)
            this.industrie = 100 - this.agriculture;
    }

    public Event getNextEvent() {
        if (!eventsQueue.isEmpty()) {
            return eventsQueue.remove();
        }
        Event event = seasons[turn % 4].getRandomEvent();
        addNextEventToQueue(event.getNextEvent());
        return event;
    }

    private void addNextEventToQueue(Event event) {
        if (event != null) {
            eventsQueue.add(event);
            addNextEventToQueue(event.getNextEvent());
        }
    }

    public void newTurn() {
        turn++;
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

    public int getTurn() {
        return turn;
    }

    @Override
    public String toString() {
        return ressource + "\n" +
                String.format("%-21s%s", "agriculture: " + agriculture + "%", "industrie: " + industrie + "%") + "\n" +
                "global satisfaction: " + population.getGlobalSatisfaction();
    }
}
