package game;

public class Faction {
    private Population population;
    private final String name;
    private final int satisfaction;
    private final int supporter;

    public Faction(String name, int satisfaction, int supporter) {
        this.name = name;
        this.satisfaction = satisfaction;
        this.supporter = supporter;
    }

    public void takeCorruption(Island island) {

    }

    public String getName() {
        return name;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public int getSupporter() {
        return supporter;
    }

    public Population getPopulation() {
        return population;
    }
}
