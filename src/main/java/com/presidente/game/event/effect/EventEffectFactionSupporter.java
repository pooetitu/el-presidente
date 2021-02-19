package com.presidente.game.event.effect;

import com.presidente.game.Faction;
import com.presidente.game.GameDifficulty;
import com.presidente.game.Island;
import com.presidente.game.event.effect.calculation.Calculation;

import java.util.ArrayList;
import java.util.Iterator;

public class EventEffectFactionSupporter extends EventEffect {
    private final ArrayList<String> factions;
    private final boolean applyToAll;

    public EventEffectFactionSupporter(double amount, boolean applyToAll, Calculation calculationMethod) {
        super(amount, calculationMethod);
        this.factions = new ArrayList<>();
        this.applyToAll = applyToAll;
    }


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
            return String.format("%+d%s", (int) calculateAmountWithEffectRatio(effectRatio), " % de supporter pour toutes les factions");
        }
        StringBuilder display = new StringBuilder(String.format("%+d%s", (int) calculateAmountWithEffectRatio(effectRatio), " % de supporter pour les "));
        Iterator<String> factionIterator = factions.iterator();
        while (factionIterator.hasNext()) {
            display.append(factionIterator.next());
            if (factionIterator.hasNext()) display.append(" ");
        }
        return display.toString();
    }

    private void applyToAll(Island island) {
        for (Faction faction : island.getPopulation().getFactions()) {
            applyChangement(faction, island.getDifficulty());
        }
    }

    private void applyChangement(Faction faction, GameDifficulty difficulty) {
        int result = calculateNewValue(faction.getSupporter(), difficulty.getEffectRatio());
        faction.setSupporter(result);
    }

    public void addFaction(String name) {
        factions.add(name);
    }
}
