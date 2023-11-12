package fr.pantheonsorbonne.miage.game.Monopoly.Cases;

import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CasePropriete extends CaseAchetable{

    public CasePropriete(String name, int prixAchat, TypePropriete couleur) {
        super(name, prixAchat, couleur);
    }

    @Override
    protected void doCaseEffect(Player joueur) {
        if (this.isBuyable()){
            joueur.askBuyProperty();
        }
        else {
            pay(joueur);
        }
    }

    @Override
    protected void pay(Player joueur) {
        
        
    }

}
