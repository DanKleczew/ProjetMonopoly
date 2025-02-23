package fr.pantheonsorbonne.miage.game.Monopoly.Cases;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CaseChance extends Case implements CaseCard{

    public CaseChance(String name) {
        super(name);
    }

    @Override
    public void doCaseEffect(Player joueur, PerfectBoard plateauComplet) throws IsBankruptException {
        super.doCaseEffect(joueur, plateauComplet);
        plateauComplet.pickAChanceCard().cardEffect(joueur, plateauComplet);
    }

}
