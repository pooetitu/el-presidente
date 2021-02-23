package com.presidente.game.event.effect;

import com.presidente.game.Island;
import com.presidente.game.event.effect.calculation.Calculation;

public class EventEffectFood extends EventEffect {

    public EventEffectFood(double amount, Calculation calculationMethod) {
        super(amount, calculationMethod);
    }

    @Override
    public void applyEffect(Island island) {
        int result = calculateNewValue(island.getResource().getFood(), island.getDifficulty().getEffectRatio());
        island.getResource().setFood(result);
    }

    @Override
    public String display(double effectRatio) {
        return String.format("%+d%s", (int) calculateAmountWithEffectRatio(effectRatio), "% nourriture");
    }
}
