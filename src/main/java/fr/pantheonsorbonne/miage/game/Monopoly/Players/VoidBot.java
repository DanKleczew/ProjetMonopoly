package fr.pantheonsorbonne.miage.game.Monopoly.Players;

import java.util.HashMap;
import java.util.Map;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseAchetable;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.TypePropriete;

public class VoidBot extends Player{

    //Un bot qui ne fait jamais rien, pratique pour les tests

    public VoidBot(int ID) {
        super(ID);
    }

    @Override
    protected Map<TypePropriete, Integer> thinkAboutBuyingHouses(PerfectBoard plateauComplet) {
        return new HashMap<TypePropriete, Integer>();
    }

    @Override
    protected CasePropriete[] thinkAboutHypothequeProprietes(PerfectBoard plateau) {
        return new CasePropriete[0];
    }

    @Override
    protected CasePropriete[] thinkAboutCreatingJails(PerfectBoard plateau) {
        return new CasePropriete[0];
    }

    @Override
    protected Map<TypePropriete, Integer> thinkAboutSellingHouses(PerfectBoard plateauComplet) {
        return new HashMap<TypePropriete, Integer>();
    }

    @Override
    public boolean askGetOutOfJail(PerfectBoard board) {
        return false;
    }

    @Override
    public boolean askBuyProperty(CaseAchetable proprieteLibre, PerfectBoard plateauComplet) {
        return false;
    }

    @Override
    public boolean askRemoveInstantlySquat(CasePropriete ProprieteSquatee, PerfectBoard plateauComplet) {
        return false;
    }
    
}
