package fr.pantheonsorbonne.miage.game.Monopoly;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.*;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CardGoToPrisonTest {

    @Test
    public void TestCardEffect() throws IsBankruptException {
        Player Thierry = new Dumb(1);
        PerfectBoard plateau2 = new PerfectBoard(Thierry);
        Card prison = new CardGoToPrison();
        prison.cardEffect(Thierry, plateau2);
        Thierry.resetTimeOut(Thierry.askGetOutOfJail(plateau2));
        assertEquals(0, Thierry.getTimeOut());
        assertEquals(10, plateau2.getPositionJoueur(Thierry));

        Player Didier = new VoidBot(1);
        prison.cardEffect(Didier, plateau2);
        assertEquals(3, Didier.getTimeOut());
        assertEquals(10, plateau2.getPositionJoueur(Didier));
    }

    
}
