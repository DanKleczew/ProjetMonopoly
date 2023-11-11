package fr.pantheonsorbonne.miage.game.Monopoly.Cases;

import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CaseGoToPrison extends Case {

    public CaseGoToPrison(String name) {
        super(name);
    }

    @Override
    protected void doCaseEffect(Player joueur) {
        joueur.setTimeOut();
    }

}
