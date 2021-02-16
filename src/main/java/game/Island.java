package game;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import game.event.Event;
import utils.ScenarioLoader;

import java.util.LinkedList;

@XStreamAlias("island")
public class Island {
    private final Resource resource;
    private final Population population;
    @XStreamImplicit(itemFieldName = "event")
    private LinkedList<Event> eventsQueue;
    private GameDifficulty difficulty;
    @XStreamOmitField
    private Season[] seasons;
    private int agriculture;
    private int industrie;
    private int turn;

    public Island(int agriculture, int industrie, Resource resource) {
        this.turn = 0;
        this.agriculture = agriculture;
        this.industrie = industrie;
        this.resource = resource;
        this.eventsQueue = new LinkedList<>();
        this.population = new Population();
        this.population.populate();
        this.seasons = ScenarioLoader.getScenarioLoader().loadSeasons();
    }

    public Island(int agriculture, int industrie, GameDifficulty difficulty, Resource resource) {
        this.turn = 0;
        this.agriculture = agriculture;
        this.industrie = industrie;
        this.difficulty = difficulty;
        this.resource = resource;
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

    public void corruptFaction(int factionIndex, int amount) {
        if (amount <= getMaximumPurchasableCorruption(factionIndex)) {
            population.corruptFaction(factionIndex, amount);
            resource.setTreasury(resource.getTreasury() - population.getFactionCorruptionCost(factionIndex, amount));
        }
    }

    public int getMaximumPurchasableCorruption(int factionIndex) {
        return resource.getTreasury() / population.getFactionCorruptionCost(factionIndex, 1);
    }

    public int getAgriculture() {
        return agriculture;
    }

    public void setAgriculture(int agriculture) {
        this.agriculture = agriculture;
        if (this.agriculture < 0) {
            this.agriculture = 0;
        }
        if (this.industrie + this.agriculture >= 100) {
            this.agriculture = 100 - this.industrie;
        }
    }

    public boolean isEndOfYear() {
        if (turn % 4 == 0) {
            resource.addIndustriePayoff(industrie);
            resource.addAgriculturePayoff(agriculture);
            return true;
        }
        return false;
    }

    public void endTheYear() {
        int foodRest = resource.consumeFood(population.getTotalPopulation());
        population.calculateNewPeopleCount(foodRest, agriculture);
    }

    public int getIndustrie() {
        return industrie;
    }

    public void setIndustrie(int industrie) {
        this.industrie = industrie;
        if (this.industrie < 0) {
            this.industrie = 0;
        }
        if (this.agriculture + this.industrie >= 100) {
            this.industrie = 100 - this.agriculture;
        }
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

    public Resource getResource() {
        return resource;
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
        return resource + "\n" +
                String.format("%-21s%s", "Agriculture: " + agriculture + "%", "Industrie: " + industrie + "%") + "\n" +
                "Satisfaction globale: " + population.getGlobalSatisfaction();
    }

    public String advancedDisplay() {
        return population + toString();
    }
}
