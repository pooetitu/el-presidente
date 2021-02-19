package com.presidente.game;


public enum GameDifficulty {
    EASY(0.5, 10), NORMAL(1, 25), HARD(2, 50);
    private final double effectRatio;
    private final int satisfactionThreshold;

    GameDifficulty(double effectRatio, int satisfactionThreshold) {
        this.effectRatio = effectRatio;
        this.satisfactionThreshold = satisfactionThreshold;
    }

    public double getEffectRatio() {
        return effectRatio;
    }

    public int getSatisfactionThreshold() {
        return satisfactionThreshold;
    }
}
