package fr.pantheonsorbonne.miage.game.Monopoly.Players;

import java.util.List;
import java.util.Map;
import java.util.Random;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseAchetable;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.TypePropriete;

public abstract class Player {

    private int bankAccount = 1500;
    final protected int ID;
    private int timeOut = 0;
    private boolean aJoue = false;

    public Player(int ID) {
        this.ID = ID;
    }

    public boolean hasPlayed(){
        return aJoue;
    }

    public void switchPlayingStatus(){
        aJoue = !aJoue;
    }
    public int getID(){
        return this.ID;
    }
    public void bankAccountModify(int gainOuPerte) throws IsBankruptException {
        if (this.bankAccount + gainOuPerte >= 0) {
            this.bankAccount += gainOuPerte;
        } else {
            throw new IsBankruptException(this);
        }
    }

    public int getBankAccount() {
        return this.bankAccount;
    }

    public void setTimeOut(PerfectBoard plateau) throws IsBankruptException {
        this.timeOut = 3;
        plateau.goToPrison(this);
    }

    public void timeOutReduction() {
        this.timeOut -= 1;
    }

    public void resetTimeOut(boolean b) throws IsBankruptException{
        if (b){
            this.timeOut = 0;
            this.bankAccountModify(-50);
        }
    }

    public abstract boolean askGetOutOfJail(PerfectBoard board);

    public int getTimeOut(){
        return this.timeOut;
    }

    public int[] throwDice(PerfectBoard plateauComplet, int seed) {
        int[] table = new int[2];
        Random aleatoire = new Random(seed);
        table[0] = aleatoire.nextInt(6) + 1;
        table[1] = aleatoire.nextInt(6) + 1;
        plateauComplet.setSommeDesThisRound(table[0] + table[1]);
        return table;
    }

    public int getNumberSpecificTypeProperty(TypePropriete type, List<CaseAchetable> OwnedProperties) {
        // Renvoie le nombre de propriétés de (this) d'un TypePropriete particulier
        int countTypePropiete = 0;
        for (CaseAchetable caseAchete : OwnedProperties) {
            if (caseAchete.getTypeOuCouleur() == type) {
                countTypePropiete++;
            }
        }
        return countTypePropiete;
    }

    // Le Player sur lequel est appelée la méthode doit une sommeAPayer au Player
    // gagnant.
    // Cette méthode s'assure que le perdant paie son dû sans pour autant donner de
    // l'argent en trop en cas de faillite
    // Si par exemple il doit 2000€ mais n'en a que 1200 il ne donnera que 1200 à
    // son adversaire
    public void transaction(Player gagnant, int sommeAPayer) throws IsBankruptException {
        
        if (this.verifMoneyEnough(sommeAPayer)) {

            gagnant.bankAccountModify(sommeAPayer);
        } else {
            gagnant.bankAccountModify(this.getBankAccount());
        }
        this.bankAccountModify(-sommeAPayer);
        
    }

    private boolean verifMoneyEnough(int moneyNeeded) {
        return (this.getBankAccount() >= moneyNeeded);
    }

    public abstract boolean askBuyProperty(CaseAchetable proprieteLibre, PerfectBoard plateauComplet);

    // Renvoie un dictionnaire (potentiellement vide) des couleurs sur lesquelles le
    // joueur veut mettre des maisons et combien
    protected abstract Map<TypePropriete, Integer> thinkAboutBuyingHouses(PerfectBoard plateauComplet);

    // Renvoie un dictionnaire (potentiellement vide) des couleurs sur lesquelles le
    // joueur veut vendre des maisons et combien
    protected abstract Map<TypePropriete, Integer> thinkAboutSellingHouses(PerfectBoard plateauComplet);

    // Renvoie un Array de cases propriétés du joueur que celui ci veut hypothéquer/déshypothéquer
    protected abstract CaseAchetable[] thinkAboutHypothequeProprietes(PerfectBoard plateauComplet);

    // Renvoie un Array de cases propriétés du joueur que celui ci veut transformer
    // en prisons
    protected abstract CasePropriete[] thinkAboutCreatingJails(PerfectBoard plateauComplet);

    public void thinkAndDo(PerfectBoard plateauComplet) throws IsBankruptException {
        plateauComplet.addNumerousHouses(thinkAboutBuyingHouses(plateauComplet));
        plateauComplet.sellNumerousHouses(thinkAboutSellingHouses(plateauComplet));
        this.sellProprietes(thinkAboutHypothequeProprietes(plateauComplet));
        this.transformToJails(thinkAboutCreatingJails(plateauComplet)); 
    }

    private void transformToJails(CasePropriete[] listeProprietesATransformer) {
        for (CasePropriete propriete: listeProprietesATransformer){
            propriete.setAsJail();
        }
    }

    private void sellProprietes(CaseAchetable[] listeProprietesAHypothequer) throws IsBankruptException {
        for (CaseAchetable propriete : listeProprietesAHypothequer){
            propriete.switchHypothequeStatus();
    }
}

    public abstract boolean askRemoveInstantlySquat(CasePropriete ProprieteSquatee, PerfectBoard plateauComplet);
        
}
