package game;

public class Faction {
    private static final int CORRUPTION_COST = 15;
    private final String name;
    private Population population;
    private int satisfaction;
    private int supporter;

    public Faction(String name, int satisfaction, int supporter) {
        this.name = name;
        this.satisfaction = satisfaction;
        this.supporter = supporter;
    }

    public int corrupt() {
        int corruptionCost = CORRUPTION_COST * supporter;
        setSatisfaction(getSatisfaction() + 10);
        return corruptionCost;
    }

    public String getName() {
        return name;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(int satisfaction) {
        if (this.satisfaction == 0) return;
        this.satisfaction = satisfaction;
        if (this.satisfaction < 0) this.satisfaction = 0;
        if (this.satisfaction > 100) this.satisfaction = 100;
    }

    public int getSupporter() {
        return supporter;
    }

    public void setSupporter(int supporter) {
        this.supporter = supporter;
    }

    public Population getPopulation() {
        return population;
    }

    public int getCorruptionCost() {
        return CORRUPTION_COST * supporter;
    }
}
