package com.presidente.game.event.effect;

import com.presidente.game.GameDifficulty;
import com.presidente.game.Island;
import com.presidente.game.Resource;
import com.presidente.game.event.effect.calculation.CalculationFixed;
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
        islandEasy = new Island(15, 15, GameDifficulty.EASY, new Resource( 100));
        islandEasy.getResource().addFood(100,4);
        islandNormal = new Island(15, 15, GameDifficulty.NORMAL, new Resource( 100));
        islandNormal.getResource().addFood(100,4);
        islandHard = new Island(15, 15, GameDifficulty.HARD, new Resource( 100));
        islandHard.getResource().addFood(100,4);
    }


    public void testEffectPositiveEasyDifficulty() {
        eventEffectPositive.applyEffect(islandEasy);
        assertEquals(300, islandEasy.getResource().getFoodQuantity());
    }

    public void testEffectNegativeEasyDifficulty() {
        eventEffectNegative.applyEffect(islandEasy);
        assertEquals(50, islandEasy.getResource().getFoodQuantity());
    }

    public void testEffectPositiveNormalDifficulty() {
        eventEffectPositive.applyEffect(islandNormal);
        assertEquals(200, islandNormal.getResource().getFoodQuantity());
    }

    public void testEffectNegativeNormalDifficulty() {
        eventEffectNegative.applyEffect(islandNormal);
        assertEquals(0, islandNormal.getResource().getFoodQuantity());
    }

    public void testEffectPositiveHardDifficulty() {
        eventEffectPositive.applyEffect(islandHard);
        assertEquals(150, islandHard.getResource().getFoodQuantity());
    }

    public void testEffectNegativeHardDifficulty() {
        eventEffectNegative.applyEffect(islandHard);
        assertEquals(0, islandHard.getResource().getFoodQuantity());
    }
}
