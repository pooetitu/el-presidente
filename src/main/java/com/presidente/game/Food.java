package com.presidente.game;

public class Food {
    /**
     * The current amount of food for this instance
     */
    private int amount;

    public Food(int amount) {
        this.amount = amount;
    }

    /**
     * Sets a new amount of food can't go below 0
     *
     * @param amount The amount of food to be removed
     * @return The amount of food that has not been removed
     */
    public int removeFood(int amount) {
        this.amount -= amount;
        int removed = amount;
        if (this.amount <= 0) {
            removed = amount + this.amount;
            this.amount = 0;
        }
        return removed;
    }

    /**
     * Adds a certain amount of food
     *
     * @param amount The amount of food to be added
     */
    public void addFood(int amount) {
        this.amount += amount;
        if (this.amount < 0) {
            this.amount = 0;
        }
    }

    public int getAmount() {
        return amount;
    }
}
