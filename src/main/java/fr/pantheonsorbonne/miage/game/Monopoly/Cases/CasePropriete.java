package fr.pantheonsorbonne.miage.game.Monopoly.Cases;

import fr.pantheonsorbonne.miage.game.Monopoly.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CasePropriete extends CaseAchetable {
    private int[] echelleDeLoyer;
    private int nombreMaisons = 0;
    private int prixMaisonUnitaire = 0;

    public CasePropriete(String name, int prixAchat, TypePropriete couleur) {
        super(name, prixAchat, couleur);
        this.echelleDeLoyer = couleur.getEchelleDeLoyer();
        this.prixMaisonUnitaire = assignPrixMaisonUnitaire(couleur);
    }

    public CasePropriete(String name, int prixAchat, TypePropriete couleur, int[] echelleDeLoyer) {
        super(name, prixAchat, couleur);
        this.echelleDeLoyer = echelleDeLoyer;
        this.prixMaisonUnitaire = assignPrixMaisonUnitaire(couleur);
    }

    private int assignPrixMaisonUnitaire(TypePropriete couleur) {
        switch (couleur.ordinal()) {
            case 0:
            case 1:
                return 50;
            case 2:
            case 3:
                return 100;
            case 4:
            case 5:
                return 150;
            case 6:
            case 7:
                return 200;
            default:
                return 0;
        }
    }

    @Override
    protected void makePay(Player joueurQuiPaye, PerfectBoard plateauComplet) throws IsBankruptException {
        Player owner = this.getOwner();
        TypePropriete couleur = this.getTypeOuCouleur();

        if (joueurQuiPaye.equals(owner)) { // Le proprio de la case est tombé sur une case à lui
            return;
        } else {
            int aPayer;
            if (couleur.getNbProprieteDeCeType() == owner.getNumberSpecificTypeProperty(couleur,
                    plateauComplet.getOwnedProperties(owner)) && nombreMaisons == 0) {
                // Si le nombre de propriétés qu'il existe de cette couleur == nombre de
                // propriétés de cette couleur possédée par l'owner
                // C'est à dire l'owner possède toutes les propriétés de cette couleur
                aPayer = 2 * (this.getEchelleDeLoyer()[0]);
            } else {
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
        return this.nombreMaisons == 5;
    }

    public void addHouse() throws IsBankruptException {
        System.out.println(this.prixMaisonUnitaire);
        this.possesseur.bankAccountModify(-prixMaisonUnitaire);
        this.nombreMaisons++;
    }

    public void sellHouse() throws IsBankruptException {
        this.possesseur.bankAccountModify(prixMaisonUnitaire / 2);
        this.nombreMaisons--;
    }

}
