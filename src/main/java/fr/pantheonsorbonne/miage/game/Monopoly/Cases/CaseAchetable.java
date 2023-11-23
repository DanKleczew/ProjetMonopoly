package fr.pantheonsorbonne.miage.game.Monopoly.Cases;

import fr.pantheonsorbonne.miage.game.Monopoly.Board;
import fr.pantheonsorbonne.miage.game.Monopoly.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public abstract class CaseAchetable extends Case {

    private int prixAchat;
    private Player owner = null;
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

        if (! this.hasOwner()) {
            this.setOwner(joueur, joueur.askBuyProperty(this, plateauComplet));
        } else {
            if (!estHypothequee)
            this.makePay(joueur, plateauComplet);
        }
    }

    public int getPrixAchat(){
        return this.prixAchat;
    }
    

    public Player getOwner() {
        return this.owner;
    }

    public void setOwner(Player joueur, boolean choice) {
        this.owner = joueur;
    }

    public boolean hasOwner() {
        return (! (this.owner == null));
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
            this.owner.bankAccountModify(- (prixAchat/2) - (prixAchat/10));
        }
        //Si elle est normale et on veut l'hypothéquer
        else{
            this.owner.bankAccountModify(this.prixAchat/2);
        }
        estHypothequee = !estHypothequee;
    }


    protected void makePay(Player joueurQuiPaye, Board plateau) throws IsBankruptException{
        if (joueurQuiPaye.equals(this.owner)){ // Le proprio de la case est tombé sur une case à lui
            return;
        }
        else {
            joueurQuiPaye.transaction(this.owner, this.getLoyerAPayer(plateau));
        }
    }

    public abstract int getLoyerAPayer(Board plateau);
}
