package com.presidente.game;

import junit.framework.TestCase;

public class FoodTest extends TestCase {
    private Food food;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        food = new Food(50);
    }

    public void testRemoveFood() {
        food.removeFood(50);
        assertEquals(0, food.getAmount());
    }

    public void testRemoveFoodSubZero() {
        int rest = food.removeFood(100);
        assertEquals(0, food.getAmount());
        assertEquals(50, rest);
    }


    public void testAddFood() {
        food.addFood(100);
        assertEquals(150, food.getAmount());
    }
}