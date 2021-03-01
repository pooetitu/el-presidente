package com.presidente.game.event.effect;

import com.presidente.builders.IslandBuilder;
import com.presidente.game.GameDifficulty;
import com.presidente.game.Island;
import com.presidente.game.Resource;
import com.presidente.game.event.effect.calculation.CalculationFixed;
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

        islandEasy = new IslandBuilder().setDifficulty(GameDifficulty.EASY).setResource(new Resource(100)).build();
        islandNormal = new IslandBuilder().setDifficulty(GameDifficulty.NORMAL).setResource(new Resource(100)).build();
        islandHard = new IslandBuilder().setDifficulty(GameDifficulty.HARD).setResource(new Resource(100)).build();
    }


    public void testEffectPositiveEasyDifficulty() {
        eventEffectPositive.applyEffect(islandEasy);
        assertEquals(300, islandEasy.getResource().getTreasury());
    }


    public void testEffectNegativeEasyDifficulty() {
        eventEffectNegative.applyEffect(islandEasy);
        assertEquals(50, islandEasy.getResource().getTreasury());
    }

    public void testEffectPositiveNormalDifficulty() {
        eventEffectPositive.applyEffect(islandNormal);
        assertEquals(200, islandNormal.getResource().getTreasury());
    }

    public void testEffectNegativeNormalDifficulty() {
        eventEffectNegative.applyEffect(islandNormal);
        assertEquals(0, islandNormal.getResource().getTreasury());
    }

    public void testEffectPositiveHardDifficulty() {
        eventEffectPositive.applyEffect(islandHard);
        assertEquals(150, islandHard.getResource().getTreasury());
    }

    public void testEffectNegativeHardDifficulty() {
        eventEffectNegative.applyEffect(islandHard);
        assertEquals(-100, islandHard.getResource().getTreasury());
    }
}
