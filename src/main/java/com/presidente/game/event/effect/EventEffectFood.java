package com.presidente.game.event.effect;

import com.presidente.game.Island;
import com.presidente.game.event.effect.calculation.Calculation;

public class EventEffectFood extends EventEffect {

    public EventEffectFood(double amount, Calculation calculationMethod) {
        super(amount, calculationMethod);
    }

    /**
     * Modifies the food value of the given island
     *
     * @param island The Island on which the effect will be applied
     */
    @Override
    public void applyEffect(Island island) {
        int result = calculateNewValue(island.getResource().getFoodQuantity(), island.getDifficulty().getEffectRatio()) - island.getResource().getFoodQuantity();
        if (result > 0) {
            island.getResource().addFood(result, island.getTurn() / 4);
        } else {
            island.getResource().removeFood(-result);
        }
    }

    @Override
    public String display(double effectRatio) {
        return String.format("%+d%s", (int) calculateAmountWithEffectRatio(effectRatio), "% nourriture");
    }
}
