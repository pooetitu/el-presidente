package game.event.effect;

import game.Island;
import game.event.effect.calculation.Calculation;

public abstract class EventEffect {
    private final double amount;
    private final Calculation calculationMethod;

    public EventEffect(double amount, Calculation calculationMethod) {
        this.amount = amount;
        this.calculationMethod = calculationMethod;
    }

    public abstract void applyEffect(Island island);

    public abstract String display(double effectRatio);

    protected int calculateNewValue(int value, double effectRatio) {
        return calculationMethod.calculateNewValue(calculateAmountWithEffectRatio(effectRatio), value);
    }

    protected double calculateAmountWithEffectRatio(double effectRatio) {
        if (amount > 0) {
            return amount / effectRatio;
        } else {
            return amount * effectRatio;
        }
    }

    public double getAmount() {
        return amount;
    }
}
