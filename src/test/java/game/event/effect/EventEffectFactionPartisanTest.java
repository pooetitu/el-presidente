package game.event.effect;

import game.Faction;
import game.GameDifficulty;
import game.Island;
import game.Ressource;
import junit.framework.TestCase;

public class EventEffectFactionPartisanTest extends TestCase {
    private Island islandEasy;
    private Island islandNormal;
    private Island islandHard;
    private EventEffectFactionInfluence eventEffectPositive;
    private EventEffectFactionInfluence eventEffectNegative;
    private EventEffectFactionInfluence eventEffectGreaterThanHundred;
    private EventEffectFactionInfluence eventEffectSmallerThanZero;
    private EventEffectFactionInfluence eventEffectMultipleFactions;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        eventEffectNegative = new EventEffectFactionInfluence("-15% influence capitalistes", -0.15);
        eventEffectNegative.addFaction("capitalistes");

        eventEffectPositive = new EventEffectFactionInfluence("+15% influence capitalistes", 0.15);
        eventEffectPositive.addFaction("capitalistes");

        eventEffectSmallerThanZero = new EventEffectFactionInfluence("-15% influence libéraux", -0.15);
        eventEffectSmallerThanZero.addFaction("libéraux");

        eventEffectGreaterThanHundred = new EventEffectFactionInfluence("+15% influence communistes", 0.15);
        eventEffectGreaterThanHundred.addFaction("communistes");

        eventEffectMultipleFactions = new EventEffectFactionInfluence("+15% influence communistes et communistes", 0.15);
        eventEffectMultipleFactions.addFaction("capitalistes");
        eventEffectMultipleFactions.addFaction("communistes");

        islandEasy = new Island(15, 15, GameDifficulty.EASY, new Ressource(10, 10));
        islandEasy.getPopulation().addFaction(new Faction("capitalistes", 50, 50));

        islandNormal = new Island(15, 15, GameDifficulty.NORMAL, new Ressource(10, 10));
        islandNormal.getPopulation().addFaction(new Faction("capitalistes", 50, 50));
        islandNormal.getPopulation().addFaction(new Faction("écologistes", 50, 50));
        islandNormal.getPopulation().addFaction(new Faction("religieux", 50, 50));
        islandNormal.getPopulation().addFaction(new Faction("libéraux", 0, 0));
        islandNormal.getPopulation().addFaction(new Faction("communistes", 100, 100));

        islandHard = new Island(15, 15, GameDifficulty.HARD, new Ressource(10, 10));
        islandHard.getPopulation().addFaction(new Faction("capitalistes", 50, 50));

    }

    public void testEffectPositiveEasyDifficulty() {
        eventEffectPositive.applyEffect(islandEasy);
        assertEquals(65, islandEasy.getPopulation().getFactionByName("capitalistes").getSupporter());
    }

    public void testEffectNegativeEasyDifficulty() {
        eventEffectNegative.applyEffect(islandEasy);
        assertEquals(46, islandEasy.getPopulation().getFactionByName("capitalistes").getSupporter());
    }

    public void testEffectPositiveNormalDifficulty() {
        eventEffectPositive.applyEffect(islandNormal);
        assertEquals(57, islandEasy.getPopulation().getFactionByName("capitalistes").getSupporter());
    }

    public void testEffectNegativeNormalDifficulty() {
        eventEffectNegative.applyEffect(islandNormal);
        assertEquals(42, islandEasy.getPopulation().getFactionByName("capitalistes").getSupporter());
    }

    public void testEffectPositiveHardDifficulty() {
        eventEffectPositive.applyEffect(islandHard);
        assertEquals(53, islandEasy.getPopulation().getFactionByName("capitalistes").getSupporter());
    }

    public void testEffectNegativeHardDifficulty() {
        eventEffectNegative.applyEffect(islandHard);
        assertEquals(35, islandEasy.getPopulation().getFactionByName("capitalistes").getSupporter());
    }

    public void testMultipleFaction() {
        eventEffectMultipleFactions.applyEffect(islandNormal);
        assertEquals(42, islandNormal.getPopulation().getFactionByName("religieux").getSatisfaction());
        assertEquals(42, islandNormal.getPopulation().getFactionByName("écologistes").getSatisfaction());
    }

    public void testEffectPositiveNotGreaterThanHundred() {
        eventEffectGreaterThanHundred.applyEffect(islandNormal);
        assertTrue(islandNormal.getPopulation().getFactionByName("communistes").getSatisfaction() <= 100);
    }

    public void testEffectNegativeNotSmallerThanZero() {
        eventEffectGreaterThanHundred.applyEffect(islandNormal);
        assertTrue(islandNormal.getPopulation().getFactionByName("libéraux").getSatisfaction() >= 0);
    }
}
