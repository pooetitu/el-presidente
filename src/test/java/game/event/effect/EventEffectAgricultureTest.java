package game.event.effect;

import game.GameDifficulty;
import game.Island;
import game.Ressource;
import game.event.effect.calculation.CalculationPercentage;
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
        eventEffectNegative = new EventEffectAgriculture("-15% agriculture", -0.15, new CalculationPercentage());
        eventEffectPositive = new EventEffectAgriculture("+15% agriculture", 0.15, new CalculationPercentage());
        islandEasy = new Island(15, 15, GameDifficulty.EASY, new Ressource(10, 10));
        islandNormal = new Island(15, 15, GameDifficulty.NORMAL, new Ressource(10, 10));
        islandHard = new Island(15, 15, GameDifficulty.HARD, new Ressource(10, 10));
        islandNormalHundred = new Island(100, 15, GameDifficulty.NORMAL, new Ressource(10, 10));
        islandNormalZero = new Island(0, 15, GameDifficulty.NORMAL, new Ressource(10, 10));
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
        assertTrue(islandNormalHundred.getAgriculture() + islandNormalHundred.getIndustrie() <= 100);
    }

    public void testEffectNegativeNotSmallerThanZero() {
        eventEffectNegative.applyEffect(islandNormalZero);
        assertTrue(islandNormalZero.getAgriculture() + islandNormalZero.getIndustrie() >= 0);
    }
}
