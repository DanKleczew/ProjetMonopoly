package fr.pantheonsorbonne.miage.game.Monopoly;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.TypePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.*;

public class LocalMonopolyAppTest {
    @Test
    public void allTest(){
        Player t1 = new VoidBot(0);
        PlayersManager liste = new PlayersManager(t1);
        PerfectBoard pb = new PerfectBoard(liste);

        LocalMonopolyApp lma = new LocalMonopolyApp(pb, liste);
        assertEquals( false, lma.askPlayerGetOutOfJail(0, pb, liste));
        assertEquals(false, lma.askPlayerBuyProperty(0, new CasePropriete("a", 100, TypePropriete.MARRON), pb, liste));
        assertEquals( false, lma.askPlayerRemoveInstantlySquat(0, new CasePropriete("a", 100, TypePropriete.MARRON), pb, liste));
        assertDoesNotThrow(()->lma.playerThinkAndDo(0, pb, liste));
    }
}
