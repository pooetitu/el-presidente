package com.presidente.game;


public enum GameDifficulty {
    EASY("Facile", 0.5, 10), NORMAL("Normal", 1, 25), HARD("Difficile", 2, 50);
    private final String name;
    private final double effectRatio;
    private final int satisfactionThreshold;

    GameDifficulty(String name, double effectRatio, int satisfactionThreshold) {
        this.name = name;
        this.effectRatio = effectRatio;
        this.satisfactionThreshold = satisfactionThreshold;
    }

    public double getEffectRatio() {
        return effectRatio;
    }

    public int getSatisfactionThreshold() {
        return satisfactionThreshold;
    }

    @Override
    public String toString() {
        return name;
    }
}
