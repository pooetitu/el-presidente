package game.event.effect;

import game.Island;
import game.event.effect.calculation.Calculation;

public class EventEffectMoney extends EventEffect {

    public EventEffectMoney(String description, double amount, Calculation calculationMethod) {
        super(description, amount, calculationMethod);
    }

    @Override
    public void applyEffect(Island island) {
        int result = calculateNewValue(island.getRessources().getTreasury(), island.getDifficulty().getEffectRatio());
        System.out.println("Setting new treasury value " + result);
        island.getRessources().setTreasury(result);
    }
}
