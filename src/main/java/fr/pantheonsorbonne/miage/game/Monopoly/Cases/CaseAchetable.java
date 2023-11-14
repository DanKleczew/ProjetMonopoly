package fr.pantheonsorbonne.miage.game.Monopoly.Cases;

import java.util.Objects;

import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public abstract class CaseAchetable extends Case{

    public int prixAchat;
    protected Player possesseur = null;
    protected TypePropriete typeOuCouleur;

    public CaseAchetable(String name, int prixAchat, TypePropriete typeOuCouleur) {
        super(name);
        this.prixAchat = prixAchat;
        this.typeOuCouleur = typeOuCouleur;
    }    

    @Override
    protected void doCaseEffect(Player joueur) {
        // if (this.isBuyable()){
        //     joueur.askBuyProperty();
        // }
        // else {
        //     pay(joueur);
        // }
    }

    public boolean isBuyable(){
        return (Objects.isNull(possesseur));
    }

    public Player getOwner(){
        return this.possesseur;
    }

    public void setOwner(Player joueur){
        this.possesseur = joueur;
    }

    public boolean hasOwner(){
        return (!Objects.isNull(this.possesseur));
    }
    public TypePropriete getTypeOuCouleur(){
        return this.typeOuCouleur;
    }

    protected abstract void pay(Player joueurQuiPaye) throws IsBankruptException;
}
