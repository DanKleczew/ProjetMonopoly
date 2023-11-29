package fr.pantheonsorbonne.miage.game.Monopoly.Cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeckChance extends Deck {
    private final static List<Card> DECK_ARRAY_CHANCE = new ArrayList<Card>(Arrays.asList(
            new CardDeplacement("Rue de la Paix", 39),
            new CardDeplacement("Case Départ", 0),
            new CardDeplacement("Avenue Henri-Martin", 24),
            new CardDeplacement("Boulevard de La Villette", 11),
            new CardVoierie(40, 115),
            new CardDeplacement("Gare de Lyon", 15),
            new CardWinLose(100),
            new CardWinLose(50),
            new CardDeplacement(-3),
            new CardGoToPrison(),
            new CardVoierie(25, 100),
            new CardWinLose(-15),
            new CardWinLose(-150),
            new CardWinLose(-20),
            new CardWinLose(150)));

    public DeckChance() {
        super(DECK_ARRAY_CHANCE);
    }
}
