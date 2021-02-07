package game.event.effect;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import game.Island;
import game.event.effect.calculation.Calculation;

@XStreamAlias("effect-industrie")
public class EventEffectIndustrie extends EventEffect {

    public EventEffectIndustrie(String description, double amount, Calculation calculationMethod) {
        super(description, amount, calculationMethod);
    }

    @Override
    public void applyEffect(Island island) {
        int result = calculateNewValue(island.getIndustrie(), island.getDifficulty().getEffectRatio());
        System.out.println("Setting new industrie value " + result);
        island.setIndustrie(result);
    }
}
