package fr.pantheonsorbonne.miage.game.Monopoly.Players;

import java.util.HashMap;

import fr.pantheonsorbonne.miage.game.Monopoly.Cases.Case;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.TypePropriete;

public class Manual extends Player{

    public Manual(int ID) {
        super(ID);
    }

    @Override
    public boolean askBuyProperty() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'askBuyProperty'");
    }

    @Override
    protected HashMap<TypePropriete, Integer> thinkAboutHouses() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'thinkAboutHouses'");
    }

    @Override
    protected Case[] thinkAboutSellingProprietes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'thinkAboutSellingProprietes'");
    }

    @Override
    protected Case[] thinkAboutCreatingJails() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'thinkAboutCreatingJails'");
    }
    
}
