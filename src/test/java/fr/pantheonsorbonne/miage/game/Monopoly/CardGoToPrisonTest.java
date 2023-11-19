package fr.pantheonsorbonne.miage.game.Monopoly;

import fr.pantheonsorbonne.miage.game.Monopoly.Cards.CardGoToPrison;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.Card;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Manual;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CardGoToPrisonTest {
    
    @Test
    public void TestcardEffect() throws IsBankruptException {
        Player Thierry = new Manual(1);
        PerfectBoard plateau2 = new PerfectBoard(Thierry);
        Card prison = new CardGoToPrison();
        prison.cardEffect(Thierry,plateau2);
        assertEquals(3, Thierry.getTimeOut());
    }
}
