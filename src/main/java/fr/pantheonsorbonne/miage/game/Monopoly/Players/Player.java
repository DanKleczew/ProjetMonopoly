package fr.pantheonsorbonne.miage.game.Monopoly.Players;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import fr.pantheonsorbonne.miage.game.Monopoly.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseAchetable;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.TypePropriete;

public abstract class Player {

    private int bankAccount = 1500;
    final protected int ID;
    private int timeOut = 0;    //peut etre protected

    public Player(int ID) {
        this.ID = ID;
    }

    public void bankAccountModify(int gainOuPerte) throws IsBankruptException {
        if (this.bankAccount + gainOuPerte > 0) {
            this.bankAccount += gainOuPerte;
        } else {
            throw new IsBankruptException(this);
        }

    }

    public int getBankAccount() {
        return this.bankAccount;
    }

    public void setTimeOut() {
        this.timeOut = 3;
    }
    public int getTimeOut(){
        return this.timeOut;
    }

    public int[] throwDice(PerfectBoard plateauComplet) {
        int[] table = new int[2];
        Random aleatoire = new Random();
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

    public abstract boolean askBuyProperty();

    // Renvoie un dictionnaire (potentiellement vide) des couleurs sur lesquelles le
    // joueur veut mettre des maisons et combien
    protected abstract HashMap<TypePropriete, Integer> thinkAboutBuyingHouses();

    // Renvoie un dictionnaire (potentiellement vide) des couleurs sur lesquelles le
    // joueur veut vendre des maisons et combien
    protected abstract HashMap<TypePropriete, Integer> thinkAboutSellingHouses();

    // Renvoie un Array de cases propriétés du joueur que celui ci veut hypothéquer
    protected abstract CasePropriete[] thinkAboutHypothequeProprietes();

    // Renvoie un Array de cases propriétés du joueur que celui ci veut transformer
    // en prisons
    protected abstract CasePropriete[] thinkAboutCreatingJails();

    public void thinkAndDo(PerfectBoard plateauComplet) throws IsBankruptException {
        // TODO : Make them do something
        plateauComplet.addNumerousHouses(thinkAboutBuyingHouses());
        plateauComplet.sellNumerousHouses(thinkAboutSellingHouses());
        this.sellProprietes(thinkAboutHypothequeProprietes());
        thinkAboutCreatingJails();
    }

    private void sellProprietes(CasePropriete[] listeProprietesAHypothequer) throws IsBankruptException {
        for (CasePropriete propriete : listeProprietesAHypothequer){
            propriete.switchHypothequeStatus();
    }
}
}
