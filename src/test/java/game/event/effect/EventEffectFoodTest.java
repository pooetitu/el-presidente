package game.event.effect;

import game.GameDifficulty;
import game.Island;
import game.Ressource;
import junit.framework.TestCase;

public class EventEffectFoodTest extends TestCase {
    Island islandEasy;
    Island islandNormal;
    Island islandHard;
    EventEffect eventEffectPositive;
    EventEffect eventEffectNegative;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        eventEffectNegative = new EventEffectFood("-15% nourriture", -0.15);
        eventEffectPositive = new EventEffectMoney("+15% nourriture", 0.15);
        islandEasy = new Island(15, 15, GameDifficulty.NORMAL, new Ressource(10, 10));
        islandNormal = new Island(15, 15, GameDifficulty.NORMAL, new Ressource(10, 10));
        islandHard = new Island(15, 15, GameDifficulty.NORMAL, new Ressource(10, 10));
    }


    public void testEffectPositiveEasyDifficulty() {
        eventEffectPositive.applyEffect(islandEasy);
        assertEquals(300, islandEasy.getRessources().getFood());
    }

    public void testEffectNegativeEasyDifficulty() {
        eventEffectNegative.applyEffect(islandEasy);
        assertEquals(50, islandEasy.getRessources().getFood());
    }

    public void testEffectPositiveNormalDifficulty() {
        eventEffectPositive.applyEffect(islandNormal);
        assertEquals(200, islandNormal.getRessources().getFood());
    }

    public void testEffectNegativeNormalDifficulty() {
        eventEffectNegative.applyEffect(islandNormal);
        assertEquals(0, islandNormal.getRessources().getFood());
    }

    public void testEffectPositiveHardDifficulty() {
        eventEffectPositive.applyEffect(islandHard);
        assertEquals(-100, islandHard.getRessources().getFood());
    }

    public void testEffectNegativeHardDifficulty() {
        eventEffectNegative.applyEffect(islandHard);
        assertEquals(150, islandHard.getRessources().getFood());
    }
}
