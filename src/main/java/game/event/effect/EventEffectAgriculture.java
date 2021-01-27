package game.event.effect;

import game.Island;
import game.event.effect.calculation.Calculation;

public class EventEffectAgriculture extends EventEffect {

    public EventEffectAgriculture(String description, double amount, Calculation calculationMethod) {
        super(description, amount, calculationMethod);
    }

    @Override
    public void applyEffect(Island island) {
        int result = this.calculateNewValue(island.getAgriculture(), island.getDifficulty().getEffectRatio());
        System.out.println("Setting new agriculture value " + result);
        island.setAgriculture(result);
    }
}
