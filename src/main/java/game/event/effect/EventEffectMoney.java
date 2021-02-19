package game.event.effect;

import game.Island;
import game.event.effect.calculation.Calculation;

public class EventEffectMoney extends EventEffect {

    public EventEffectMoney(double amount, Calculation calculationMethod) {
        super(amount, calculationMethod);
    }

    @Override
    public void applyEffect(Island island) {
        int result = calculateNewValue(island.getResource().getTreasury(), island.getDifficulty().getEffectRatio());
        island.getResource().setTreasury(result);
    }

    @Override
    public String display(double effectRatio) {
        return String.format("%+d%s", (int) calculateAmountWithEffectRatio(effectRatio), "$");
    }
}
