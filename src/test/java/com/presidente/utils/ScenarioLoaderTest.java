package com.presidente.utils;

import com.presidente.game.Season;
import junit.framework.TestCase;

public class ScenarioLoaderTest extends TestCase {
    private ScenarioLoader loader;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        loader = ScenarioLoader.getInstance();
    }

    public void testGetInstance() {
        assertNotNull(ScenarioLoader.getInstance());
    }

    public void testLoadScenario() {
        for (int i = 0; i < loader.getScenarioList().size(); i++) {
            assertNotNull(loader.loadScenario(i));
        }
    }

    public void testLoadSeasons() {
        for (Season season : loader.loadSeasons()) {
            assertNotNull(season);
        }
    }

    public void testGetIslandSandboxInstance() {
        assertNotNull(loader.getIslandSandboxInstance());
    }
}