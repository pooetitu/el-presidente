package game;

public class Faction {
    private Population population;
    private final String name;
    private int satisfaction;
    private int supporter;

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

    public void setSatisfaction(int satisfaction) {
        if (this.satisfaction == 0) return;
        this.satisfaction = satisfaction;
        if (this.satisfaction < 0) this.satisfaction = 0;
        if (this.satisfaction > 100) this.satisfaction = 100;
    }

    public void setSupporter(int supporter) {
        this.supporter = supporter;
    }
}
