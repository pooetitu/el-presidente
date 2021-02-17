package com.presidente.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.presidente.game.*;
import com.presidente.game.event.Event;
import com.presidente.game.event.EventChoice;
import com.presidente.game.event.effect.*;
import com.presidente.game.event.effect.calculation.CalculationFixed;
import com.presidente.game.event.effect.calculation.CalculationPercentage;

public class GameFileParser {
    private static GameFileParser gameFileParser;
    private final XStream xstream;

    private GameFileParser() {
        xstream = new XStream(new JettisonMappedXmlDriver());
        xstream.setMode(XStream.NO_REFERENCES);
        initXStreamSecurity(xstream);
        initXStreamAliases(xstream);
    }

    public static GameFileParser getGameFileParser() {
        if (gameFileParser == null) {
            gameFileParser = new GameFileParser();
        }
        return gameFileParser;
    }

    private void initXStreamAliases(XStream xstream) {
        xstream.autodetectAnnotations(true);
        xstream.processAnnotations(Season.class);
        xstream.processAnnotations(Event.class);
        xstream.processAnnotations(EventChoice.class);
        xstream.processAnnotations(EventEffectFactionSatisfaction.class);
        xstream.processAnnotations(EventEffectFactionSupporter.class);
        xstream.processAnnotations(EventEffectIndustrie.class);
        xstream.processAnnotations(EventEffectAgriculture.class);
        xstream.processAnnotations(EventEffectMoney.class);
        xstream.processAnnotations(EventEffectFood.class);
        xstream.processAnnotations(CalculationPercentage.class);
        xstream.processAnnotations(CalculationFixed.class);
        xstream.processAnnotations(Population.class);
        xstream.processAnnotations(Faction.class);
        xstream.processAnnotations(Island.class);
        xstream.processAnnotations(Resource.class);
    }

    private void initXStreamSecurity(XStream xstream) {
        Class<?>[] classes = new Class[]{Island.class, Resource.class, Faction.class, Population.class, Season.class, Event.class,
                EventChoice.class, EventEffectFactionSatisfaction.class, EventEffectFactionSupporter.class, EventEffectIndustrie.class,
                EventEffectAgriculture.class, EventEffectMoney.class, EventEffectFood.class, CalculationPercentage.class, CalculationFixed.class};
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);
    }

    public Object parseData(String data) {
        return xstream.fromXML(data);
    }

    public String dataToJson(Object object) {
        return xstream.toXML(object);
    }
}
