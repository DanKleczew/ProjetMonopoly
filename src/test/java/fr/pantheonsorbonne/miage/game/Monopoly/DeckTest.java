package fr.pantheonsorbonne.miage.game.Monopoly;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.*;
//import fr.pantheonsorbonne.miage.game.Monopoly.Cases.*;
//import fr.pantheonsorbonne.miage.game.Monopoly.Players.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import org.junit.jupiter.api.Test;



public class DeckTest {
    protected Queue<Card> deck;

    @Test
    public void piocherCaisseTest(){
        Deck deck = new DeckCaisse();
        Set<Card> a = new HashSet<Card>();
        for(int i = 0; i<15; i++){
            Card newCard = deck.piocher();
            assertEquals(false, a.contains(newCard));
            a.add(newCard);
        }
    
    }

    @Test
    public void piocherChanceTest(){
        Deck deck = new DeckCaisse();
        Set<Card> a = new HashSet<Card>();
        for(int i = 0; i<15; i++){
            Card newCard = deck.piocher();
            assertEquals(false, a.contains(newCard));
            a.add(newCard);
        }
    
    }
}