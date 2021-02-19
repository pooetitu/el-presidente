package com.presidente.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.presidente.adapters.SubClassesTypeAdapter;
import com.presidente.game.event.effect.EventEffect;
import com.presidente.game.event.effect.calculation.Calculation;

public class GameFileParser {
    private static GameFileParser gameFileParser;
    private final Gson gson;

    private GameFileParser() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(EventEffect.class, new SubClassesTypeAdapter<EventEffect>())
                .registerTypeAdapter(Calculation.class, new SubClassesTypeAdapter<Calculation>())
                .create();
    }

    public static GameFileParser getGameFileParser() {
        if (gameFileParser == null) {
            gameFileParser = new GameFileParser();
        }
        return gameFileParser;
    }

    public <T> T parseData(String data, Class<T> c) {
        return gson.fromJson(data, c);
    }

    public String dataToJson(Object object) {
        System.out.println(gson.toJson(object) + " " + object);
        return gson.toJson(object);
    }
}
