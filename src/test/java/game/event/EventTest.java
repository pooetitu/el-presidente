package game.event;

import game.GameDifficulty;
import game.Island;
import game.Resource;
import game.event.effect.EventEffectFactionSatisfaction;
import game.event.effect.calculation.CalculationFixed;
import junit.framework.TestCase;

public class EventTest extends TestCase {
    private Island islandNormal;
    private Event eventOneChoice;
    private Event eventMultipleChoice;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        islandNormal = new Island(15, 15, GameDifficulty.NORMAL, new Resource(10, 10));

        EventEffectFactionSatisfaction[] eventEffectsMultipleChoice = new EventEffectFactionSatisfaction[2];
        eventEffectsMultipleChoice[0] = new EventEffectFactionSatisfaction(15, false, new CalculationFixed());
        eventEffectsMultipleChoice[0].addFaction("religieux");
        eventEffectsMultipleChoice[1] = new EventEffectFactionSatisfaction(-15, false, new CalculationFixed());
        eventEffectsMultipleChoice[1].addFaction("libéraux");

        EventEffectFactionSatisfaction[] eventEffectsMultipleChoice2 = new EventEffectFactionSatisfaction[2];
        eventEffectsMultipleChoice2[0] = new EventEffectFactionSatisfaction(15, false, new CalculationFixed());
        eventEffectsMultipleChoice2[0].addFaction("capitalistes");
        eventEffectsMultipleChoice2[1] = new EventEffectFactionSatisfaction(-15, false, new CalculationFixed());
        eventEffectsMultipleChoice2[1].addFaction("loyalistes");

        EventChoice[] multipleChoices = new EventChoice[2];
        multipleChoices[0] = new EventChoice("Décliner poliment au motif que vous n’avez pas les infrastructures pour eux", eventEffectsMultipleChoice);
        multipleChoices[1] = new EventChoice("Décliner poliment au motif que vous n’avez pas les infrastructures pour eux", eventEffectsMultipleChoice2);
        eventMultipleChoice = new Event("L'Organisation des Caraïbes-Unies souhaite que vous accueillez des réfugiés climatiques suite aux récentes inondations dans la région", null, multipleChoices);


        EventEffectFactionSatisfaction[] eventEffectsOneChoice = new EventEffectFactionSatisfaction[1];
        eventEffectsOneChoice[0] = new EventEffectFactionSatisfaction(15, false, new CalculationFixed());
        eventEffectsOneChoice[0].addFaction("religieux");

        EventChoice[] eventSingleChoice = new EventChoice[1];
        eventSingleChoice[0] = new EventChoice("Décliner poliment au motif que vous n’avez pas les infrastructures pour eux", eventEffectsOneChoice);

        eventOneChoice = new Event("L'Organisation des Caraïbes-Unies souhaite que vous accueillez des réfugiés climatiques suite aux récentes inondations dans la région", null, eventSingleChoice);

    }

    public void testDisplayEventOneChoice() {
        assertEquals("L'Organisation des Caraïbes-Unies souhaite que vous accueillez des réfugiés climatiques suite aux récentes inondations dans la région\n" +
                "0. Décliner poliment au motif que vous n’avez pas les infrastructures pour eux\n" +
                "effets: +15 % de satisfaction pour les religieux", eventOneChoice.display(islandNormal.getDifficulty().getEffectRatio()));
    }

    public void testDisplayEventMultipleChoices() {
        assertEquals("L'Organisation des Caraïbes-Unies souhaite que vous accueillez des réfugiés climatiques suite aux récentes inondations dans la région\n" +
                "0. Décliner poliment au motif que vous n’avez pas les infrastructures pour eux\n" +
                "effets: +15 % de satisfaction pour les religieux, -15 % de satisfaction pour les libéraux\n" +
                "1. Décliner poliment au motif que vous n’avez pas les infrastructures pour eux\n" +
                "effets: +15 % de satisfaction pour les capitalistes, -15 % de satisfaction pour les loyalistes", eventMultipleChoice.display(islandNormal.getDifficulty().getEffectRatio()));
    }

    public void testChooseInSingleChoice() {
        eventOneChoice.applyChoice(islandNormal, 0);
        assertEquals(65, islandNormal.getPopulation().getFactionByName("religieux").getSatisfaction());
    }

    public void testChooseFirstInMultipleChoice() {
        eventMultipleChoice.applyChoice(islandNormal, 0);
        assertEquals(65, islandNormal.getPopulation().getFactionByName("religieux").getSatisfaction());
        assertEquals(35, islandNormal.getPopulation().getFactionByName("libéraux").getSatisfaction());
    }

    public void testChooseSecondInMultipleChoice() {
        eventMultipleChoice.applyChoice(islandNormal, 1);
        assertEquals(65, islandNormal.getPopulation().getFactionByName("capitalistes").getSatisfaction());
        assertEquals(35, islandNormal.getPopulation().getFactionByName("loyalistes").getSatisfaction());
    }

}
