package com.presidente.game;

public class Resource {
    private static final int FOOD_UNIT_COST = 8;
    private static final int FOOD_CONSUMPTION_PER_PERSON = 4;
    private int treasury;
    private int food;

    public Resource() {
        super();
    }

    public Resource(int treasury, int food) {
        this.treasury = treasury;
        this.food = food;
    }

    /**
     * Adds the amount of food to the current stock and removes from the treasury the necessary amount of money if there is enough money to pay
     *
     * @param amount the amount of food to buy
     */
    public void buyFood(int amount) {
        if (purchasableMaximumFoodAmount() < amount) return;
        food += amount;
        treasury -= amount * FOOD_UNIT_COST;
    }

    /**
     * Calculate the amount of money produced by the industry
     *
     * @param industrie The current percentage of industry on the island
     */
    public void addIndustriePayoff(int industrie) {
        treasury += industrie * 10;
    }

    /**
     * Calculate the amount of food produced by the agriculture
     *
     * @param agriculture The current percentage of agriculture on the island
     */
    public void addAgriculturePayoff(int agriculture) {
        food += agriculture * 40;
    }

    /**
     * Consumes the food for the amount of person on the island
     *
     * @param population The current amount of people on the island
     * @return The rest of food after the population ate
     */
    public int consumeFood(int population) {
        int tooConsume = population * FOOD_CONSUMPTION_PER_PERSON;
        int rest = food - tooConsume;
        food -= tooConsume;
        return rest;
    }

    /**
     * @return The amount of food that can be bought
     */
    public int purchasableMaximumFoodAmount() {
        return treasury / FOOD_UNIT_COST;
    }

    public int getTreasury() {
        return treasury;
    }

    public void setTreasury(int treasury) {
        this.treasury = treasury;
    }

    public int getFood() {
        return food;
    }

    /**
     * Sets a new amount of food can't go below 0
     *
     * @param food The amount of food to be set
     */
    public void setFood(int food) {
        this.food = food;
        if (this.food < 0) {
            this.food = 0;
        }
    }

    @Override
    public String toString() {
        return String.format("%-21s%s", "Argent: " + treasury, "Nourriture: " + food);
    }
}
