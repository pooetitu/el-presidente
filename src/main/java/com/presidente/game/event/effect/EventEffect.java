package com.presidente.game.event.effect;

import com.presidente.game.Island;
import com.presidente.game.event.effect.calculation.Calculation;

public abstract class EventEffect {
    /**
     * The amount to be applied on an Island's attribute
     */
    private double amount;
    /**
     * The method of calculation used to apply the new amount of an Island's attribute
     */
    private Calculation calculationMethod;

    public EventEffect() {
        super();
    }

    public EventEffect(double amount, Calculation calculationMethod) {
        this.amount = amount;
        this.calculationMethod = calculationMethod;
    }

    /**
     * An attribute of the given Island will be updated
     *
     * @param island The Island on which the effect will be applied
     */
    public abstract void applyEffect(Island island);

    public abstract String display(double effectRatio);

    /**
     * Calculate the new value of an Island's attribute
     *
     * @param value       The old value of the attribute to be edited
     * @param effectRatio The difficulty's ratio to be applied on the amount
     * @return The new value of the attribute to be set
     */
    protected int calculateNewValue(int value, double effectRatio) {
        return calculationMethod.calculateNewValue(value, calculateAmountWithEffectRatio(effectRatio));
    }

    /**
     * The amount is divided by the effect ratio if greater than 0 or multiplied if lower,
     * this allows the game difficulty to be adjusted
     *
     * @param effectRatio The difficulty's ratio to be applied on the amount
     * @return The amount that will be applied on an Island's attribute
     */
    protected double calculateAmountWithEffectRatio(double effectRatio) {
        if (amount > 0) {
            return amount / effectRatio;
        } else {
            return amount * effectRatio;
        }
    }
}
