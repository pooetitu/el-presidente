package com.presidente.game;

import com.presidente.game.event.Event;
import com.presidente.utils.ScenarioLoader;

import java.util.LinkedList;

/**
 * The main game object it links together every game component
 */
public class Island {
    /**
     * An array of seasons which can be used to get a random event from the current season, it is transient to have lighter game save
     */
    private final transient Season[] seasons;
    /**
     * The treasury and the food possesses on this island
     */
    private Resource resource;
    /**
     * Contains the list of factions
     */
    private Population population;
    /**
     * A list of events incoming
     */
    private LinkedList<Event> eventsQueue;
    /**
     * The defined difficulty of the game
     */
    private GameDifficulty difficulty;
    /**
     * The percentage of agriculture on the island, stored as an integer between 0 and 100
     */
    private int agriculture;
    /**
     * The percentage of industry on the island, stored as an integer between 0 and 100
     */
    private int industry;
    /**
     * The current turn this data is stored in the island to be written in the save file
     */
    private int turn;

    public Island() {
        seasons = ScenarioLoader.getInstance().loadSeasons();
        if (eventsQueue == null) {
            eventsQueue = new LinkedList<>();
        }
    }

    /**
     * Checks if the global satisfaction goes under the threshold set in the difficulty
     *
     * @return Is the game over or not
     */
    public boolean isGameOver() {
        return population.getGlobalSatisfaction() < difficulty.getSatisfactionThreshold();
    }

    /**
     * Checks if the island is able to pay for the amount given,
     * corrupts the faction and sets the new amount of money
     *
     * @param factionIndex The index of the faction to corrupt
     * @param amount       The amount of time to corrupt this faction
     */
    public void corruptFaction(int factionIndex, int amount) {
        if (amount <= getMaximumPurchasableCorruption(factionIndex)) {
            population.corruptFaction(factionIndex, amount);
            resource.setTreasury(resource.getTreasury() - population.getFactionCorruptionCost(factionIndex, amount));
        }
    }

    /**
     * Calculate the amount of time a faction is corruptible
     *
     * @param factionIndex The index of the faction to corrupt
     * @return The amount of time the faction can be corrupted
     */
    public int getMaximumPurchasableCorruption(int factionIndex) {
        if (population.getFactionCorruptionCost(factionIndex, 1) <= 0) {
            return 0;
        }
        return resource.getTreasury() / population.getFactionCorruptionCost(factionIndex, 1);
    }

    public int getAgriculture() {
        return agriculture;
    }

    /**
     * The amount of agriculture is capped between 0 and 100
     *
     * @param agriculture The new value of agriculture to be set
     */
    public void setAgriculture(int agriculture) {
        this.agriculture = agriculture;
        if (this.agriculture < 0) {
            this.agriculture = 0;
        }
        if (this.industry + this.agriculture >= 100) {
            this.agriculture = 100 - this.industry;
        }
    }

    /**
     * Checks if the year is finished,
     * adds the new amount of food from the agriculture and the money from the industries
     *
     * @return True if the year is finished, False if not
     */
    public boolean isEndOfYear() {
        if (turn % 4 == 3) {
            resource.addIndustryPayoff(industry);
            resource.addAgriculturePayoff(agriculture, turn / 4);
            return true;
        }
        return false;
    }

    /**
     * Consumes the food and calculates the new population proportion
     */
    public void endTheYear() {
        resource.removeExpiredFood(turn / 4);
        int foodRest = resource.consumeFood(population.getTotalPopulation());
        population.calculateNewPeopleCount(foodRest, agriculture);
    }

    public int getIndustry() {
        return industry;
    }

    /**
     * The amount of industry is capped between 0 and 100
     *
     * @param industry The new value of agriculture to be set
     */
    public void setIndustry(int industry) {
        this.industry = industry;
        if (this.industry < 0) {
            this.industry = 0;
        }
        if (this.agriculture + this.industry >= 100) {
            this.industry = 100 - this.agriculture;
        }
    }

    /**
     * Gets an event from the eventQueue otherwise if it's empty a random Event is get from the current season
     *
     * @return An instance of the Event to be played
     */
    public Event getNextEvent() {
        if (!eventsQueue.isEmpty()) {
            return eventsQueue.remove();
        }
        Event event = seasons[turn % 4].getRandomEvent();
        addNextEventToQueue(event.getNextEvent());
        return event;
    }

    /**
     * This function is called recursively on the given event stops when the given event is null
     *
     * @param event The event to be added to the list
     */
    private void addNextEventToQueue(Event event) {
        if (event != null) {
            eventsQueue.add(event);
            addNextEventToQueue(event.getNextEvent());
        }
    }

    /**
     * Increments the current turn
     */
    public void newTurn() {
        turn++;
    }

    public Population getPopulation() {
        return population;
    }

    public void setPopulation(Population population) {
        this.population = population;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
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

    public void setTurn(int turn) {
        this.turn = turn;
    }

    @Override
    public String toString() {
        return resource + "\n" +
                String.format("%-21s%s", "Agriculture: " + agriculture + "%", "Industry: " + industry + "%") + "\n" +
                "Satisfaction globale: " + population.getGlobalSatisfaction();
    }
}
