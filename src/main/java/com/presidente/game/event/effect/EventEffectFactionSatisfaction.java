package com.presidente.game.event.effect;

import com.presidente.game.Faction;
import com.presidente.game.GameDifficulty;
import com.presidente.game.Island;
import com.presidente.game.event.effect.calculation.Calculation;

public class EventEffectFactionSatisfaction extends EventEffectFaction {
    public EventEffectFactionSatisfaction(double amount, boolean applyToAll, Calculation calculationMethod) {
        super(amount, applyToAll, calculationMethod, "satisfaction");
    }

    protected void applyToAll(Island island) {
        for (Faction faction : island.getPopulation().getFactions()) {
            applyChangement(faction, island.getDifficulty());
        }
    }

    protected void applyChangement(Faction faction, GameDifficulty difficulty) {
        int result = calculateNewValue(faction.getSatisfaction(), difficulty.getEffectRatio());
        faction.setSatisfaction(result);
    }
}
