package com.presidente.game.event.effect.calculation;

public interface Calculation {
    /**
     * @param currentValue The current value to be updated
     * @param factor       The amount to be applied one the old value
     * @return The new value to be applied
     */
    int calculateNewValue(int currentValue, double factor);
}
