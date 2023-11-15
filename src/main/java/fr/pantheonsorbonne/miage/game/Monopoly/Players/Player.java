package fr.pantheonsorbonne.miage.game.Monopoly.Players;

import java.util.List;
import java.util.Random;

import fr.pantheonsorbonne.miage.game.Monopoly.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseAchetable;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.TypePropriete;

public abstract class Player {

    private int bankAccount = 1500;
    final protected int ID;
    protected int timeOut = 0;

    public Player(int ID) {
        this.ID = ID;
    }

    public void bankAccountModify(int gainOuPerte) throws IsBankruptException {
        if (this.bankAccount + gainOuPerte > 0) {
            this.bankAccount += gainOuPerte;
        } else {
            throw new IsBankruptException();
        }

    }

    public int getBankAccount() {
        return this.bankAccount;
    }

    public void setTimeOut() {
        this.timeOut = 3;
    }

    public abstract boolean askBuyProperty();

    public int[] throwDice(PerfectBoard plateauComplet) {
        int[] table = new int[2];
        Random aleatoire = new Random();
        table[0] = aleatoire.nextInt(6) + 1;
        table[1] = aleatoire.nextInt(6) + 1;
        plateauComplet.setSommeDesThisRound(table[0] + table[1]);
        return table;
    }

    public int getNumberSpecificTypeProperty(TypePropriete type, List<CaseAchetable> OwnedProperties) { 
        //Renvoie le nombre de propriétés de (this) d'un TypePropriete particulier
        int countTypePropiete = 0;
        for (CaseAchetable caseAchete : OwnedProperties) {
            if (caseAchete.getTypeOuCouleur() == type) {
                countTypePropiete++;
            }
        }
        return countTypePropiete;
    }



    //Le Player sur lequel est appelée la méthode doit une sommeAPayer au Player gagnant.
    //Cette méthode s'assure que le perdant paie son dû sans pour autant donner de l'argent en trop en cas de faillite
    //Si par exemple il doit 2000€ mais n'en a que 1200 il ne donnera que 1200 à son adversaire
    public void transaction(Player gagnant, int sommeAPayer) throws IsBankruptException{
        if (this.verifMoneyEnough(sommeAPayer)){
                
            gagnant.bankAccountModify(sommeAPayer);
        }
        else {
            gagnant.bankAccountModify(this.getBankAccount());
        }
        this.bankAccountModify(-sommeAPayer);
    }

    private boolean verifMoneyEnough(int moneyNeeded) {
        return (this.getBankAccount() >= moneyNeeded);
    }
}
