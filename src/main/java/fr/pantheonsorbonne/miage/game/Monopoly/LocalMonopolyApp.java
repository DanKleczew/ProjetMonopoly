package fr.pantheonsorbonne.miage.game.Monopoly;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseAchetable;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.*;

public final class LocalMonopolyApp extends MonopolyEngine {

    protected LocalMonopolyApp(PerfectBoard plateauComplet) {
        super(plateauComplet);
    }

    public static void main(String... args) throws IsBankruptException {
        // int winp1 = 0;
        // int winp2 = 0;
        
             for (int i = 0; i < 1000; i++) {
                PerfectBoard plateauEnLocal = new PerfectBoard(new MediumBot(0), new MediumBot(1) , new VoidBot(2), new VoidBot(3));
                LocalMonopolyApp localMonopoly = new LocalMonopolyApp(plateauEnLocal);
                localMonopoly.play();
            //     if (localMonopoly.play() <= 1){
            //         winp1++;
                 }
            //     else{
            //         winp2++;
            //     }
              //}
            //  System.out.println(winp1);
            //  System.out.println(winp2);
        System.exit(0);

    }

    // ------------------------------------------

    @Override
    protected boolean askPlayerGetOutOfJail(int playerID, PerfectBoard plateauComplet) {

    return plateauComplet.getPlayerByID(playerID).askGetOutOfJail(plateauComplet);

    }

    @Override
    protected boolean askPlayerRemoveInstantlySquat(int playerID, CasePropriete caseSquatee, PerfectBoard plateauComplet) {
        return plateauComplet.getPlayerByID(playerID).askRemoveInstantlySquat(caseSquatee, plateauComplet);
    }

    @Override
    protected boolean askPlayerBuyProperty(int playerID, CaseAchetable caseAchetable, PerfectBoard plateauComplet) {

        return plateauComplet.getPlayerByID(playerID).askBuyProperty(caseAchetable, plateauComplet);

    }

    @Override
    protected void playerThinkAndDo(int playerID, PerfectBoard plateauComplet) throws IsBankruptException {
        for (Player joueur : plateauComplet.getListeJoueurs()) {
            if (joueur.getID() == playerID) {
                joueur.thinkAndDo(plateauComplet);
            }
        }
    }
}
