package fr.pantheonsorbonne.miage.game.Monopoly;

import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public final class LocalMonopolyApp {

    private static PerfectBoard plateauComplet = new PerfectBoard();

    public static void main(String... args) {

        BoucleDeJeu:
        for (;;){
            Player currentPlayer = plateauComplet.getNextPlayer();
            int compteurRepetitionTour;
            do{
                currentPlayer.throwDice(plateauComplet);


            }
            while (true);
        }
    }
}
