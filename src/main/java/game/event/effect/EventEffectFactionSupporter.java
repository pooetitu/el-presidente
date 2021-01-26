package game.event.effect;

import game.Faction;
import game.Island;
import game.event.effect.calculation.Calculation;

import java.util.ArrayList;

public class EventEffectFactionSupporter extends EventEffect {
    private final ArrayList<String> factions;

    public EventEffectFactionSupporter(String description, double amount, Calculation calculationMethod) {
        super(description, amount, calculationMethod);
        factions = new ArrayList<>();
    }


    @Override
    public void applyEffect(Island island) {
        for (String factionName : factions) {
            Faction faction = island.getPopulation().getFactionByName(factionName);
            System.out.println(faction);
            int result = calculateNewValue(faction.getSatisfaction(), island.getDifficulty().getEffectRatio());
            System.out.println("Setting " + factionName + " faction's supporter " + result);
            faction.setSupporter(result);
        }
    }

    public void addFaction(String name) {
        factions.add(name);
    }
}
