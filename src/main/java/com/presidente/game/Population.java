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
     * @deprecated Populates the factions list (used in tests should be removed)
     */
    public void populate() {
        addFaction(new Faction("capitalistes", 50, 15));
        addFaction(new Faction("communistes", 50, 15));
        addFaction(new Faction("libéraux", 50, 15));
        addFaction(new Faction("religieux", 50, 15));
        addFaction(new Faction("militaristes", 50, 15));
        addFaction(new Faction("écologistes", 50, 15));
        addFaction(new Faction("nationalistes", 50, 15));
        addFaction(new Faction("loyalistes", 50, 15));
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
            peopleToAdd = rand.nextInt(11) / (100 * getTotalPopulation());
        }
        int percentPerFaction = 100 / factions.size();
        for (Faction faction : factions.values()) {
            faction.addPeople(peopleToAdd * percentPerFaction);
        }
    }

    /**
     * Reduces the population according to the amount of food needed to feed everyone with the current food production
     *
     * @param agricultureProduction The amount of food produced this year
     */
    private void reducePeople(int agricultureProduction) {
        int peopleToRemove = getTotalPopulation() - agricultureProduction / 4;
        int percentPerFaction = 100 / factions.size();
        for (Faction faction : factions.values()) {
            faction.removePeople(peopleToRemove * percentPerFaction);
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
            reducePeople(agriculture * 40);
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
     * @param name The name of the faction to be returned
     * @return The faction corresponding to the given name
     */
    public Faction getFactionByName(String name) {
        return factions.get(name);
    }

    public int getFactionCorruptionCost(int index, int amount) {
        return ((Faction) factions.values().toArray()[index]).getCorruptionCost() * amount;
    }

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