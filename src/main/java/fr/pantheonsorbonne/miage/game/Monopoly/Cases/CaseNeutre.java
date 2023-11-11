package fr.pantheonsorbonne.miage.game.Monopoly.Cases;

import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CaseNeutre extends Case{

    public CaseNeutre(String name) {
        super(name);
    }

    //Il ne se passe rien
    @Override
    protected void doCaseEffect(Player joueur) {
    }

}
