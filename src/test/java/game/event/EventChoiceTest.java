package game.event;

import game.GameDifficulty;
import game.Island;
import game.Resource;
import game.event.effect.EventEffectFactionSatisfaction;
import game.event.effect.calculation.CalculationFixed;
import junit.framework.TestCase;

public class EventChoiceTest extends TestCase {
    private Island islandNormal;
    private EventChoice choiceOneEffect;
    private EventChoice choiceMultipleEffect;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        islandNormal = new Island(15, 15, GameDifficulty.NORMAL, new Resource(10, 10));

        EventEffectFactionSatisfaction[] eventEffectsMultipleChoice = new EventEffectFactionSatisfaction[2];
        eventEffectsMultipleChoice[0] = new EventEffectFactionSatisfaction(15, false, new CalculationFixed());
        eventEffectsMultipleChoice[0].addFaction("religieux");
        eventEffectsMultipleChoice[1] = new EventEffectFactionSatisfaction(-15, false, new CalculationFixed());
        eventEffectsMultipleChoice[1].addFaction("libéraux");

        choiceMultipleEffect = new EventChoice("Décliner poliment au motif que vous n’avez pas les infrastructures pour eux", eventEffectsMultipleChoice);

        EventEffectFactionSatisfaction[] eventEffectsOneChoice = new EventEffectFactionSatisfaction[1];
        eventEffectsOneChoice[0] = new EventEffectFactionSatisfaction(15, false, new CalculationFixed());
        eventEffectsOneChoice[0].addFaction("religieux");
        choiceOneEffect = new EventChoice("Décliner poliment au motif que vous n’avez pas les infrastructures pour eux", eventEffectsOneChoice);
    }

    public void testApplyOneEffect() {
        choiceOneEffect.applyEffects(islandNormal);
        assertEquals(65, islandNormal.getPopulation().getFactionByName("religieux").getSatisfaction());
    }

    public void testApplyMultipleEffect() {
        choiceMultipleEffect.applyEffects(islandNormal);
        assertEquals(65, islandNormal.getPopulation().getFactionByName("religieux").getSatisfaction());
        assertEquals(35, islandNormal.getPopulation().getFactionByName("libéraux").getSatisfaction());
    }
}
