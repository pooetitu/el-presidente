package game.event.effect.calculation;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("calculation-percentage")
public class CalculationPercentage implements Calculation {
    @Override
    public int calculateNewValue(double factor, int currentValue, double effectRatio) {
        if (factor > 0) {
            return (int) (currentValue + currentValue * ((factor/100) / effectRatio));
        } else {
            return (int) (currentValue + currentValue * ((factor/100) * effectRatio));
        }
    }
}
