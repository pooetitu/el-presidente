package game.event;

import game.event.effect.EventEffect;
import game.event.effect.EventEffectFactionSatisfaction;
import game.event.effect.calculation.CalculationPercentage;
import junit.framework.TestCase;

public class EventTest extends TestCase {
    private Event event;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        EventEffect[] eventEffects = new EventEffect[2];
        eventEffects[0] = new EventEffectFactionSatisfaction("-15 % de satisfaction pour les religieux,", 0.85, new CalculationPercentage());
        eventEffects[1] = new EventEffectFactionSatisfaction(" -15 % de satisfaction pour les libéraux", 0.85, new CalculationPercentage());

        EventChoice[] choices = new EventChoice[1];
        choices[0] = new EventChoice("Décliner poliment au motif que vous n’avez pas les infrastructures pour eux", eventEffects);
        event = new Event("L'Organisation des Caraïbes-Unies souhaite que vous accueillez des réfugiés climatiques suite aux récentes inondations dans la région", null, choices);
    }

    public void testDisplayEvent() {
        assertEquals("L'Organisation des Caraïbes-Unies souhaite que vous accueillez des réfugiés climatiques suite aux récentes inondations dans la région\n" +
                "1. Décliner poliment au motif que vous n’avez pas les infrastructures pour eux\n" +
                "effets: -15 % de satisfaction pour les religieux, -15 % de satisfaction pour les libéraux", event.display());
    }
}
