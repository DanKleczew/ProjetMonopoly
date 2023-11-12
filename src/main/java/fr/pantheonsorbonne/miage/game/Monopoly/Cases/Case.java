package fr.pantheonsorbonne.miage.game.Monopoly.Cases;

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
    
    protected abstract void doCaseEffect(Player joueur) throws IsBankruptException;
}
