package fr.pantheonsorbonne.miage.game.Monopoly.Cases;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CaseGoToPrison extends Case {

    public CaseGoToPrison(String name) {
        super(name);
    }

    @Override
    public void doCaseEffect(Player joueur, PerfectBoard plateauComplet) throws IsBankruptException {
        super.doCaseEffect(joueur, plateauComplet);
        joueur.setTimeOut(plateauComplet);
        
    }

}
