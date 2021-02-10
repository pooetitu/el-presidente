package game;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("faction")
public class Faction {
    private static final int CORRUPTION_COST = 15;
    private final String name;
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
        if (this.supporter < 0) this.supporter = 0;
    }

    public int getCorruptionCost() {
        return CORRUPTION_COST * supporter;
    }

    @Override
    public String toString() {
        return String.format("%-18s%-20s%s", name + ":",
                "\tsatisfaction:" + satisfaction,
                "\tsupporter:" + supporter);
    }
}
