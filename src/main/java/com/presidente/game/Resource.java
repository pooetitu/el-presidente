package com.presidente.game;

import java.util.HashMap;
import java.util.Map;

public class Resource {
    private static final int FOOD_UNIT_COST = 8;
    private static final int FOOD_CONSUMPTION_PER_PERSON = 4;
    /**
     * The key is the year on which the food will expire
     */
    private final HashMap<Integer, Food> foodList;
    /**
     * The amount of money available
     */
    private int treasury;

    public Resource() {
        super();
        this.foodList = new HashMap<>();
    }

    public Resource(int treasury) {
        this.treasury = treasury;
        this.foodList = new HashMap<>();
    }

    /**
     * Adds the amount of food to the current stock and removes from the treasury the necessary amount of money if there is enough money to pay
     *
     * @param amount The amount of food to buy
     * @param year   The year on which the food is created
     */
    public void buyFood(int amount, int year) {
        if (purchasableMaximumFoodAmount() < amount) return;
        System.out.println(amount);
        addFood(amount, year + 4);
        treasury -= amount * FOOD_UNIT_COST;
    }

    public void addFood(int amount, int expirationYear) {
        if (foodList.containsKey(expirationYear)) {
            foodList.get(expirationYear).addFood(amount);
        } else {
            foodList.put(expirationYear, new Food(amount));
        }
    }

    public void removeFood(int amount) {
        for (Map.Entry<Integer, Food> entry : foodList.entrySet()) {
            if (amount == 0) {
                break;
            }
            amount -= entry.getValue().removeFood(amount);
            if (entry.getValue().getAmount() == 0) {
                foodList.remove(entry.getKey());
            }
        }
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
     * @param year        The year on which the food is created
     */
    public void addAgriculturePayoff(int agriculture, int year) {
        addFood(agriculture * 40, year + 4);
    }

    /**
     * Consumes the food for the amount of person on the island
     *
     * @param population The current amount of people on the island
     * @return The rest of food after the population ate
     */
    public int consumeFood(int population) {
        int tooConsume = population * FOOD_CONSUMPTION_PER_PERSON;
        //int rest = food - tooConsume;
        //food -= tooConsume;
        return 0;//rest;
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

    @Override
    public String toString() {
        return String.format("%-21s%s", "Argent: " + treasury, "Nourriture: " + foodList);
    }

    public int getFoodQuantity() {
        return foodList
                .values()
                .stream()
                .mapToInt(Food::getAmount)
                .sum();
    }
}
