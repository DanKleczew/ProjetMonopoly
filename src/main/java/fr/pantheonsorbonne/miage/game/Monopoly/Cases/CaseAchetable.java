package fr.pantheonsorbonne.miage.game.Monopoly.Cases;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.Board;
import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public abstract class CaseAchetable extends Case {

    private final int prixAchat;
    private final TypePropriete typeOuCouleur;
    private Player owner = null;
    private boolean estHypothequee = false;

    public CaseAchetable(String name, int prixAchat, TypePropriete typeOuCouleur) {
        super(name);
        this.prixAchat = prixAchat;
        this.typeOuCouleur = typeOuCouleur;
    }

    @Override
    public void doCaseEffect(Player joueur, PerfectBoard plateauComplet) throws IsBankruptException {
        super.doCaseEffect(joueur, plateauComplet);

        if (this.hasOwner() && !estHypothequee)
        this.makePay(joueur, plateauComplet);
        //Potentiellement 0 si le joueur est le proprio, voir makePay plus bas
    }

    public int getPrixAchat(){
        return this.prixAchat;
    }
    

    public Player getOwner() {
        return this.owner;
    }

    public void setOwner(Player joueur)  {
        this.owner = joueur;
    }

    public void resetOwner(){
        this.owner = null;
    }

    public void buyThePropriete(Player joueur, boolean choice) throws IsBankruptException{
        if (choice){
            joueur.bankAccountModify(-this.prixAchat);
            this.setOwner(joueur);
        }
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

    public void switchHypothequeStatusFree(){
        estHypothequee = ! estHypothequee;
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
