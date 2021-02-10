package game.event.effect;

import game.GameDifficulty;
import game.Island;
import game.Ressource;
import game.event.effect.calculation.CalculationFixed;
import junit.framework.TestCase;

public class EventEffectMoneyTest extends TestCase {
    private Island islandEasy;
    private Island islandNormal;
    private Island islandHard;
    private EventEffect eventEffectPositive;
    private EventEffect eventEffectNegative;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        eventEffectNegative = new EventEffectMoney(-100, new CalculationFixed());
        eventEffectPositive = new EventEffectMoney(100, new CalculationFixed());
        islandEasy = new Island(15, 15, GameDifficulty.EASY, new Ressource(100, 100));
        islandNormal = new Island(15, 15, GameDifficulty.NORMAL, new Ressource(100, 100));
        islandHard = new Island(15, 15, GameDifficulty.HARD, new Ressource(100, 100));
    }


    public void testEffectPositiveEasyDifficulty() {
        eventEffectPositive.applyEffect(islandEasy);
        assertEquals(300, islandEasy.getRessources().getTreasury());
    }


    public void testEffectNegativeEasyDifficulty() {
        eventEffectNegative.applyEffect(islandEasy);
        assertEquals(50, islandEasy.getRessources().getTreasury());
    }

    public void testEffectPositiveNormalDifficulty() {
        eventEffectPositive.applyEffect(islandNormal);
        assertEquals(200, islandNormal.getRessources().getTreasury());
    }

    public void testEffectNegativeNormalDifficulty() {
        eventEffectNegative.applyEffect(islandNormal);
        assertEquals(0, islandNormal.getRessources().getTreasury());
    }

    public void testEffectPositiveHardDifficulty() {
        eventEffectPositive.applyEffect(islandHard);
        assertEquals(150, islandHard.getRessources().getTreasury());
    }

    public void testEffectNegativeHardDifficulty() {
        eventEffectNegative.applyEffect(islandHard);
        assertEquals(-100, islandHard.getRessources().getTreasury());
    }
}
