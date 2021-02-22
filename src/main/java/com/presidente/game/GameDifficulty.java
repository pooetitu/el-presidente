package com.presidente.game;

/**
 * The difficulty of the game
 */
public enum GameDifficulty {
    EASY("Facile", 0.5, 10), NORMAL("Normal", 1, 25), HARD("Difficile", 2, 50);
    /**
     * The name to be displayed
     */
    private final String name;
    /**
     * A ratio applied to the event effects
     */
    private final double effectRatio;
    /**
     * A threshold on which the game will be over if the global satisfaction goes below
     */
    private final int satisfactionThreshold;

    GameDifficulty(String name, double effectRatio, int satisfactionThreshold) {
        this.name = name;
        this.effectRatio = effectRatio;
        this.satisfactionThreshold = satisfactionThreshold;
    }

    /**
     * @return The effect ratio
     */
    public double getEffectRatio() {
        return effectRatio;
    }

    /**
     * @return The satisfaction threshold
     */
    public int getSatisfactionThreshold() {
        return satisfactionThreshold;
    }

    @Override
    public String toString() {
        return name;
    }
}
