package game;

public enum GameDifficulty {
    EASY(0.5, 0), NORMAL(1, 10), HARD(2, 50);
    private double effectRatio;
    private int satisfactionThreshold;

    GameDifficulty(double effectRatio, int satisfactionThreshold) {
        this.effectRatio = effectRatio;
        this.satisfactionThreshold = satisfactionThreshold;
    }
}
