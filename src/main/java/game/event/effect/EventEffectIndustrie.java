package game.event.effect;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import game.Island;
import game.event.effect.calculation.Calculation;

@XStreamAlias("effect-industrie")
public class EventEffectIndustrie extends EventEffect {

    public EventEffectIndustrie(double amount, Calculation calculationMethod) {
        super(amount, calculationMethod);
    }

    @Override
    public void applyEffect(Island island) {
        int result = calculateNewValue(island.getIndustrie(), island.getDifficulty().getEffectRatio());
        island.setIndustrie(result);
    }

    @Override
    public String display(double effectRatio) {
        return String.format("%+d%s", (int) calculateAmountWithEffectRatio(effectRatio), "% industrie");
    }
}
