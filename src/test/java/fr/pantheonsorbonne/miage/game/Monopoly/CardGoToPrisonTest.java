package fr.pantheonsorbonne.miage.game.Monopoly;

import fr.pantheonsorbonne.miage.game.Monopoly.Cards.*;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CardGoToPrisonTest {

    @Test
    public void TestCardEffect() throws IsBankruptException {
        Player Thierry = new Manual(1);
        PerfectBoard plateau2 = new PerfectBoard(Thierry);
        Card prison = new CardGoToPrison();
        prison.cardEffect(Thierry, plateau2);
        assertEquals(3, Thierry.getTimeOut());
        assertEquals(10, plateau2.getPositionJoueur(Thierry));

    }
}
