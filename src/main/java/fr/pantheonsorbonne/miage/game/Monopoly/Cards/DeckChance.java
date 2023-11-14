package fr.pantheonsorbonne.miage.game.Monopoly.Cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeckChance extends Deck {
    private final static List<Card> DECK_ARRAY_CHANCE = new ArrayList<Card>(Arrays.asList(
            new CardDeplacement("rue de la Paix"),
            new CardDeplacement("case départ"),
            new CardDeplacement("l'Avenue Henr-Martin"),
            new CardDeplacement("Boulevard de La Vilette"),
            new CardVoierie(40, 115),
            new CardDeplacement("Gare de Lyon"),
            new CardWinLose(100),
            new CardWinLose(50),
            // libere prison
            new CardDeplacement(-3),
            new CardDeplacement("Prison"),
            new CardVoierie(25, 100),
            new CardWinLose(-15),
            new CardWinLose(-150),
            new CardWinLose(-20),
            new CardWinLose(150)));

    public DeckChance() {
        super(DECK_ARRAY_CHANCE);
    }
}
