package com.presidente.game.event.effect;

import com.presidente.builders.IslandBuilder;
import com.presidente.game.Faction;
import com.presidente.game.GameDifficulty;
import com.presidente.game.Island;
import com.presidente.game.Population;
import com.presidente.game.event.effect.calculation.CalculationPercentage;
import junit.framework.TestCase;

public class EventEffectFactionSupporterTest extends TestCase {
    private Island islandEasy;
    private Island islandNormal;
    private Island islandHard;
    private EventEffectFactionSupporter eventEffectPositive;
    private EventEffectFactionSupporter eventEffectNegative;
    private EventEffectFactionSupporter eventEffectMultipleFactions;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        eventEffectNegative = new EventEffectFactionSupporter(-15, false, new CalculationPercentage());
        eventEffectNegative.addFaction("capitalistes");

        eventEffectPositive = new EventEffectFactionSupporter(15, false, new CalculationPercentage());
        eventEffectPositive.addFaction("capitalistes");

        eventEffectMultipleFactions = new EventEffectFactionSupporter(15, false, new CalculationPercentage());
        eventEffectMultipleFactions.addFaction("capitalistes");
        eventEffectMultipleFactions.addFaction("écologistes");

        Population population = new Population();
        population.addFaction(new Faction("capitalistes", 50, 50));
        islandEasy = new IslandBuilder().setPopulation(population).setDifficulty(GameDifficulty.EASY).build();

        population = new Population();
        population.addFaction(new Faction("capitalistes", 50, 50));
        population.addFaction(new Faction("écologistes", 50, 50));
        islandNormal = new IslandBuilder().setPopulation(population).setDifficulty(GameDifficulty.NORMAL).build();

        population = new Population();
        population.addFaction(new Faction("capitalistes", 50, 50));
        islandHard = new IslandBuilder().setPopulation(population).setDifficulty(GameDifficulty.HARD).build();

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
        assertEquals(57, islandNormal.getPopulation().getFactionByName("capitalistes").getSupporter());
    }

    public void testEffectNegativeNormalDifficulty() {
        eventEffectNegative.applyEffect(islandNormal);
        assertEquals(42, islandNormal.getPopulation().getFactionByName("capitalistes").getSupporter());
    }

    public void testEffectPositiveHardDifficulty() {
        eventEffectPositive.applyEffect(islandHard);
        assertEquals(53, islandHard.getPopulation().getFactionByName("capitalistes").getSupporter());
    }

    public void testEffectNegativeHardDifficulty() {
        eventEffectNegative.applyEffect(islandHard);
        assertEquals(35, islandHard.getPopulation().getFactionByName("capitalistes").getSupporter());
    }

    public void testMultipleFaction() {
        eventEffectMultipleFactions.applyEffect(islandNormal);
        assertEquals(57, islandNormal.getPopulation().getFactionByName("écologistes").getSupporter());
        assertEquals(57, islandNormal.getPopulation().getFactionByName("capitalistes").getSupporter());
    }

}
