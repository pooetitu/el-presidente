package utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import game.*;
import game.event.Event;
import game.event.EventChoice;
import game.event.effect.*;
import game.event.effect.calculation.CalculationFixed;
import game.event.effect.calculation.CalculationPercentage;

public class GameFileParser {
    private static GameFileParser gameFileParser;
    private final XStream xstream;

    private GameFileParser() {
        xstream = new XStream(new JettisonMappedXmlDriver());
        xstream.setMode(XStream.NO_REFERENCES);
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

    public static GameFileParser getGameFileParser() {
        if (gameFileParser == null) {
            gameFileParser = new GameFileParser();
        }
        return gameFileParser;
    }

    public Object parseData(String data) {
        return xstream.fromXML(data);
    }

    public String dataToJson(Object object) {
        return xstream.toXML(object);
    }
}
