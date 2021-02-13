package game;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.Collection;
import java.util.LinkedHashMap;

@XStreamAlias("population")
public class Population {
    @XStreamImplicit(itemFieldName = "faction", keyFieldName = "name")
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

    public void corruptFaction(int index, int amount) {
        Faction faction = (Faction) factions.values().toArray()[index];
        faction.corrupt(amount);
        getFactionByName("loyalistes").setSatisfaction((getFactionByName("loyalistes").getSatisfaction() - faction.getCorruptionCost() / 10) * amount);
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
        StringBuilder display = new StringBuilder("0. Retour");
        int count = 1;
        for (Faction faction : factions.values()) {
            display.append("\n").append(count).append(". ").append(faction.getName()).append(" - ").append(faction.getCorruptionCost()).append("$");
            count++;
        }
        return display.toString();
    }

    public int getFactionCount() {
        return factions.size();
    }

    public int getFactionCorruptionCost(int index, int amount) {
        return ((Faction) factions.values().toArray()[index]).getCorruptionCost() * amount;
    }

    public Collection<game.Faction> getFactions() {
        return factions.values();
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
