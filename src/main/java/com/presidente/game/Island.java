package com.presidente.game;

import com.presidente.game.event.Event;
import com.presidente.utils.ScenarioLoader;

import java.util.LinkedList;

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
    private int industrie;
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

    public Island(int agriculture, int industrie, Resource resource) {
        this.turn = 0;
        this.agriculture = agriculture;
        this.industrie = industrie;
        this.resource = resource;
        this.eventsQueue = new LinkedList<>();
        this.population = new Population();
        this.population.populate();
        this.seasons = ScenarioLoader.getInstance().loadSeasons();
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
        this.seasons = ScenarioLoader.getInstance().loadSeasons();
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
        if (this.industrie + this.agriculture >= 100) {
            this.agriculture = 100 - this.industrie;
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
            resource.addIndustriePayoff(industrie);
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

    public int getIndustrie() {
        return industrie;
    }

    /**
     * The amount of industrie is capped between 0 and 100
     *
     * @param industrie The new value of agriculture to be set
     */
    public void setIndustrie(int industrie) {
        this.industrie = industrie;
        if (this.industrie < 0) {
            this.industrie = 0;
        }
        if (this.agriculture + this.industrie >= 100) {
            this.industrie = 100 - this.agriculture;
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
}
