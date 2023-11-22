package fr.pantheonsorbonne.miage.game.Monopoly;

import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Dumb;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public final class LocalMonopolyApp {

    private static PerfectBoard plateauComplet = new PerfectBoard(new Dumb(0), new Dumb(1));
    private static final double SQUATT_PROBA_DENOMINATEUR = 20000.00;
    // La probabilité qu'un squatteur apparaisse est de (Somme Totale des Loyers
    // (voir getSommeTotaleLoyerActuelle() dans Board) / 20K )
    // Cela permet en moyenne de faire apparaître un squatteur tous les 4 tours vers
    // la fin de la partie.

    public static void main(String... args) throws IsBankruptException {

        while (!plateauComplet.isGameFinished()) {
            Player currentPlayer = plateauComplet.getNextPlayer();

            if (currentPlayer.hasPlayed()) { // On retombe sur un joueur qui a déjà joué càd un tour est fini
                plateauComplet.resetPlayingStatusAllPlayers(); // On remet en false le a joué

                if (Math.random() < plateauComplet.getSommeTotaleLoyerActuelle() / SQUATT_PROBA_DENOMINATEUR) { 
                    // Simule la proba des squatteurs
                    CasePropriete randomProp = plateauComplet.getRandomOwnedPropriete();
                    randomProp.setSquat();
                    randomProp.removeSquat(randomProp.getOwner().askRemoveInstantlySquat(randomProp, plateauComplet));
                }

                plateauComplet.policeDoYourJob();
                plateauComplet.renteDesPrisons();
            }

            int compteurRepetitionTour = 0;
            int[] des;
            currentPlayer.switchPlayingStatus(); // Indique que le joueur joue

            tourJoueur: do {
                // C'est avant tout le tour des casseurs
                if (plateauComplet.hasHouses(currentPlayer)) { // Si le joueur a des maisons
                    if (Math.random() < (1 / plateauComplet.getLoyerDeBaseProprieteLaPlusChere(currentPlayer))
                            - (plateauComplet.getNombrePrisons() / 10)) { // Représente la proba qu'une maison casse
                        plateauComplet.houseBreak(currentPlayer);
                    }
                }

                // Si le joueur est en prison
                if (currentPlayer.getTimeOut() > 0) {
                    // Il jette les dés
                    des = currentPlayer.throwDice(plateauComplet);
                    // System.out.println(sumDes(des));
                    // Si il fait un double
                    if (des[0] == des[1]) {
                        // Il avance avec le montant des dés qu'il vient de lancer
                        try {
                            plateauComplet.walk(currentPlayer, sumDes(des));
                            // Si ca le fait perdre on catch l'exception
                        } catch (IsBankruptException e) {
                            plateauComplet.deletePlayer(e);

                        }
                        // Sinon (il est en prison et n'a pas fait un double)
                    } else {
                        // Si il veut payer 50 pour sortir
                        if (currentPlayer.askGetOutOfJail()) {
                            // On le sort
                            currentPlayer.resetTimeOut(true);
                        }
                        // Sinon
                        else {
                            // On lui réduit son time out de 1
                            currentPlayer.timeOutReduction();
                        }
                    }
                    // Quoi qu'il arrive si il était en prison au début du tour
                    // Il thinkAndDo un coup
                    currentPlayer.thinkAndDo(plateauComplet);
                    // Mais impossible qu'il rejoue une deuxième fois (ou joue tout court si il a
                    // payé)
                    break tourJoueur;
                }

                // Si il n'est pas en prison :

                compteurRepetitionTour++;
                des = currentPlayer.throwDice(plateauComplet);
                // System.out.println(sumDes(des));
                if (compteurRepetitionTour == 3 && des[0] == des[1]) {
                    currentPlayer.setTimeOut();
                    break tourJoueur;
                }
                try {
                    plateauComplet.walk(currentPlayer, sumDes(des));
                    currentPlayer.thinkAndDo(plateauComplet);
                } catch (IsBankruptException e) {
                    // Si l'exception est thrown pendant le .walk (par exemple tombé sur une
                    // propriété adverse)
                    // Le joueur n'aura pas le loisir de .thinkAndDo
                    plateauComplet.deletePlayer(e);
                    break tourJoueur;
                }
            } while (des[0] == des[1]);

        }
        System.out.println("////////FIN DE PARTIE\\\\\\\\\\\\\\");
        Player winner = plateauComplet.getNextPlayer();
        System.out.println("Victoire du joueur " + winner.getID() + " !");
        System.out.println("Liquidités en fin de partie : " + winner.getBankAccount());

    }

    private static int sumDes(int[] des) {
        return des[0] + des[1];
    }
}
