package fr.pantheonsorbonne.miage.game.Monopoly.Cases;

import fr.pantheonsorbonne.miage.game.Monopoly.Board;

import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CaseCaisseDeCommunaute extends Case {

    public CaseCaisseDeCommunaute(String name) {
        super(name);
        //TODO Auto-generated constructor stub
    }

    @Override
    protected void doCaseEffect(Player joueur) throws IsBankruptException {
        Board.pickACaisseCard().cardEffect(joueur);
    }

}
