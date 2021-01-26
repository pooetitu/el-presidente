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
        return 0;
    }

    public Faction getFactionByName(String name) {
        return factions.get(name);
    }

    public String corruptionDisplay() {
        return null;
    }
}
