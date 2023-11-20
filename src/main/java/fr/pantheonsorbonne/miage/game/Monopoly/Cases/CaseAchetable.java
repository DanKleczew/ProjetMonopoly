package fr.pantheonsorbonne.miage.game.Monopoly.Cases;

import java.util.Objects;

import fr.pantheonsorbonne.miage.game.Monopoly.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public abstract class CaseAchetable extends Case {

    private int prixAchat;
    private Player possesseur = null;
    private TypePropriete typeOuCouleur;
    private boolean estHypothequee = false;

    public CaseAchetable(String name, int prixAchat, TypePropriete typeOuCouleur) {
        super(name);
        this.prixAchat = prixAchat;
        this.typeOuCouleur = typeOuCouleur;
    }

    @Override
    public void doCaseEffect(Player joueur, PerfectBoard plateauComplet) throws IsBankruptException {
        super.doCaseEffect(joueur, plateauComplet);

        if (this.isBuyable()) {
            joueur.askBuyProperty(this, plateauComplet);
        } else {
            if (!estHypothequee)
            this.makePay(joueur, plateauComplet);
        }
    }

    public int getPrixAchat(){
        return this.prixAchat;
    }
    
    public boolean isBuyable() {
        return (Objects.isNull(possesseur));
    }

    public Player getOwner() {
        return this.possesseur;
    }

    public void setOwner(Player joueur) {
        this.possesseur = joueur;
    }

    public boolean hasOwner() {
        return (!Objects.isNull(this.possesseur));
    }

    public TypePropriete getTypeOuCouleur() {
        return this.typeOuCouleur;
    }

    public boolean isHypothequed(){
        return estHypothequee;
    }

    public void switchHypothequeStatus() throws IsBankruptException{
        //Si elle est hypothéquée et on veut la faire redevenir normale
        if (estHypothequee){
            this.possesseur.bankAccountModify(- (prixAchat/2 + (1/10)*prixAchat));
        }
        //Si elle est normale et on veut l'hypothéquer
        else{
            this.possesseur.bankAccountModify(this.prixAchat/2);
        }
        estHypothequee = !estHypothequee;
    }


    protected abstract void makePay(Player joueur, PerfectBoard plateau) throws IsBankruptException;
}
