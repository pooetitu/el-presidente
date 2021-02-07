package game;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

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

    public int corruptFaction(int index) {
        Faction faction = (Faction) factions.values().toArray()[index];
        int corruptionCost = faction.corrupt();
        getFactionByName("loyalistes").setSatisfaction(getFactionByName("loyalistes").getSatisfaction() - corruptionCost / 10);
        return corruptionCost;
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
}
