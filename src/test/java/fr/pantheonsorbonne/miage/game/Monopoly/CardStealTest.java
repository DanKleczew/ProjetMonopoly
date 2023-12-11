package fr.pantheonsorbonne.miage.game.Monopoly;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.*;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.*;

public class CardStealTest{

    @Test
    public void testCardEffect() throws IsBankruptException{
        Player Thierry1 = new VoidBot(1);
        Player Thierry2 = new VoidBot(2);
        Player Thierry3 = new VoidBot(3);
        Player Thierry4 = new VoidBot(4);
        PlayersManager liste = new PlayersManager(Thierry1, Thierry2, Thierry3, Thierry4);
        PerfectBoard plateau2 = new PerfectBoard(liste);
        Card carte = new CardSteal(100, liste);
        carte.cardEffect(Thierry1, plateau2);
        assertEquals (1800, Thierry1.getBankAccount());
        assertEquals (1400, Thierry2.getBankAccount());
        assertEquals (1400, Thierry3.getBankAccount());
        assertEquals (1400, Thierry4.getBankAccount());
    }

    @Test
    public void testCardEffectLose() throws IsBankruptException{
        Player Thierry = new VoidBot(1);
        Player Thierry2 = new VoidBot(2);
        PlayersManager liste = new PlayersManager(Thierry, Thierry2);
        PerfectBoard plateaufantome = new PerfectBoard(liste);
        Card carte = new CardSteal(100, liste);
        Thierry2.bankAccountModify(-1499);
        carte.cardEffect(Thierry, plateaufantome);
        
        Deque<Player> listeJoueurExpected = new ArrayDeque<Player>();
        listeJoueurExpected.add(Thierry);

        assertArrayEquals(listeJoueurExpected.toArray(), liste.getListeJoueurs().toArray());
    }

}
