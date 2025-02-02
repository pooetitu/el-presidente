package com.presidente.game.event.effect;

import com.presidente.game.Island;
import com.presidente.game.event.effect.calculation.Calculation;


public class EventEffectAgriculture extends EventEffect {

    public EventEffectAgriculture(double amount, Calculation calculationMethod) {
        super(amount, calculationMethod);
    }

    /**
     * Modifies the agriculture value of the given island
     *
     * @param island The Island on which the effect will be applied
     */
    @Override
    public void applyEffect(Island island) {
        int result = this.calculateNewValue(island.getAgriculture(), island.getDifficulty().getEffectRatio());
        island.setAgriculture(result);
    }

    @Override
    public String display(double effectRatio) {
        return String.format("%+d%s", (int) calculateAmountWithEffectRatio(effectRatio), "% agriculture");
    }
}
