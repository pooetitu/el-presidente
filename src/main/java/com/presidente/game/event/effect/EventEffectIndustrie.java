package com.presidente.game.event.effect;

import com.presidente.game.Island;
import com.presidente.game.event.effect.calculation.Calculation;

public class EventEffectIndustrie extends EventEffect {

    public EventEffectIndustrie(double amount, Calculation calculationMethod) {
        super(amount, calculationMethod);
    }

    /**
     * Modifies the industry value of the given island
     *
     * @param island The Island on which the effect will be applied
     */
    @Override
    public void applyEffect(Island island) {
        int result = calculateNewValue(island.getIndustrie(), island.getDifficulty().getEffectRatio());
        island.setIndustrie(result);
    }

    @Override
    public String display(double effectRatio) {
        return String.format("%+d%s", (int) calculateAmountWithEffectRatio(effectRatio), "% industrie");
    }
}
