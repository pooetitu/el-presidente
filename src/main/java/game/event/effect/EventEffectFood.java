package game.event.effect;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import game.Island;
import game.event.effect.calculation.Calculation;

@XStreamAlias("effect-food")
public class EventEffectFood extends EventEffect {

    public EventEffectFood(String description, double amount, Calculation calculationMethod) {
        super(description, amount, calculationMethod);
    }

    @Override
    public void applyEffect(Island island) {
        int result = calculateNewValue(island.getRessources().getFood(), island.getDifficulty().getEffectRatio());
        System.out.println("Setting new food value " + result);
        island.getRessources().setFood(result);
        System.out.println(island.getRessources().getFood());
    }
}
