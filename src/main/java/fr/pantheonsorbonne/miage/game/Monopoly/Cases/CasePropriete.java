package fr.pantheonsorbonne.miage.game.Monopoly.Cases;

import fr.pantheonsorbonne.miage.game.Monopoly.Board;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CasePropriete extends CaseAchetable {
    private int[] echelleDeLoyer;
    private int nombreMaisons = 0;
    private boolean hotel = false;

    public CasePropriete(String name, int prixAchat, TypePropriete couleur) {
        super(name, prixAchat, couleur);
        this.echelleDeLoyer = couleur.getEchelleDeLoyer();

    }

    public CasePropriete(String name, int prixAchat, TypePropriete couleur, int[] echelleDeLoyer) {
        super(name, prixAchat, couleur);
        this.echelleDeLoyer = echelleDeLoyer;
    }

    @Override
    protected void makePay(Player joueurQuiPaye) throws IsBankruptException {
        Player owner = this.getOwner();
        TypePropriete couleur = this.getTypeOuCouleur();

        if (joueurQuiPaye.equals(owner)) { // Le proprio de la case est tombé sur une case à lui
            return;
        } else {
            int aPayer;
            if (couleur.getNbProprieteDeCeType() == owner.getNumberSpecificTypeProperty(couleur, Board.getOwnedProperties(owner)) && nombreMaisons == 0){
                //Si le nombre de propriétés qu'il existe de cette couleur == nombre de propriétés de cette couleur possédée par l'owner
                //C'est à dire l'owner possède toutes les propriétés de cette couleur
                aPayer = 2 * (this.getEchelleDeLoyer()[0]);
            }
            else {
                aPayer = this.getEchelleDeLoyer()[nombreMaisons];
            }
            
            joueurQuiPaye.transaction(owner, aPayer);

        }

    }

    public int[] getEchelleDeLoyer() {
        return echelleDeLoyer;
    }

    public int getNombreMaisons() {
        return this.nombreMaisons;
    }

    public boolean hasHotel() {
        return this.hotel;
    }

}
