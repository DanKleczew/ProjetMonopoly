package fr.pantheonsorbonne.miage.game.Monopoly.Cases;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CaseCaisseDeCommunaute extends Case implements CaseCard{

    public CaseCaisseDeCommunaute(String name) {
        super(name);
    }

    @Override
    public void doCaseEffect(Player joueur, PerfectBoard plateau) throws IsBankruptException {
        super.doCaseEffect(joueur, plateau);
        plateau.pickACaisseCard().cardEffect(joueur, plateau);
    }

}
