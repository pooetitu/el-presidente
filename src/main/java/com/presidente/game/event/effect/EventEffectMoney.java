package com.presidente.game.event.effect;

import com.presidente.game.Island;
import com.presidente.game.event.effect.calculation.Calculation;

public class EventEffectMoney extends EventEffect {

    public EventEffectMoney(double amount, Calculation calculationMethod) {
        super(amount, calculationMethod);
    }

    /**
     * Modifies the money value of the given island
     *
     * @param island The Island on which the effect will be applied
     */
    @Override
    public void applyEffect(Island island) {
        int result = calculateNewValue(island.getResource().getTreasury(), island.getDifficulty().getEffectRatio());
        island.getResource().setTreasury(result);
    }

    @Override
    public String display(double effectRatio) {
        return String.format("%+d%s", (int) calculateAmountWithEffectRatio(effectRatio), "$");
    }
}
