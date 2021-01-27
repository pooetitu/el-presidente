package game;

public class Ressource {
    private int treasury;
    private int food;

    public Ressource(int treasury, int food) {
        this.treasury = treasury;
        this.food = food;
    }

    public void buyFood(int amount) {

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
