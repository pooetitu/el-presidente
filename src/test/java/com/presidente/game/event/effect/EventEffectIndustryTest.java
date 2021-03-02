package com.presidente.game.event.effect;

import com.presidente.builders.IslandBuilder;
import com.presidente.game.GameDifficulty;
import com.presidente.game.Island;
import com.presidente.game.event.effect.calculation.CalculationPercentage;
import junit.framework.TestCase;

public class EventEffectIndustryTest extends TestCase {
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
        eventEffectNegative = new EventEffectIndustry(-15, new CalculationPercentage());
        eventEffectPositive = new EventEffectIndustry(15, new CalculationPercentage());
        islandEasy = new IslandBuilder().setIndustry(15).setDifficulty(GameDifficulty.EASY).build();
        islandNormal = new IslandBuilder().setIndustry(15).setDifficulty(GameDifficulty.NORMAL).build();
        islandHard = new IslandBuilder().setIndustry(15).setDifficulty(GameDifficulty.HARD).build();
        islandNormalHundred = new IslandBuilder().setIndustry(100).setDifficulty(GameDifficulty.NORMAL).build();
        islandNormalZero = new IslandBuilder().setIndustry(0).setDifficulty(GameDifficulty.NORMAL).build();
    }

    public void testEffectPositiveEasyDifficulty() {
        eventEffectPositive.applyEffect(islandEasy);
        assertEquals(19, islandEasy.getIndustry());
    }

    public void testEffectNegativeEasyDifficulty() {
        eventEffectNegative.applyEffect(islandEasy);
        assertEquals(13, islandEasy.getIndustry());
    }

    public void testEffectPositiveNormalDifficulty() {
        eventEffectPositive.applyEffect(islandNormal);
        assertEquals(17, islandNormal.getIndustry());
    }

    public void testEffectNegativeNormalDifficulty() {
        eventEffectNegative.applyEffect(islandNormal);
        assertEquals(12, islandNormal.getIndustry());
    }

    public void testEffectPositiveHardDifficulty() {
        eventEffectPositive.applyEffect(islandHard);
        assertEquals(16, islandHard.getIndustry());
    }

    public void testEffectNegativeHardDifficulty() {
        eventEffectNegative.applyEffect(islandHard);
        assertEquals(10, islandHard.getIndustry());
    }

    public void testEffectPositiveNotGreaterThanHundred() {
        eventEffectPositive.applyEffect(islandNormalHundred);
        assertTrue(islandNormalHundred.getIndustry() + islandNormalHundred.getAgriculture() <= 100);
    }

    public void testEffectNegativeNotSmallerThanZero() {
        eventEffectNegative.applyEffect(islandNormalZero);
        assertTrue(islandNormalZero.getIndustry() >= 0);
    }
}
