package game.event.effect;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import game.Island;
import game.event.effect.calculation.Calculation;

@XStreamAlias("effect-money")
public class EventEffectMoney extends EventEffect {

    public EventEffectMoney(String description, double amount, Calculation calculationMethod) {
        super(description, amount, calculationMethod);
    }

    @Override
    public void applyEffect(Island island) {
        int result = calculateNewValue(island.getRessources().getTreasury(), island.getDifficulty().getEffectRatio());
        island.getRessources().setTreasury(result);
    }
}
