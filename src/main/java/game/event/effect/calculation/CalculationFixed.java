package game.event.effect.calculation;


public class CalculationFixed implements Calculation {
    @Override
    public int calculateNewValue(double factor, int currentValue) {
        return (int) (currentValue + factor);
    }
}
