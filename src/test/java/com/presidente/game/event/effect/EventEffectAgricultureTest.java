package com.presidente.game.event.effect;

import com.presidente.builders.IslandBuilder;
import com.presidente.game.GameDifficulty;
import com.presidente.game.Island;
import com.presidente.game.Resource;
import com.presidente.game.event.effect.calculation.CalculationPercentage;
import junit.framework.TestCase;

public class EventEffectAgricultureTest extends TestCase {
    Island islandEasy;
    Island islandNormal;
    Island islandHard;
    Island islandNormalHundred;
    Island islandNormalZero;
    EventEffect eventEffectPositive;
    EventEffect eventEffectNegative;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        eventEffectNegative = new EventEffectAgriculture(-15, new CalculationPercentage());
        eventEffectPositive = new EventEffectAgriculture(15, new CalculationPercentage());
        islandEasy = new IslandBuilder().setAgriculture(15).setDifficulty(GameDifficulty.EASY).setResource(new Resource(10)).build();
        islandNormal = new IslandBuilder().setAgriculture(15).setDifficulty(GameDifficulty.NORMAL).setResource(new Resource(10)).build();
        islandHard = new IslandBuilder().setAgriculture(15).setDifficulty(GameDifficulty.HARD).setResource(new Resource(10)).build();
        islandNormalHundred = new IslandBuilder().setAgriculture(100).setDifficulty(GameDifficulty.NORMAL).setResource(new Resource(10)).build();
        islandNormalZero = new IslandBuilder().setAgriculture(0).setDifficulty(GameDifficulty.NORMAL).setResource(new Resource(10)).build();
    }

    public void testEffectPositiveEasyDifficulty() {
        eventEffectPositive.applyEffect(islandEasy);
        assertEquals(19, islandEasy.getAgriculture());
    }

    public void testEffectNegativeEasyDifficulty() {
        eventEffectNegative.applyEffect(islandEasy);
        assertEquals(13, islandEasy.getAgriculture());
    }

    public void testEffectPositiveNormalDifficulty() {
        eventEffectPositive.applyEffect(islandNormal);
        assertEquals(17, islandNormal.getAgriculture());
    }

    public void testEffectNegativeNormalDifficulty() {
        eventEffectNegative.applyEffect(islandNormal);
        assertEquals(12, islandNormal.getAgriculture());
    }

    public void testEffectPositiveHardDifficulty() {
        eventEffectPositive.applyEffect(islandHard);
        assertEquals(16, islandHard.getAgriculture());
    }

    public void testEffectNegativeHardDifficulty() {
        eventEffectNegative.applyEffect(islandHard);
        assertEquals(10, islandHard.getAgriculture());
    }

    public void testEffectPositiveNotGreaterThanHundred() {
        eventEffectPositive.applyEffect(islandNormalHundred);
        assertTrue(islandNormalHundred.getIndustry() + islandNormalHundred.getAgriculture() <= 100);
    }

    public void testEffectNegativeNotSmallerThanZero() {
        eventEffectNegative.applyEffect(islandNormalZero);
        assertTrue(islandNormalZero.getAgriculture() >= 0);
    }
}
