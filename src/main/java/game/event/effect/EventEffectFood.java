package game.event.effect;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import game.Island;
import game.event.effect.calculation.Calculation;

@XStreamAlias("effect-food")
public class EventEffectFood extends EventEffect {

    public EventEffectFood(double amount, Calculation calculationMethod) {
        super(amount, calculationMethod);
    }

    @Override
    public void applyEffect(Island island) {
        int result = calculateNewValue(island.getResource().getFood(), island.getDifficulty().getEffectRatio());
        island.getResource().setFood(result);
        System.out.println(island.getResource().getFood());
    }

    @Override
    public String display(double effectRatio) {
        return String.format("%+d%s", (int) calculateAmountWithEffectRatio(effectRatio), "% nourriture");
    }
}
