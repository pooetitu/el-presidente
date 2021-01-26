package game;

import java.util.HashMap;
import java.util.Map;

public class Population {
    private final Map<String, Faction> factions;

    public Population() {
        factions = new HashMap<>();
    }

    public void addFaction(Faction faction) {
        factions.put(faction.getName(), faction);
    }

    public int getTotalPopulation() {
        return factions.values().stream().mapToInt(Faction::getSupporter).sum();
    }

    public Faction getFactionByName(String name) {
        return factions.get(name);
    }

    public String corruptionDisplay() {
        StringBuilder display = new StringBuilder("0. Retour");
        int count = 1;
        for (Faction faction : factions.values()) {
            display.append("\n").append(count).append(". ").append(faction.getName()).append(" - ").append(faction.getCorruptionCost());
            count++;
        }
        return display.toString();
    }
}
