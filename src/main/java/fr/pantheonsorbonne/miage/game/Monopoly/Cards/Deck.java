package fr.pantheonsorbonne.miage.game.Monopoly.Cards;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public abstract class Deck {
    protected Queue<Card> deck;


    protected Deck(List<Card> deckArray){
        melangeDeck(deckArray);
    }
    
    public Card premierCarte(){
        return this.deck.poll();
    }
    
    public Card piocher(){
        Card firstCard = premierCarte();
        this.deck.offer(firstCard);
        return firstCard;
    };

    private void melangeDeck(List<Card> deckArray){
        Collections.shuffle(deckArray);
        this.deck = new ArrayDeque<Card>();
        this.deck.addAll(deckArray);
    }
}