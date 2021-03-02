package com.presidente.game.event;

import com.presidente.builders.IslandBuilder;
import com.presidente.game.GameDifficulty;
import com.presidente.game.Island;
import com.presidente.game.Population;
import com.presidente.game.event.effect.EventEffectFactionSatisfaction;
import com.presidente.game.event.effect.calculation.CalculationFixed;
import junit.framework.TestCase;

public class EventChoiceTest extends TestCase {
    private Island islandNormal;
    private EventChoice choiceSingleEffect;
    private EventChoice choiceMultipleEffect;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        islandNormal = new IslandBuilder().setPopulation(new Population()).setDifficulty(GameDifficulty.NORMAL).build();

        EventEffectFactionSatisfaction[] eventEffectsMultipleChoice = new EventEffectFactionSatisfaction[2];
        eventEffectsMultipleChoice[0] = new EventEffectFactionSatisfaction(15, false, new CalculationFixed());
        eventEffectsMultipleChoice[0].addFaction("religieux");
        eventEffectsMultipleChoice[1] = new EventEffectFactionSatisfaction(-15, false, new CalculationFixed());
        eventEffectsMultipleChoice[1].addFaction("libéraux");

        choiceMultipleEffect = new EventChoice("Décliner poliment au motif que vous n’avez pas les infrastructures pour eux", eventEffectsMultipleChoice, null);

        EventEffectFactionSatisfaction[] eventEffectsOneChoice = new EventEffectFactionSatisfaction[1];
        eventEffectsOneChoice[0] = new EventEffectFactionSatisfaction(15, false, new CalculationFixed());
        eventEffectsOneChoice[0].addFaction("religieux");
        choiceSingleEffect = new EventChoice("Décliner poliment au motif que vous n’avez pas les infrastructures pour eux", eventEffectsOneChoice, null);
    }

    public void testApplyOneEffect() {
        choiceSingleEffect.applyEffects(islandNormal);
        assertEquals(65, islandNormal.getPopulation().getFactionByName("religieux").getSatisfaction());
    }

    public void testApplyMultipleEffect() {
        choiceMultipleEffect.applyEffects(islandNormal);
        assertEquals(65, islandNormal.getPopulation().getFactionByName("religieux").getSatisfaction());
        assertEquals(35, islandNormal.getPopulation().getFactionByName("libéraux").getSatisfaction());
    }
}
