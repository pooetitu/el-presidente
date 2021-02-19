package com.presidente.game.event.effect.calculation;

public class CalculationPercentage implements Calculation {
    @Override
    public int calculateNewValue(double factor, int currentValue) {
        return (int) (currentValue + currentValue * (factor / 100));
    }
}
