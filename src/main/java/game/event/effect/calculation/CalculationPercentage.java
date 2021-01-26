package game.event.effect.calculation;

public class CalculationPercentage implements Calculation {
    @Override
    public int calculateNewValue(double factor, int currentValue, double effectRatio) {
        if (factor > 0) {
            return (int) (currentValue + currentValue * (factor / effectRatio));
        } else {
            return (int) (currentValue + currentValue * (factor * effectRatio));
        }
    }
}
