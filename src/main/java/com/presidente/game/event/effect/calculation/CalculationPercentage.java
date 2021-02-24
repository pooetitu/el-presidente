package com.presidente.game.event.effect.calculation;

/**
 * Calculates the new value as a percentage
 */
public class CalculationPercentage implements Calculation {
    /**
     * Adds the factor as a percentage of the old value to the old value
     *
     * @param currentValue The current value to be updated
     * @param factor       The amount to be applied one the old value
     * @return The new value to be applied
     */
    @Override
    public int calculateNewValue(int currentValue, double factor) {
        return (int) (currentValue + currentValue * (factor / 100));
    }
}
