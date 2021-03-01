package com.presidente.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    /**
     * Adds the amount of food to the current stock and removes from the treasury the necessary amount of money if there is enough money to pay
     *
     * @param amount The amount of food to buy
     * @param year   The year on which the food is created
     */
    public void buyFood(int amount, int year) {
        if (purchasableMaximumFoodAmount() < amount) return;
        addFood(amount, year + 4);
        treasury -= amount * FOOD_UNIT_COST;
    }

    /**
     * Adds a certain amount of food to the given expiration year, or creates a new instance of Food if there is no food at the given expiration year
     *
     * @param amount         The amount of food to be added
     * @param expirationYear The year at which the added food will expire
     */
    public void addFood(int amount, int expirationYear) {
        if (foodList.containsKey(expirationYear)) {
            foodList.get(expirationYear).addFood(amount);
        } else {
            foodList.put(expirationYear, new Food(amount));
        }
    }

    /**
     * Removes food from each instance of Food
     *
     * @param amount The amount of food to be removed
     * @return The amount of food that has not been removed
     */
    public int removeFood(int amount) {
        for (Map.Entry<Integer, Food> entry : foodList.entrySet()) {
            if (amount == 0) {
                break;
            }
            amount -= entry.getValue().removeFood(amount);
        }
        return amount;
    }

    /**
     * Calculate the amount of money produced by the industry
     *
     * @param industry The current percentage of industry on the island
     */
    public void addIndustryPayoff(int industry) {
        treasury += industry * 10;
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
        int rest = removeFood(tooConsume);
        return getFoodQuantity() - rest;
    }

    /**
     * Removes an instance of Food if it contains no food or the expiration year is passed and so the total amount of food is decreased
     *
     * @param year The current year of the game
     */
    public void removeExpiredFood(int year) {
        List<Integer> toBeRemoved = new ArrayList<>();
        for (Map.Entry<Integer, Food> entry : foodList.entrySet()) {
            if (entry.getKey() <= year || entry.getValue().getAmount() == 0) {
                toBeRemoved.add(entry.getKey());
            }
        }
        toBeRemoved.forEach(foodList::remove);
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

    /**
     * @return The total amount of food available
     */
    public int getFoodQuantity() {
        return foodList
                .values()
                .stream()
                .mapToInt(Food::getAmount)
                .sum();
    }

    public HashMap<Integer, Food> getFoodList() {
        return foodList;
    }

    @Override
    public String toString() {
        return String.format("%-21s%s", "Argent: " + treasury, "Nourriture: " + foodList);
    }
}
