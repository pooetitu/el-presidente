package game;

public class Ressource {
    private static final int FOOD_UNIT_COST = 8;
    private int treasury;
    private int food;

    public Ressource(int treasury, int food) {
        this.treasury = treasury;
        this.food = food;
    }

    public void buyFood(int amount) {
        if (treasury <= 0) return;
        food += amount;
        treasury -= amount * FOOD_UNIT_COST;
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
}
