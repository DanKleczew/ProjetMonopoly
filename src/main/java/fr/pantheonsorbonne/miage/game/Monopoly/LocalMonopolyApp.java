package fr.pantheonsorbonne.miage.game.Monopoly;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseAchetable;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.*;

public final class LocalMonopolyApp extends MonopolyEngine {

    protected LocalMonopolyApp(PerfectBoard plateauComplet, PlayersManager ensembleDesJoueurs) {
        super(plateauComplet, ensembleDesJoueurs);
    }

    public static void main(String... args) throws IsBankruptException {


        PlayersManager ensembleDesJoueurs = new PlayersManager(new CleverBot(0), new CleverBot(1) , new Dumb(2), new Dumb(3));
        PerfectBoard plateauEnLocal = new PerfectBoard(ensembleDesJoueurs);
       
        MonopolyEngine localMonopoly = new LocalMonopolyApp(plateauEnLocal, ensembleDesJoueurs);
        
        localMonopoly.play();
        
        System.exit(0);

    }

    // ------------------------------------------

    @Override
    protected boolean askPlayerGetOutOfJail(int playerID, PerfectBoard plateauComplet, PlayersManager ensembleDesJoueurs) {

    return ensembleDesJoueurs.getPlayerByID(playerID).askGetOutOfJail(plateauComplet);

    }

    @Override
    protected boolean askPlayerRemoveInstantlySquat(int playerID, CasePropriete caseSquatee, PerfectBoard plateauComplet, PlayersManager ensembleDesJoueurs) {
        return ensembleDesJoueurs.getPlayerByID(playerID).askRemoveInstantlySquat(caseSquatee, plateauComplet);
    }

    @Override
    protected boolean askPlayerBuyProperty(int playerID, CaseAchetable caseAchetable, PerfectBoard plateauComplet, PlayersManager ensembleDesJoueurs) {

        return ensembleDesJoueurs.getPlayerByID(playerID).askBuyProperty(caseAchetable, plateauComplet);

    }

    @Override
    protected void playerThinkAndDo(int playerID, PerfectBoard plateauComplet, PlayersManager ensembleDesJoueurs) throws IsBankruptException {
        for (Player joueur : ensembleDesJoueurs.getListeJoueurs()) {
            if (joueur.getID() == playerID) {
                joueur.thinkAndDo(plateauComplet);
            }
        }
    }
}
