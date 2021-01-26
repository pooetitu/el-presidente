package game.event.effect.calculation;

public interface Calculation {
    int calculateNewValue(double factor, int currentValue, double effectRatio);
}
