package fr.pantheonsorbonne.miage.game.Monopoly;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseAchetable;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.MediumBot;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.VoidBot;

public final class LocalMonopolyApp extends MonopolyEngine {

    protected LocalMonopolyApp(PerfectBoard plateauComplet) {
        super(plateauComplet);
    }

    public static void main(String... args) throws IsBankruptException {
        // int winp1 = 0;
        // int winp2 = 0;
        
            
             //for (int i = 0; i < 50; i++) {
                PerfectBoard plateauEnLocal = new PerfectBoard(new MediumBot(0), new VoidBot(1),new MediumBot(2),new MediumBot(3));
                LocalMonopolyApp localMonopoly = new LocalMonopolyApp(plateauEnLocal);
                localMonopoly.play();
            //     if (localMonopoly.play() == 1){
            //         winp1++;
            //     }
            //     else{
            //         winp2++;
            //     }
            //  }
            //  System.out.println(winp1);
            //  System.out.println(winp2);
        System.exit(0);

    }

    // ------------------------------------------

    @Override
    protected boolean askGetOutOfJail(int playerID, int playerPosition, PerfectBoard plateauComplet) {

        for (Player joueur : plateauComplet.getListeJoueurs()) {
            if (joueur.getID() == playerID) {
                return joueur.askGetOutOfJail(plateauComplet);
            }
        }
        return false; // Impossible à atteindre
    }

    @Override
    protected boolean askRemoveInstantlySquat(int playerID, CasePropriete caseSquatee, PerfectBoard plateauComplet) {

        for (Player joueur : plateauComplet.getListeJoueurs()) {
            if (joueur.getID() == playerID) {
                return joueur.askRemoveInstantlySquat(caseSquatee, plateauComplet);
            }
        }
        return false; // Impossible à atteindre
    }

    @Override
    protected boolean askBuyProperty(int playerID, CaseAchetable caseAchetable, PerfectBoard plateauComplet) {
        for (Player joueur : plateauComplet.getListeJoueurs()) {
            if (joueur.getID() == playerID) {
                return joueur.askBuyProperty(caseAchetable, plateauComplet);
            }
        }
        return false; // Impossible à atteindre
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
