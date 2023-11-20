package fr.pantheonsorbonne.miage.game.Monopoly;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

import fr.pantheonsorbonne.miage.game.Monopoly.Cards.Card;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.CardDeplacement;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.CardGoToPrison;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.CardSteal;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.CardWinLose;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.Deck;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.DeckCaisse;

public abstract class DeckTest {
    protected Queue<Card> deck;


    // public Card piocher(){
    //     Card firstCard = this.deck.poll();
    //     this.deck.offer(firstCard);
    //     return firstCard;
    // };

    public void piocher(){
        Deck caisse = new DeckCaisse();
        //caisse
        // List<Card> DECK_ARRAY_CAISSE = new ArrayList<Card>(Arrays.asList(
        //     new CardDeplacement("Départ",0),
        //     new CardWinLose(200),
        //     new CardWinLose(-50),
        //     new CardWinLose(50),
        //     // libération prison
        //     new CardGoToPrison(),
        //     new CardDeplacement("Belleville",1),
        //     new CardWinLose(100),
        //     new CardSteal(10),
        //     new CardWinLose(20),
        //     new CardWinLose(25),
        //     new CardWinLose(-50),
        //     new CardWinLose(-10),
        //     new CardDeplacement("Gare la plus proche"),
        //     new CardWinLose(10),
        //     new CardWinLose(100))
        // );
        // Queue<Card> deck = new ArrayDeque<Card>();
        // deck.addAll(DECK_ARRAY_CAISSE);

        // List<Card> DECK_ARRAY_CAISSE2 = new ArrayList<Card>(Arrays.asList(
        //     new CardWinLose(200),
        //     new CardWinLose(-50),
        //     new CardWinLose(50),
        //     // libération prison
        //     new CardGoToPrison(),
        //     new CardDeplacement("Belleville",1),
        //     new CardWinLose(100),
        //     new CardSteal(10),
        //     new CardWinLose(20),
        //     new CardWinLose(25),
        //     new CardWinLose(-50),
        //     new CardWinLose(-10),
        //     new CardDeplacement("Gare la plus proche"),
        //     new CardWinLose(10),
        //     new CardWinLose(100),
        //     new CardDeplacement("Départ",0))
        // );
        // Queue<Card> deck2 = new ArrayDeque<Card>();
        // deck2.addAll(DECK_ARRAY_CAISSE2);

        // List<Card> DECK_ARRAY_CAISSE3 = new ArrayList<Card>(Arrays.asList(
        //     new CardDeplacement("Départ",0),
        //     new CardWinLose(200),
        //     new CardWinLose(-50),
        //     new CardWinLose(50),
        //     // libération prison
        //     new CardGoToPrison(),
        //     new CardDeplacement("Belleville",1),
        //     new CardWinLose(100),
        //     new CardSteal(10),
        //     new CardWinLose(20),
        //     new CardWinLose(25),
        //     new CardWinLose(-50),
        //     new CardWinLose(-10),
        //     new CardDeplacement("Gare la plus proche"),
        //     new CardWinLose(10),
        //     new CardWinLose(100))
        // );
        // Queue<Card> deck3 = new ArrayDeque<Card>();
        // deck3.addAll(DECK_ARRAY_CAISSE3);
        //initialisé deck,deck3 identique
        //initialisé deck2 = deck1 après avoir piocher
        
    }


    private void melangeDeck(List<Card> deckArray){
        Collections.shuffle(deckArray);
        this.deck = new ArrayDeque<Card>();
        this.deck.addAll(deckArray);
    }
}