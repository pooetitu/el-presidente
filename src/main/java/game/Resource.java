package game;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ressource")
public class Resource {
    private static final int FOOD_UNIT_COST = 8;
    private int treasury;
    private int food;

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
    }

    @Override
    public String toString() {
        return String.format("%-21s%s", "Argent: " + treasury, "Nourriture: " + food);
    }
}
