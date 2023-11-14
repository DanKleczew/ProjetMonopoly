package fr.pantheonsorbonne.miage.game.Monopoly.Players;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import fr.pantheonsorbonne.miage.game.Monopoly.Board;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.Case;
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

    public int[] throwDice() {
        int[] table = new int[2];
        Random aleatoire = new Random();
        table[0] = aleatoire.nextInt(6) + 1;
        table[1] = aleatoire.nextInt(6) + 1;
        return table;
    }

    public List<CaseAchetable> getOwnedProperties() {
        List<CaseAchetable> proprietes = new ArrayList<>();
        for (Case caseActuel : Board.getPlateau()) {
            if (caseActuel instanceof CaseAchetable) {
                CaseAchetable caseProprietaire = (CaseAchetable) caseActuel;
                if (Objects.equals(caseProprietaire.getOwner(), this)) {
                    proprietes.add(caseProprietaire);
                }
            }
        }
        return proprietes;
    }

    public int getNumberSpecificProperty(TypePropriete type, List<CaseAchetable> OwnedProperties) {
        int countTypePropiete = 0;
        for (CaseAchetable caseAchete : OwnedProperties) {
            if (caseAchete.getTypeOuCouleur() == type) {
                countTypePropiete++;
            }
        }
        return countTypePropiete;
    }
}
