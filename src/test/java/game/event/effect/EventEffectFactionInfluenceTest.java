package game.event.effect;

import game.Faction;
import game.GameDifficulty;
import game.Island;
import game.Ressource;
import junit.framework.TestCase;

public class EventEffectFactionInfluenceTest extends TestCase {
    Island islandEasy;
    Island islandNormal;
    Island islandHard;
    EventEffectFactionInfluence eventEffectPositive;
    EventEffectFactionInfluence eventEffectNegative;
    EventEffectFactionInfluence eventEffectGreaterThanHundred;
    EventEffectFactionInfluence eventEffectSmallerThanZero;
    EventEffectFactionInfluence eventEffectMultipleFactions;

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

        islandEasy = new Island(15, 15, GameDifficulty.NORMAL, new Ressource(10, 10));
        islandEasy.getPopulation().addFaction(new Faction("capitalistes", 50, 15));

        islandNormal = new Island(15, 15, GameDifficulty.NORMAL, new Ressource(10, 10));
        islandNormal.getPopulation().addFaction(new Faction("capitalistes", 50, 15));
        islandNormal.getPopulation().addFaction(new Faction("libéraux", 0, 15));
        islandNormal.getPopulation().addFaction(new Faction("communistes", 100, 15));

        islandHard = new Island(15, 15, GameDifficulty.NORMAL, new Ressource(10, 10));
        islandHard.getPopulation().addFaction(new Faction("capitalistes", 50, 15));

    }

    public void testEffectPositiveEasyDifficulty() {
        eventEffectPositive.applyEffect(islandEasy);
        assertEquals(65, islandEasy.getPopulation().getFactionByName("capitalistes").getSatisfaction());
    }

    public void testEffectNegativeEasyDifficulty() {
        eventEffectNegative.applyEffect(islandEasy);
        assertEquals(46, islandEasy.getPopulation().getFactionByName("capitalistes").getSatisfaction());
    }

    public void testEffectPositiveNormalDifficulty() {
        eventEffectPositive.applyEffect(islandNormal);
        assertEquals(57, islandEasy.getPopulation().getFactionByName("capitalistes").getSatisfaction());
    }

    public void testEffectNegativeNormalDifficulty() {
        eventEffectNegative.applyEffect(islandNormal);
        assertEquals(42, islandEasy.getPopulation().getFactionByName("capitalistes").getSatisfaction());
    }

    public void testEffectPositiveHardDifficulty() {
        eventEffectPositive.applyEffect(islandHard);
        assertEquals(53, islandEasy.getPopulation().getFactionByName("capitalistes").getSatisfaction());
    }

    public void testEffectNegativeHardDifficulty() {
        eventEffectNegative.applyEffect(islandHard);
        assertEquals(35, islandEasy.getPopulation().getFactionByName("capitalistes").getSatisfaction());
    }

    public void testMultipleFaction() {
        eventEffectMultipleFactions.applyEffect(islandNormal);
        assertEquals(42, islandEasy.getPopulation().getFactionByName("libéraux").getSatisfaction());
        assertEquals(42, islandEasy.getPopulation().getFactionByName("communistes").getSatisfaction());
    }
}
