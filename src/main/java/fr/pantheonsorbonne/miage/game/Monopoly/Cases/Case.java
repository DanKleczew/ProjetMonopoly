package fr.pantheonsorbonne.miage.game.Monopoly.Cases;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public abstract class Case {

    protected String nameCase;

    public Case(String name){
        this.nameCase = name;
    }

    @Override
    public String toString(){
        return nameCase;
    }
    
    public void doCaseEffect(Player joueur, PerfectBoard plateauComplet) throws IsBankruptException{
        System.out.println(plateauComplet.getCouleur(joueur) + this.toString() + "\u001B[0m");
    }
}
