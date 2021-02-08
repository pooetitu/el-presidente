package game.event.effect.calculation;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("calculation-fixed")
public class CalculationFixed implements Calculation {
    @Override
    public int calculateNewValue(double factor, int currentValue) {
        return (int) (currentValue + factor);
    }
}
