package game.event.effect.calculation;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("calculation-percentage")
public class CalculationPercentage implements Calculation {
    @Override
    public int calculateNewValue(double factor, int currentValue) {
        System.out.println(factor);
        return (int) (currentValue + currentValue * (factor / 100));
    }
}
