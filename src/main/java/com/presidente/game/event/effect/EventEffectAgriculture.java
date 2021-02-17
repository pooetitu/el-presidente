package com.presidente.game.event.effect;

import com.presidente.game.Island;
import com.presidente.game.event.effect.calculation.Calculation;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("effect-agriculture")
public class EventEffectAgriculture extends EventEffect {

    public EventEffectAgriculture() {
        super();
    }

    public EventEffectAgriculture(double amount, Calculation calculationMethod) {
        super(amount, calculationMethod);
    }

    @Override
    public void applyEffect(Island island) {
        int result = this.calculateNewValue(island.getAgriculture(), island.getDifficulty().getEffectRatio());
        island.setAgriculture(result);
    }

    @Override
    public String display(double effectRatio) {
        return String.format("%+d%s", (int) calculateAmountWithEffectRatio(effectRatio), "% agriculture");
    }
}
