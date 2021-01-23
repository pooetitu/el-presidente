package game.event.effect;

import game.GameDifficulty;
import game.Island;
import game.Ressource;
import junit.framework.TestCase;

public class EventEffectIndustrieTest extends TestCase {
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
        eventEffectNegative = new EventEffectIndustrie("-15% industrie", -0.15);
        eventEffectPositive = new EventEffectIndustrie("+15% industrie", 0.15);
        islandEasy = new Island(15, 15, GameDifficulty.NORMAL, new Ressource(10, 10));
        islandNormal = new Island(15, 15, GameDifficulty.NORMAL, new Ressource(10, 10));
        islandHard = new Island(15, 15, GameDifficulty.NORMAL, new Ressource(10, 10));
        islandNormalHundred = new Island(100, 15, GameDifficulty.NORMAL, new Ressource(10, 10));
        islandNormalZero = new Island(0, 15, GameDifficulty.NORMAL, new Ressource(10, 10));
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
        assertTrue(islandNormalHundred.getIndustrie() + islandNormalHundred.getIndustrie() <= 100);
    }
    public void testEffectPositiveNotSmallerThanZero() {
        eventEffectNegative.applyEffect(islandNormalZero);
        assertTrue(islandNormalZero.getIndustrie() + islandNormalZero.getIndustrie() >= 0);
    }
}
