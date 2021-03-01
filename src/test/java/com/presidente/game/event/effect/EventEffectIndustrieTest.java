package com.presidente.game.event.effect;

import com.presidente.builders.IslandBuilder;
import com.presidente.game.GameDifficulty;
import com.presidente.game.Island;
import com.presidente.game.Resource;
import com.presidente.game.event.effect.calculation.CalculationPercentage;
import junit.framework.TestCase;

public class EventEffectIndustrieTest extends TestCase {
    private Island islandEasy;
    private Island islandNormal;
    private Island islandHard;
    private Island islandNormalHundred;
    private Island islandNormalZero;
    private EventEffect eventEffectPositive;
    private EventEffect eventEffectNegative;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        eventEffectNegative = new EventEffectIndustrie(-15, new CalculationPercentage());
        eventEffectPositive = new EventEffectIndustrie(15, new CalculationPercentage());
        islandEasy = new IslandBuilder().setIndustrie(15).setDifficulty(GameDifficulty.EASY).setResource(new Resource(10)).build();
        islandNormal = new IslandBuilder().setIndustrie(15).setDifficulty(GameDifficulty.NORMAL).setResource(new Resource(10)).build();
        islandHard = new IslandBuilder().setIndustrie(15).setDifficulty(GameDifficulty.HARD).setResource(new Resource(10)).build();
        islandNormalHundred = new IslandBuilder().setIndustrie(100).setDifficulty(GameDifficulty.NORMAL).setResource(new Resource(10)).build();
        islandNormalZero = new IslandBuilder().setIndustrie(0).setDifficulty(GameDifficulty.NORMAL).setResource(new Resource(10)).build();
    }

    public void testEffectPositiveEasyDifficulty() {
        eventEffectPositive.applyEffect(islandEasy);
        assertEquals(19, islandEasy.getIndustrie());
    }

    public void testEffectNegativeEasyDifficulty() {
        eventEffectNegative.applyEffect(islandEasy);
        assertEquals(13, islandEasy.getIndustrie());
    }

    public void testEffectPositiveNormalDifficulty() {
        eventEffectPositive.applyEffect(islandNormal);
        assertEquals(17, islandNormal.getIndustrie());
    }

    public void testEffectNegativeNormalDifficulty() {
        eventEffectNegative.applyEffect(islandNormal);
        assertEquals(12, islandNormal.getIndustrie());
    }

    public void testEffectPositiveHardDifficulty() {
        eventEffectPositive.applyEffect(islandHard);
        assertEquals(16, islandHard.getIndustrie());
    }

    public void testEffectNegativeHardDifficulty() {
        eventEffectNegative.applyEffect(islandHard);
        assertEquals(10, islandHard.getIndustrie());
    }

    public void testEffectPositiveNotGreaterThanHundred() {
        eventEffectPositive.applyEffect(islandNormalHundred);
        assertTrue(islandNormalHundred.getIndustrie() + islandNormalHundred.getAgriculture() <= 100);
    }

    public void testEffectNegativeNotSmallerThanZero() {
        eventEffectNegative.applyEffect(islandNormalZero);
        assertTrue(islandNormalZero.getIndustrie() >= 0);
    }
}
