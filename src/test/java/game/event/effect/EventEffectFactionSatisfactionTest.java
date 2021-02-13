package game.event.effect;

import game.Faction;
import game.GameDifficulty;
import game.Island;
import game.Resource;
import game.event.effect.calculation.CalculationFixed;
import junit.framework.TestCase;

public class EventEffectFactionSatisfactionTest extends TestCase {
    private Island islandEasy;
    private Island islandNormal;
    private Island islandHard;
    private EventEffectFactionSatisfaction eventEffectPositive;
    private EventEffectFactionSatisfaction eventEffectNegative;
    private EventEffectFactionSatisfaction eventEffectGreaterThanHundred;
    private EventEffectFactionSatisfaction eventEffectSmallerThanZero;
    private EventEffectFactionSatisfaction eventEffectPositiveOnZero;
    private EventEffectFactionSatisfaction eventEffectMultipleFactions;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        eventEffectNegative = new EventEffectFactionSatisfaction(-15, false, new CalculationFixed());
        eventEffectNegative.addFaction("capitalistes");

        eventEffectPositive = new EventEffectFactionSatisfaction(15, false, new CalculationFixed());
        eventEffectPositive.addFaction("capitalistes");

        eventEffectPositiveOnZero = new EventEffectFactionSatisfaction(15, false, new CalculationFixed());
        eventEffectPositiveOnZero.addFaction("libéraux");

        eventEffectSmallerThanZero = new EventEffectFactionSatisfaction(15, false, new CalculationFixed());
        eventEffectSmallerThanZero.addFaction("libéraux");

        eventEffectGreaterThanHundred = new EventEffectFactionSatisfaction(15, false, new CalculationFixed());
        eventEffectGreaterThanHundred.addFaction("communistes");

        eventEffectMultipleFactions = new EventEffectFactionSatisfaction(15, false, new CalculationFixed());
        eventEffectMultipleFactions.addFaction("capitalistes");
        eventEffectMultipleFactions.addFaction("religieux");

        islandEasy = new Island(15, 15, GameDifficulty.EASY, new Resource(10, 10));
        islandEasy.getPopulation().addFaction(new Faction("capitalistes", 50, 15));

        islandNormal = new Island(15, 15, GameDifficulty.NORMAL, new Resource(10, 10));
        islandNormal.getPopulation().addFaction(new Faction("capitalistes", 50, 15));
        islandNormal.getPopulation().addFaction(new Faction("écologistes", 50, 15));
        islandNormal.getPopulation().addFaction(new Faction("religieux", 50, 15));
        islandNormal.getPopulation().addFaction(new Faction("libéraux", 0, 15));
        islandNormal.getPopulation().addFaction(new Faction("communistes", 100, 15));

        islandHard = new Island(15, 15, GameDifficulty.HARD, new Resource(10, 10));
        islandHard.getPopulation().addFaction(new Faction("capitalistes", 50, 15));

    }

    public void testEffectPositiveEasyDifficulty() {
        eventEffectPositive.applyEffect(islandEasy);
        assertEquals(80, islandEasy.getPopulation().getFactionByName("capitalistes").getSatisfaction());
    }

    public void testEffectNegativeEasyDifficulty() {
        eventEffectNegative.applyEffect(islandEasy);
        assertEquals(42, islandEasy.getPopulation().getFactionByName("capitalistes").getSatisfaction());
    }

    public void testEffectPositiveNormalDifficulty() {
        eventEffectPositive.applyEffect(islandNormal);
        assertEquals(65, islandNormal.getPopulation().getFactionByName("capitalistes").getSatisfaction());
    }

    public void testEffectNegativeNormalDifficulty() {
        eventEffectNegative.applyEffect(islandNormal);
        assertEquals(35, islandNormal.getPopulation().getFactionByName("capitalistes").getSatisfaction());
    }

    public void testEffectPositiveHardDifficulty() {
        eventEffectPositive.applyEffect(islandHard);
        assertEquals(57, islandHard.getPopulation().getFactionByName("capitalistes").getSatisfaction());
    }

    public void testEffectNegativeHardDifficulty() {
        eventEffectNegative.applyEffect(islandHard);
        assertEquals(20, islandHard.getPopulation().getFactionByName("capitalistes").getSatisfaction());
    }

    public void testMultipleFaction() {
        eventEffectMultipleFactions.applyEffect(islandNormal);
        assertEquals(65, islandNormal.getPopulation().getFactionByName("capitalistes").getSatisfaction());
        assertEquals(65, islandNormal.getPopulation().getFactionByName("religieux").getSatisfaction());
    }

    public void testEffectPositiveNotGreaterThanHundred() {
        eventEffectGreaterThanHundred.applyEffect(islandNormal);
        assertTrue(islandNormal.getPopulation().getFactionByName("communistes").getSatisfaction() <= 100);
    }

    public void testEffectNegativeNotSmallerThanZero() {
        eventEffectSmallerThanZero.applyEffect(islandNormal);
        assertTrue(islandNormal.getPopulation().getFactionByName("libéraux").getSatisfaction() >= 0);
    }

    public void testEffectPositiveOnZeroInfluence() {
        eventEffectPositiveOnZero.applyEffect(islandNormal);
        assertEquals(0, islandNormal.getPopulation().getFactionByName("libéraux").getSatisfaction());
    }
}
