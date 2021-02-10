package game;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("difficulty")
public enum GameDifficulty {
    EASY(0.5, 0), NORMAL(1, 10), HARD(2, 50);
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
