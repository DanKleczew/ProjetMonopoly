package fr.pantheonsorbonne.miage.game.Monopoly;

import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public final class LocalMonopolyApp {

    private static PerfectBoard plateauComplet = new PerfectBoard();

    public static void main(String... args) {

        while (! plateauComplet.isGameFinished()) {
            Player currentPlayer = plateauComplet.getNextPlayer();
            int compteurRepetitionTour = 0;
            int[] des;

            do {
                compteurRepetitionTour++;
                des = currentPlayer.throwDice(plateauComplet);
                
                try {
                    plateauComplet.walk(currentPlayer, sumDes(des));
                    currentPlayer.thinkAndDo(plateauComplet);

                } catch (IsBankruptException e) {
                    // TODO: handle exception
                }

            } while (des[0] == des[1] && compteurRepetitionTour < 3);
            if (des[0] == des[1]) currentPlayer.setTimeOut();
            

        }
    }

    private static int sumDes(int[] des) {
        return des[0] + des[1];
    }
}
