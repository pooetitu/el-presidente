package com.presidente.game;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ressource")
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

    public void buyFood(int amount) {
        if (purchasableMaximumFoodAmount() < amount) return;
        food += amount;
        treasury -= amount * FOOD_UNIT_COST;
    }

    public void addIndustriePayoff(int industrie) {
        treasury += industrie * 10;
    }

    public void addAgriculturePayoff(int agriculture) {
        food += agriculture * 40;
    }

    public int consumeFood(int population) {
        int tooConsume = population * FOOD_CONSUMPTION_PER_PERSON;
        int rest = food - tooConsume;
        food -= tooConsume;
        return rest;
    }

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
