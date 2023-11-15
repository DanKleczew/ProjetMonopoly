package fr.pantheonsorbonne.miage.game.Monopoly.Cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeckCaisse extends Deck {
    private final static List<Card> DECK_ARRAY_CAISSE = new ArrayList<Card>(Arrays.asList(
            new CardDeplacement("Départ",0),
            new CardWinLose(200),
            new CardWinLose(-50),
            new CardWinLose(50),
            // libération prison
            new CardGoToPrison(),
            new CardDeplacement("Belleville",1),
            new CardWinLose(100),
            new CardSteal(10),
            new CardWinLose(20),
            new CardWinLose(25),
            new CardWinLose(-50),
            new CardWinLose(-10),
            new CardDeplacement("Gare la plus proche"),
            new CardWinLose(10),
            new CardWinLose(100)));

    public DeckCaisse() {
        super(DECK_ARRAY_CAISSE);
    }

}
