package fr.pantheonsorbonne.miage.game.Monopoly;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseAchetable;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Dumb;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public final class LocalMonopolyApp extends MonopolyEngine {

    protected LocalMonopolyApp(PerfectBoard plateauComplet) {
        super(plateauComplet);
    }

    public static void main(String... args) throws IsBankruptException {
        PerfectBoard plateauEnLocal = new PerfectBoard(new Dumb(0), new Dumb(1));
        LocalMonopolyApp localMonopoly = new LocalMonopolyApp(plateauEnLocal);

        localMonopoly.play();

        System.exit(0);
    }

    // ------------------------------------------

    @Override
    protected boolean askGetOutOfJail(int playerID, int playerPosition, PerfectBoard plateauComplet) {
    return plateauComplet.getPlayerByID(playerID).askGetOutOfJail();
    }

    @Override
    protected boolean askRemoveInstantlySquat(int playerID, CasePropriete caseSquatee, PerfectBoard plateauComplet) {
        return plateauComplet.getPlayerByID(playerID).askRemoveInstantlySquat(caseSquatee, plateauComplet);
    }

    @Override
    protected boolean askBuyProperty(int playerID, CaseAchetable caseAchetable, PerfectBoard plateauComplet) {
        return plateauComplet.getPlayerByID(playerID).askBuyProperty(caseAchetable, plateauComplet);
    }

    @Override
    protected void thinkAndDo(int playerID, PerfectBoard plateauComplet) throws IsBankruptException {
        for (Player joueur : plateauComplet.getListeJoueurs()) {
            if (joueur.getID() == playerID) {
                joueur.thinkAndDo(plateauComplet);
            }
        }
    }
}
