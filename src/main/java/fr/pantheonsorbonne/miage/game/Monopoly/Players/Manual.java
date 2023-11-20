package fr.pantheonsorbonne.miage.game.Monopoly.Players;

import java.util.HashMap;

import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
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
    protected HashMap<TypePropriete, Integer> thinkAboutBuyingHouses() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'thinkAboutHouses'");
    }

    @Override
    protected CasePropriete[] thinkAboutHypothequeProprietes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'thinkAboutSellingProprietes'");
    }

    @Override
    protected CasePropriete[] thinkAboutCreatingJails() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'thinkAboutCreatingJails'");
    }

    @Override
    protected HashMap<TypePropriete, Integer> thinkAboutSellingHouses() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'thinkAboutSellingHouses'");
    }

    @Override
    public boolean askGetOutOfJail() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'askGetOutOfJail'");
    }
    
}
