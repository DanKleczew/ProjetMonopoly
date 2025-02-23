package fr.pantheonsorbonne.miage.game.Monopoly.Cases;

import java.util.Random;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.Board;
import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CasePropriete extends CaseAchetable {
    private int[] echelleDeLoyer;
    private int nombreMaisons = 0;
    private int prixMaisonUnitaire;

    private int toursRestantsSquat = 0;
    private boolean isAJail = false;

    //Constructeur appelé en cas de propriété "basse" (première des MARRON et BLEU, première et deuxième des AUTRES)
    public CasePropriete(String name, int prixAchat, TypePropriete couleur) {
        super(name, prixAchat, couleur);
        this.echelleDeLoyer = couleur.getEchelleDeLoyer();
        this.prixMaisonUnitaire = assignPrixMaisonUnitaire(couleur);
    }

    //Constructeur appelé pour les autres (qui ont des échelles de loyer spécifiques)
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

    public boolean isAJail(){
        return isAJail;
    }

    public void setAsJail(){
        this.isAJail = true;
    }

    public void resetJail(){
        this.isAJail = false;
    }

    @Override
    public int getLoyerAPayer(Board plateauComplet) {
        if (this.isAJail() || this.toursRestantsSquat > 0){
            return 0;
        }
        Player owner = this.getOwner();
        TypePropriete couleur = this.getTypeOuCouleur();
        int aPayer;
        if (couleur.getNbProprieteDeCeType() == owner.getNumberSpecificTypeProperty(couleur,
                plateauComplet.getOwnedProperties(owner)) && nombreMaisons == 0) {
            // Si le nombre de propriétés qu'il existe de cette couleur == nombre de
            // propriétés de cette couleur possédée par l'owner
            // C'est à dire l'owner possède toutes les propriétés de cette couleur
            aPayer = 2 * (this.getEchelleDeLoyer()[0]);
        } else {
            aPayer = this.getEchelleDeLoyer()[this.getNombreMaisons()];
        }
        return aPayer;
    }

    public int getPrixMaisonUnitaire(){
        return this.prixMaisonUnitaire;
    }
    public void setSquat() {
        this.toursRestantsSquat = 8;
    }

    public void removeSquat(boolean playerChoice, PerfectBoard plateau) throws IsBankruptException {
        if (playerChoice){ //Si le joueur décide de payer une entreprise privée
            this.toursRestantsSquat = 0;
            this.getOwner().bankAccountModify(-200);
            
            if (new Random().nextInt(10) == 0) { // Manière simple de simuler "Une chance sur 10"
                this.getOwner().setTimeOut(plateau);
            }
        }
    }

    public void policeJob() {
        if (this.toursRestantsSquat != 0) {
            this.toursRestantsSquat -= 1;
        }
    }

    public int[] getEchelleDeLoyer() {
        return echelleDeLoyer;
    }

    public int getNombreMaisons() {
        return this.nombreMaisons;
    }
    public void resetNombreMaisons(){
        this.nombreMaisons = 0;
    }
    public boolean hasHotel() {
        return this.nombreMaisons == 5;
    }

    public void addHouse() throws IsBankruptException {
        this.getOwner().bankAccountModify(-prixMaisonUnitaire);
        this.nombreMaisons++;
    }

    public void setHousesNoPay(int numberHouses){
        this.nombreMaisons = numberHouses;
    }

    public void sellHouse() throws IsBankruptException {
        this.getOwner().bankAccountModify(prixMaisonUnitaire / 2);
        this.nombreMaisons--;
    }

}
