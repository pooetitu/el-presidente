package com.presidente.game.event.effect;

import com.presidente.game.Faction;
import com.presidente.game.GameDifficulty;
import com.presidente.game.event.effect.calculation.Calculation;

public class EventEffectFactionSatisfaction extends EventEffectFaction {
    public EventEffectFactionSatisfaction(double amount, boolean applyToAll, Calculation calculationMethod) {
        super(amount, applyToAll, calculationMethod, "satisfaction");
    }

    /**
     * Modifies the satisfaction value of the given faction
     *
     * @param faction    The faction to be affected
     * @param difficulty The current game's difficulty
     */
    protected void applyChangement(Faction faction, GameDifficulty difficulty) {
        int result = calculateNewValue(faction.getSatisfaction(), difficulty.getEffectRatio());
        faction.setSatisfaction(result);
    }
}
