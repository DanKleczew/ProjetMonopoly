package fr.pantheonsorbonne.miage.game.Monopoly;

import java.util.Random;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.Case;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseAchetable;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseCard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public abstract class MonopolyEngine {

    protected final PerfectBoard plateauComplet;
    private int compteTours = 0;
    private static final double SQUATT_PROBA_DENOMINATEUR = 20000.00;
    // La probabilité qu'un squatteur apparaisse est de (Somme Totale des Loyers
    // (voir getSommeTotaleLoyerActuelle() dans Board) / 20K )
    // Cela permet en moyenne de faire apparaître un squatteur tous les 4 tours vers
    // la fin de la partie.

    protected MonopolyEngine(PerfectBoard plateauComplet) {
        this.plateauComplet = plateauComplet;
    }

    protected int play() throws IsBankruptException {
        Random random = new Random();

        while (!plateauComplet.isGameFinished()) {
            
            Player currentPlayer = plateauComplet.getNextPlayer();
            if (currentPlayer.hasPlayed()) { // On retombe sur un joueur qui a déjà joué càd un tour est fini
                plateauComplet.resetPlayingStatusAllPlayers(); // On remet en false le a joué

                if (plateauComplet.getRichestPlayer().getBankAccount() > compteTours){
                    plateauComplet.getRichestPlayer().bankAccountModify(-compteTours);
                }
                compteTours++;

                if (Math.random() < plateauComplet.getSommeTotaleLoyerActuelle() / SQUATT_PROBA_DENOMINATEUR) {
                    // Simule la proba des squatteurs
                    CasePropriete randomProp = plateauComplet.getRandomOwnedPropriete();
                    randomProp.setSquat();
        
                    randomProp.removeSquat(
                            this.askPlayerRemoveInstantlySquat(currentPlayer.getID(), randomProp, plateauComplet),
                            plateauComplet);
                }

                plateauComplet.policeDoYourJob();
                plateauComplet.renteDesPrisons();
            }

            int compteurRepetitionTour = 0;
            int[] des;
            int thisDiceThrowSeed;
            currentPlayer.switchPlayingStatus(); // Indique que le joueur joue

            tourJoueur: do {
                // C'est avant tout le tour des casseurs
                if (plateauComplet.hasHouses(currentPlayer)) { // Si le joueur a des maisons
                    if (Math.random() < (1 / this.getProbaCasseurs(currentPlayer))) { 
                        plateauComplet.houseBreak(currentPlayer);
                    }
                }

                // Si le joueur est en prison
                if (currentPlayer.getTimeOut() > 0) {
                    // Il jette les dés
                    thisDiceThrowSeed = random.nextInt(1000000000);
                    des = currentPlayer.throwDice(plateauComplet, thisDiceThrowSeed);
                    // Si il fait un double
                    if (des[0] == des[1]) {
                        try {
                            walkAndDoEffect(plateauComplet, currentPlayer, des);

                            // Si ca le fait perdre on catch l'exception
                        } catch (IsBankruptException e) {
                            Case caseFinale = plateauComplet.getCaseByIndice(plateauComplet.getPositionJoueur(currentPlayer));
                            if (caseFinale instanceof CaseAchetable){
                                e.setGagnant(((CaseAchetable) caseFinale).getOwner());
                            };
                            plateauComplet.deletePlayer(e);
                        }
                        // Sinon (il est en prison et n'a pas fait un double)
                    } else {
                        // Si il veut payer 50 pour sortir
                        if (this.askPlayerGetOutOfJail(currentPlayer.getID(),
                                plateauComplet)) {
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
                    playerThinkAndDo(currentPlayer.getID(), plateauComplet);

                    // Mais impossible qu'il rejoue une deuxième fois (ou joue tout court si il a
                    // payé)
                    break tourJoueur;
                }

                // Si il n'est pas en prison :

                compteurRepetitionTour++;

                thisDiceThrowSeed = random.nextInt(100000);
                des = currentPlayer.throwDice(plateauComplet, thisDiceThrowSeed);
                if (compteurRepetitionTour == 3 && des[0] == des[1]) {
                    currentPlayer.setTimeOut(plateauComplet);
                    break tourJoueur;
                }
                try {
                    walkAndDoEffect(plateauComplet, currentPlayer, des);
                    playerThinkAndDo(currentPlayer.getID(), plateauComplet);

                } catch (IsBankruptException e) {
                    // Si l'exception est thrown pendant le .walk (par exemple tombé sur une
                    // propriété adverse)
                    // Le joueur n'aura pas le loisir de .thinkAndDo
                    Case caseFinale = plateauComplet.getCaseByIndice(plateauComplet.getPositionJoueur(currentPlayer));
                    if (caseFinale instanceof CaseAchetable){
                        e.setGagnant(((CaseAchetable) caseFinale).getOwner());
                    };
                    plateauComplet.deletePlayer(e);
                    break tourJoueur;
                }
            } while (des[0] == des[1]);

        }
        System.out.println("////////FIN DE PARTIE\\\\\\\\\\\\\\");
        System.out.println("Nombre de tours : " + compteTours);
        Player winner = plateauComplet.getNextPlayer();
        System.out.println("Victoire du joueur " + winner.getID() + " !");
        System.out.println("Liquidités en fin de partie : " + winner.getBankAccount());
        return winner.getID();
    }

    private double getProbaCasseurs(Player currentPlayer) {
        double loyerLePlusCher = plateauComplet.getLoyerDeBaseProprieteLaPlusChere(currentPlayer);
        return loyerLePlusCher - ((loyerLePlusCher/10.0) * plateauComplet.getNombrePrisons());
    }

    private static int sumDes(int[] des) {
        return des[0] + des[1];
    }

    private void walkAndDoEffect(PerfectBoard plateauComplet, Player currentPlayer, int[] des)
            throws IsBankruptException {
        // Il avance avec le montant des dés qu'il vient de lancer
        plateauComplet.walk(currentPlayer, sumDes(des));

        // On récupère la case sur laquelle il est tombé
        Case caseArrivee = plateauComplet.getCaseByIndice(plateauComplet.getPositionJoueur(currentPlayer));

        // Il se peut qu'en un lancer de dés on ait plusieurs doCaseEffect à cause des
        // cartes de déplacement
        while (caseArrivee instanceof CaseCard) {
            caseArrivee.doCaseEffect(currentPlayer, plateauComplet);
            // Si il n'y a pas eu de déplacement, on a déjà fait le doCaseEffect donc on
            // sort de la méthode
            if (plateauComplet.getCaseByIndice(plateauComplet.getPositionJoueur(currentPlayer)).equals(caseArrivee))
                return;
            // Si il y en a eu un on va vérifier si la nvelle case est une case à carte.
            caseArrivee = plateauComplet.getCaseByIndice(plateauComplet.getPositionJoueur(currentPlayer));
        }

        // Si c'est une case achetable pas encore achetée
        if (caseArrivee instanceof CaseAchetable && !((CaseAchetable) caseArrivee).hasOwner()) {
            CaseAchetable caseArriveeAchetable = (CaseAchetable) caseArrivee;
            // On lui demande si ca l'intéresse
            caseArriveeAchetable.buyThePropriete(currentPlayer,
                    askPlayerBuyProperty(currentPlayer.getID(), caseArriveeAchetable, plateauComplet));
        }

        // Quoi qu'il arrive, on applique le doCaseEffect après ces deux conditions
        caseArrivee.doCaseEffect(currentPlayer, plateauComplet);
    }

    protected abstract boolean askPlayerGetOutOfJail(int playerID, PerfectBoard plateauComplet);

    protected abstract boolean askPlayerBuyProperty(int playerID, CaseAchetable caseAchetable, PerfectBoard plateauComplet);

    protected abstract boolean askPlayerRemoveInstantlySquat(int playerID, CasePropriete proprieteSquatee,
            PerfectBoard plateauComplet);

    protected abstract void playerThinkAndDo(int playerID, PerfectBoard plateauComplet) throws IsBankruptException;
}
