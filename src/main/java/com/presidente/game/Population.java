package com.presidente.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Random;

public class Population {
    private final LinkedHashMap<String, Faction> factions;

    public Population() {
        factions = new LinkedHashMap<>();
    }

    /**
     * Adds a Faction to the factions list and sets its key as the given faction's name
     *
     * @param faction The faction to be added
     */
    public void addFaction(Faction faction) {
        factions.put(faction.getName(), faction);
    }

    /**
     * Adds an amount calculated according to a percentage from 0 to 10 percent of the total population count
     */
    private void addPeople() {
        Random rand = new Random();
        int peopleToAdd = 0;
        if (getTotalPopulation() > 0) {
            peopleToAdd = (int) (getTotalPopulation() * ((rand.nextInt(10) + 1) / 100.0));
        }
        double percentPerFaction = (100.0 / factions.size()) / 100.0;
        for (Faction faction : factions.values()) {
            faction.addPeople((int) Math.floor(peopleToAdd * percentPerFaction));
        }
    }

    /**
     * If the food production is insufficient the total population is reduced either way the population is reduced according to a random percentage of the amount of people unfed
     *
     * @param agricultureProduction The amount of food produced this year
     * @param foodRest              The amount of food that is left
     */
    private void reducePeople(int agricultureProduction, int foodRest) {
        Random rand = new Random();
        int peopleToRemove;
        if (agricultureProduction / 4 < getTotalPopulation()) {
            peopleToRemove = getTotalPopulation() - agricultureProduction / 4;
        } else {
            peopleToRemove = (int) (-foodRest / 4 * ((rand.nextInt(100) + 1) / 100.0));
        }
        double percentPerFaction = (100.0 / factions.size()) / 100.0;
        for (Faction faction : factions.values()) {
            faction.removePeople((int) Math.floor(peopleToRemove * percentPerFaction));
            faction.setSatisfaction(faction.getSatisfaction() - peopleToRemove * 2);
        }
    }

    /**
     * If the food left is a positive amount people are added to the island otherwise the population is reduced
     *
     * @param foodRest    The amount of food that is left
     * @param agriculture The percentage of agriculture on the island
     */
    public void calculateNewPeopleCount(int foodRest, int agriculture) {
        if (foodRest >= 0) {
            addPeople();
        } else {
            reducePeople(agriculture * 40, foodRest);
        }
    }

    /**
     * Corrupts a faction corresponding to the given index of the factions list and reduce the loyalist' satisfaction
     *
     * @param index  The index of the faction to be corrupted
     * @param amount The amount of time to corrupt it
     */
    public void corruptFaction(int index, int amount) {
        Faction faction = (Faction) factions.values().toArray()[index];
        faction.corrupt(amount);
        Faction loyalist = getFactionByName("loyalistes");
        loyalist.setSatisfaction((loyalist.getSatisfaction() - faction.getCorruptionCost() / 10) * amount);
    }

    /**
     * @return The number of person on the island
     */
    public int getTotalPopulation() {
        return factions.values().stream().mapToInt(Faction::getSupporter).sum();
    }

    /**
     * Calculates the global satisfaction with the given calculus the sum of the amount of supporters times the satisfaction for each faction divided by the total population
     *
     * @return The global satisfaction for every factions
     */
    public int getGlobalSatisfaction() {
        int totalSupporter = 0;
        int totalSatisfaction = 0;
        for (Faction faction : factions.values()) {
            totalSatisfaction += faction.getSupporter() * faction.getSatisfaction();
            totalSupporter += faction.getSupporter();
        }
        if (totalSatisfaction == 0 || totalSupporter == 0) return 0;
        return (totalSatisfaction) / totalSupporter;
    }

    /**
     * Returns a faction for the given name the faction is created with the sandbox values if it does not exist
     *
     * @param name The name of the faction to be returned
     * @return The faction corresponding to the given name
     */
    public Faction getFactionByName(String name) {
        if (!factions.containsKey(name)) {
            addFaction(new Faction(name, 50, 15));
        }
        return factions.get(name);
    }

    /**
     * Calculate the cost for the given faction times the amount of corruption to be bought
     *
     * @param index  The index of the faction to be corrupted
     * @param amount The amount of time to corrupt it
     * @return The cost of the faction's corruption
     */
    public int getFactionCorruptionCost(int index, int amount) {
        return ((Faction) factions.values().toArray()[index]).getCorruptionCost() * amount;
    }

    /**
     * @return A list of the factions
     */
    public Collection<Faction> getFactions() {
        return factions.values();
    }

    /**
     * @param name The name of the faction
     * @return The index of the faction corresponding to the given name
     */
    public int getFactionIndex(String name) {
        return new ArrayList<>(factions.keySet()).indexOf(name);
    }

    @Override
    public String toString() {
        StringBuilder display = new StringBuilder();
        for (Faction faction : factions.values()) {
            display.append(faction).append("\n");
        }
        return display.toString();
    }
}
