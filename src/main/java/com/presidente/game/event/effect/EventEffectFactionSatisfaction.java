package com.presidente.game.event.effect;

import com.presidente.game.Faction;
import com.presidente.game.GameDifficulty;
import com.presidente.game.Island;
import com.presidente.game.event.effect.calculation.Calculation;

import java.util.ArrayList;
import java.util.Iterator;

public class EventEffectFactionSatisfaction extends EventEffect {
    /**
     * The list of faction names on which the effect will be applied
     */
    private final ArrayList<String> factions;
    /**
     * Whether the effect will be applied on every factions or not
     */
    private final boolean applyToAll;

    public EventEffectFactionSatisfaction(double amount, boolean applyToAll, Calculation calculationMethod) {
        super(amount, calculationMethod);
        this.factions = new ArrayList<>();
        this.applyToAll = applyToAll;
    }

    /**
     * Applies an effect on the given Island's faction(s), applies the effect to every faction if the applyToAll attribute is true or
     * applies to each faction listed in the factions list
     *
     * @param island The Island on which the effect will be applied
     */
    @Override
    public void applyEffect(Island island) {
        if (applyToAll) {
            applyToAll(island);
            return;
        }
        for (String factionName : factions) {
            Faction faction = island.getPopulation().getFactionByName(factionName);
            applyChangement(faction, island.getDifficulty());
        }
    }

    @Override
    public String display(double effectRatio) {
        if (applyToAll) {
            return String.format("%+d%s", (int) calculateAmountWithEffectRatio(effectRatio), " % de satisfaction pour toutes les factions");
        }
        StringBuilder display = new StringBuilder(String.format("%+d%s", (int) calculateAmountWithEffectRatio(effectRatio), " % de satisfaction pour les "));
        Iterator<String> factionIterator = factions.iterator();
        while (factionIterator.hasNext()) {
            display.append(factionIterator.next());
            if (factionIterator.hasNext()) {
                display.append(" ");
            }
        }
        return display.toString();
    }

    /**
     * Applies the effect on every faction of the given Island
     *
     * @param island The Island on which the effect will be applied
     */
    private void applyToAll(Island island) {
        for (Faction faction : island.getPopulation().getFactions()) {
            applyChangement(faction, island.getDifficulty());
        }
    }

    /**
     * Modifies the satisfaction value of the given faction
     *
     * @param faction    The faction to be affected
     * @param difficulty The current game's difficulty
     */
    private void applyChangement(Faction faction, GameDifficulty difficulty) {
        int result = calculateNewValue(faction.getSatisfaction(), difficulty.getEffectRatio());
        faction.setSatisfaction(result);
    }

    /**
     * Adds a faction to be affected by the effect to the factions list
     *
     * @param name The name of a faction to be affected by the effect
     */
    public void addFaction(String name) {
        factions.add(name);
    }
}
