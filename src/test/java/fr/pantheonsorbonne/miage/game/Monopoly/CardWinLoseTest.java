package fr.pantheonsorbonne.miage.game.Monopoly;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.Monopoly.Cards.*;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.*;

public class CardWinLoseTest{

    @Test
    public void testCardEffect() throws IsBankruptException {
        Player Thierry1 = new Manual(1);
        Player Thierry2 = new Manual(2);
        Player Thierry3 = new Manual(3);
        Player Thierry4 = new Manual(4);
        PerfectBoard plateau2 = new PerfectBoard(Thierry1,Thierry2,Thierry3,Thierry4);
        Card carte = new CardWinLose(100);
        Card carte2 = new CardWinLose(-100);
        carte.cardEffect(Thierry4, plateau2);
        carte2.cardEffect(Thierry3, plateau2);
        assertEquals(1600, Thierry4.getBankAccount());
        assertEquals(1400, Thierry3.getBankAccount());
    }
    
}
