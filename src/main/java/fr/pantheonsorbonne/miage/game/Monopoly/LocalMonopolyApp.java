package fr.pantheonsorbonne.miage.game.Monopoly;


import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Dumb;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;


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

    
}
