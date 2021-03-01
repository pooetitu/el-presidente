package com.presidente.game.event.effect;

import com.presidente.builders.IslandBuilder;
import com.presidente.builders.ResourceBuilder;
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

        Resource resource = new ResourceBuilder().addFood(100, 4).build();
        islandEasy = new IslandBuilder()
                .setResource(resource)
                .setDifficulty(GameDifficulty.EASY)
                .build();
        islandNormal = new IslandBuilder()
                .setResource(new ResourceBuilder().clone(resource).build())
                .setDifficulty(GameDifficulty.NORMAL)
                .build();
        islandHard = new IslandBuilder()
                .setResource(new ResourceBuilder().clone(resource).build())
                .setDifficulty(GameDifficulty.HARD)
                .build();
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
