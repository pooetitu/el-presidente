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

    public void addFaction(Faction faction) {
        factions.put(faction.getName(), faction);
    }

    private void addPeople() {
        Random rand = new Random();
        int peopleToAdd = rand.nextInt(11) / (100 * getTotalPopulation());
        int percentPerFaction = 100 / factions.size();
        for (Faction faction : factions.values()) {
            faction.addPeople(peopleToAdd * percentPerFaction);
        }
    }

    private void reducePeople(int agricultureProduction) {
        int peopleToRemove = getTotalPopulation() - agricultureProduction / 4;
        int percentPerFaction = 100 / factions.size();
        for (Faction faction : factions.values()) {
            faction.removePeople(peopleToRemove * percentPerFaction);
        }
    }

    public void calculateNewPeopleCount(int foodRest, int agriculture) {
        if (foodRest >= 0) {
            addPeople();
        } else {
            reducePeople(agriculture * 40);
        }
    }

    public void corruptFaction(int index, int amount) {
        Faction faction = (Faction) factions.values().toArray()[index];
        faction.corrupt(amount);
        Faction loyalist = getFactionByName("loyalistes");
        loyalist.setSatisfaction((loyalist.getSatisfaction() - faction.getCorruptionCost() / 10) * amount);
    }

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

    public Faction getFactionByName(String name) {
        return factions.get(name);
    }

    public String corruptionDisplay() {
        StringBuilder display = new StringBuilder();
        int count = 0;
        for (Faction faction : factions.values()) {
            display.append(count).append(". ").append(faction.getName()).append(" - ").append(faction.getCorruptionCost()).append("$").append("\n");
            count++;
        }
        display.append(count).append(". ").append("Retour");
        return display.toString();
    }

    public int getFactionCount() {
        return factions.size();
    }

    public int getFactionCorruptionCost(int index, int amount) {
        return ((Faction) factions.values().toArray()[index]).getCorruptionCost() * amount;
    }

    public Collection<Faction> getFactions() {
        return factions.values();
    }

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
