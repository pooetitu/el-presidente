package com.presidente.game;

/**
 * A faction which will have influence on the Island
 */
public class Faction {
    private static final int CORRUPTION_COST = 15;
    private String name;
    private int satisfaction;
    private int supporter;

    public Faction() {
        super();
    }

    public Faction(String name, int satisfaction, int supporter) {
        this.name = name;
        this.satisfaction = satisfaction;
        this.supporter = supporter;
    }

    /**
     * The new amount of satisfaction is calculated
     *
     * @param amount The amount of time the user wants to corrupt the faction
     */
    public void corrupt(int amount) {
        setSatisfaction(satisfaction + 10 * amount);
    }

    /**
     * Removes the given amount of person belonging to the faction,
     * The satisfaction is lowered by 2 times the number of person removed
     *
     * @param count the number of person to be removed
     */
    public void removePeople(int count) {
        setSupporter(supporter - count);
    }

    /**
     * Adds the given amount of person to the faction
     *
     * @param count The number of person to be added
     */
    public void addPeople(int count) {
        supporter += count;
    }

    /**
     * @return The cost of the corruption
     */
    public int getCorruptionCost() {
        return CORRUPTION_COST * supporter;
    }

    public String getName() {
        return name;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    /**
     * The amount of satisfaction is capped between 0 and 100
     *
     * @param satisfaction The new value of satisfaction to be set
     */
    public void setSatisfaction(int satisfaction) {
        if (this.satisfaction == 0) return;
        this.satisfaction = satisfaction;
        if (this.satisfaction < 0) this.satisfaction = 0;
        if (this.satisfaction > 100) this.satisfaction = 100;
    }

    public int getSupporter() {
        return supporter;
    }

    /**
     * The amount of supporter never goes below 0
     *
     * @param supporter The new value of supporter to be set
     */
    public void setSupporter(int supporter) {
        this.supporter = supporter;
        if (this.supporter < 0) this.supporter = 0;
    }

    @Override
    public String toString() {
        return String.format("%-18s%-20s%s", name + ":",
                "\tsatisfaction:" + satisfaction,
                "\tsupporter:" + supporter);
    }
}
