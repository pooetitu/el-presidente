package game.event.effect;

import game.Island;
import game.event.effect.calculation.Calculation;

public abstract class EventEffect {
    // TODO description can be generated dynamically for each EventEffect type
    private final String description;
    private final double amount;
    private final Calculation calculationMethod;

    public EventEffect(String description, double amount, Calculation calculationMethod) {
        this.description = description;
        this.amount = amount;
        this.calculationMethod = calculationMethod;
    }

    public abstract void applyEffect(Island island);

    public String display() {
        return description;
    }

    protected int calculateNewValue(int value, double effectRatio) {
        return calculationMethod.calculateNewValue(amount, value, effectRatio);
    }

    public double getAmount() {
        return amount;
    }
}
