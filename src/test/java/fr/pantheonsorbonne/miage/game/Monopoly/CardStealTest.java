package fr.pantheonsorbonne.miage.game.Monopoly;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.Card;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.CardSteal;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Manual;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CardStealTest{

    @Test
    public void testCardEffect() throws IsBankruptException {
        Player Thierry1 = new Manual(1);
        Player Thierry2 = new Manual(2);
        Player Thierry3 = new Manual(3);
        Player Thierry4 = new Manual(4);
        PerfectBoard plateau2 = new PerfectBoard(Thierry1,Thierry2,Thierry3,Thierry4);
        Card carte = new CardSteal(100);
        carte.cardEffect(Thierry1, plateau2);
        assertEquals (1800, Thierry1.getBankAccount());
        assertEquals (1400, Thierry2.getBankAccount());
        assertEquals (1400, Thierry3.getBankAccount());
        assertEquals (1400, Thierry4.getBankAccount());
    }
}
