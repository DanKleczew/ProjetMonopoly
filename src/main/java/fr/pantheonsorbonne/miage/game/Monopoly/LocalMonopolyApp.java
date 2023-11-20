package fr.pantheonsorbonne.miage.game.Monopoly;

import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public final class LocalMonopolyApp {

    private static PerfectBoard plateauComplet = new PerfectBoard();

    public static void main(String... args) throws IsBankruptException {

        while (! plateauComplet.isGameFinished()) {
            Player currentPlayer = plateauComplet.getNextPlayer();
            int compteurRepetitionTour = 0;
            int[] des;

            tourJoueur:
            do {
                compteurRepetitionTour++;
                des = currentPlayer.throwDice(plateauComplet);
                if (compteurRepetitionTour == 3 && des[0] == des[1]){
                    currentPlayer.setTimeOut();
                    break tourJoueur;
                }
                try {
                    plateauComplet.walk(currentPlayer, sumDes(des));
                    currentPlayer.thinkAndDo(plateauComplet);

                } catch (IsBankruptException e) {
                    //Si l'exception est thrown pendant le .walk (par exemple tombé sur une propriété adverse)
                    //Le joueur n'aura pas le loisir de .thinkAndDo
                    plateauComplet.deletePlayer(e);
                    break tourJoueur;
                }

            } while (des[0] == des[1]);
            


        }
        Player winner = plateauComplet.getNextPlayer();
        System.out.println("Victoire du joueur " + winner.getID() + " !");
        System.out.println("Liquidités en fin de partie : " + winner.getBankAccount());
    }

    private static int sumDes(int[] des) {
        return des[0] + des[1];
    }
}
