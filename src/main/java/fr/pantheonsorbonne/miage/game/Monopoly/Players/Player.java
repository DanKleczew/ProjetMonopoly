package fr.pantheonsorbonne.miage.game.Monopoly.Players;

import java.util.Random;

public abstract class Player {

    protected int bankAccount = 1500;
    final protected int ID;
    protected int timeOut = 0;

    public Player(int ID){
        this.ID = ID;
    }

    public void bankAccountModify(int gainOuPerte) throws IsBankruptException {
        if (this.bankAccount + gainOuPerte > 0){
            this.bankAccount += gainOuPerte;
        }
        else{
            throw new IsBankruptException();
        }
        
    }

    public void setTimeOut(){
        this.timeOut = 3;
    }

    public abstract boolean askBuyProperty();


    public int[] throwDice(){
        int [] table = new int[2];
        Random aleatoire = new Random();
        table[0] = aleatoire.nextInt(6) + 1;
        table[1] = aleatoire.nextInt(6) + 1;
        return table;
    }
}
