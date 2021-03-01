package com.presidente.game.event.effect;

import com.presidente.builders.IslandBuilder;
import com.presidente.game.Faction;
import com.presidente.game.GameDifficulty;
import com.presidente.game.Island;
import com.presidente.game.Population;
import com.presidente.game.event.effect.calculation.CalculationFixed;
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

        Population population = new Population();
        population.addFaction(new Faction("capitalistes", 50, 15));
        islandEasy = new IslandBuilder().setPopulation(population).setDifficulty(GameDifficulty.EASY).build();

        population = new Population();
        population.addFaction(new Faction("capitalistes", 50, 15));
        population.addFaction(new Faction("écologistes", 50, 15));
        population.addFaction(new Faction("religieux", 50, 15));
        population.addFaction(new Faction("libéraux", 0, 15));
        population.addFaction(new Faction("communistes", 100, 15));
        islandNormal = new IslandBuilder().setPopulation(population).setDifficulty(GameDifficulty.NORMAL).build();

        population = new Population();
        population.addFaction(new Faction("capitalistes", 50, 15));
        islandHard = new IslandBuilder().setPopulation(population).setDifficulty(GameDifficulty.HARD).build();

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
