package fr.pantheonsorbonne.miage.game.Monopoly.Players;

import java.util.HashMap;
import java.util.Map;

import fr.pantheonsorbonne.miage.game.Monopoly.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseAchetable;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.TypePropriete;

public class StrangeBot extends Player {
    private boolean [] choix ;
    private int [][]table;
    public StrangeBot(int ID) {
        super(ID);
        // TODO Auto-generated constructor stub
        this.table = new int[10][10];
        
    }

    @Override
    public boolean askGetOutOfJail() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'askGetOutOfJail'");
    }

    @Override
    public boolean askBuyProperty(CaseAchetable proprieteLibre, PerfectBoard plateauComplet) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'askBuyProperty'");
    }

    @Override
    protected Map<TypePropriete, Integer> thinkAboutBuyingHouses(PerfectBoard plateauComplet) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'thinkAboutBuyingHouses'");
    }

    @Override
    protected Map<TypePropriete, Integer> thinkAboutSellingHouses() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'thinkAboutSellingHouses'");
    }

    @Override
    protected CaseAchetable[] thinkAboutHypothequeProprietes(PerfectBoard plateauComplet) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'thinkAboutHypothequeProprietes'");
    }

    @Override
    protected void thinkAboutCreatingJails() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'thinkAboutCreatingJails'");
    }

    @Override
    public boolean askRemoveInstantlySquat(CasePropriete ProprieteSquatee, PerfectBoard plateauComplet) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'askRemoveInstantlySquat'");
    }

    public static int[][][][][][] bestmove() {
        int[][][][][][] tablebestmove = new int[11][11][11][11][11][11];
        int length = tablebestmove.length;
        


        

        return tablebestmove;
    }

    public static HashMap test(){
        HashMap<Integer , int []> test2 = new HashMap<Integer, int []>();
        return test2;
    } 

    public static void main(String... args) {
        StrangeBot bot1 = new StrangeBot(1);



    }

}
