package com.presidente.game.event.effect.calculation;

/**
 * Calculates the new value as an addition
 */
public class CalculationFixed implements Calculation {
    /**
     * Adds the factor to the old value
     *
     * @param currentValue The current value to be updated
     * @param factor       The amount to be applied one the old value
     * @return The new value to be applied
     */
    @Override
    public int calculateNewValue(int currentValue, double factor) {
        return (int) (currentValue + factor);
    }
}
