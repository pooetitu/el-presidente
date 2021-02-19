package game.event.effect;

import game.GameDifficulty;
import game.Island;
import game.Resource;
import game.event.effect.calculation.CalculationFixed;
import junit.framework.TestCase;

public class EventEffectFoodTest extends TestCase {
    private Island islandEasy;
    private Island islandNormal;
    private Island islandHard;
    private EventEffect eventEffectPositive;
    private EventEffect eventEffectNegative;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        eventEffectNegative = new EventEffectFood(-100, new CalculationFixed());
        eventEffectPositive = new EventEffectFood(100, new CalculationFixed());
        islandEasy = new Island(15, 15, GameDifficulty.EASY, new Resource(100, 100));
        islandNormal = new Island(15, 15, GameDifficulty.NORMAL, new Resource(100, 100));
        islandHard = new Island(15, 15, GameDifficulty.HARD, new Resource(100, 100));
    }


    public void testEffectPositiveEasyDifficulty() {
        eventEffectPositive.applyEffect(islandEasy);
        assertEquals(300, islandEasy.getResource().getFood());
    }

    public void testEffectNegativeEasyDifficulty() {
        eventEffectNegative.applyEffect(islandEasy);
        assertEquals(50, islandEasy.getResource().getFood());
    }

    public void testEffectPositiveNormalDifficulty() {
        eventEffectPositive.applyEffect(islandNormal);
        assertEquals(200, islandNormal.getResource().getFood());
    }

    public void testEffectNegativeNormalDifficulty() {
        eventEffectNegative.applyEffect(islandNormal);
        assertEquals(0, islandNormal.getResource().getFood());
    }

    public void testEffectPositiveHardDifficulty() {
        eventEffectPositive.applyEffect(islandHard);
        assertEquals(150, islandHard.getResource().getFood());
    }

    public void testEffectNegativeHardDifficulty() {
        eventEffectNegative.applyEffect(islandHard);
        assertEquals(0, islandHard.getResource().getFood());
    }
}
