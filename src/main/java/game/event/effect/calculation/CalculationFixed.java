package game.event.effect.calculation;

public class CalculationFixed implements Calculation {
    @Override
    public int calculateNewValue(double factor, int currentValue, double effectRatio) {
        if (factor > 0) {
            return (int) (currentValue + (factor / effectRatio));
        } else {
            return (int) (currentValue + (factor * effectRatio));
        }
    }
}
