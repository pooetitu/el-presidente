package com.presidente.game.event.effect;

import com.presidente.game.Faction;
import com.presidente.game.GameDifficulty;
import com.presidente.game.Island;
import com.presidente.game.event.effect.calculation.Calculation;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class EventEffectFaction extends EventEffect {
    /**
     * Whether the effect will be applied on every factions or not
     */
    protected final boolean applyToAll;
    /**
     * The list of faction names on which the effect will be applied
     */
    protected final ArrayList<String> factions;

    protected final String affectedAttribute;

    protected EventEffectFaction(double amount, boolean applyToAll, Calculation calculationMethod, String affectedAttribute) {
        super(amount, calculationMethod);
        this.affectedAttribute = affectedAttribute;
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

    /**
     * Modifies the supporter value of the given faction
     *
     * @param faction    The faction to be affected
     * @param difficulty The current game's difficulty
     */
    protected abstract void applyChangement(Faction faction, GameDifficulty difficulty);

    /**
     * Applies the effect on every faction of the given Island
     *
     * @param island The Island on which the effect will be applied
     */
    protected abstract void applyToAll(Island island);

    @Override
    public String display(double effectRatio) {
        if (applyToAll) {
            return String.format("%+d%s", (int) calculateAmountWithEffectRatio(effectRatio), " % de " + affectedAttribute + " pour toutes les factions");
        }
        StringBuilder display = new StringBuilder(String.format("%+d%s", (int) calculateAmountWithEffectRatio(effectRatio), " % de " + affectedAttribute + " pour les "));
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
     * Adds a faction to be affected by the effect to the factions list
     *
     * @param name The name of a faction to be affected by the effect
     */
    public void addFaction(String name) {
        factions.add(name);
    }
}
