package game.event.effect;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import game.Island;
import game.event.effect.calculation.Calculation;

@XStreamAlias("effect-agriculture")
public class EventEffectAgriculture extends EventEffect {

    public EventEffectAgriculture(String description, double amount, Calculation calculationMethod) {
        super(description, amount, calculationMethod);
    }

    @Override
    public void applyEffect(Island island) {
        int result = this.calculateNewValue(island.getAgriculture(), island.getDifficulty().getEffectRatio());
        island.setAgriculture(result);
    }
}
