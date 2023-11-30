package fr.pantheonsorbonne.miage.game.Monopoly;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.*;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.*;

public class CaseAchetableTest {
    
    @Test
    public void getPrixAchatTest(){

        PerfectBoard plateauFantome = new PerfectBoard();

        assertEquals(60, plateauFantome.allProprietes.get(0).getPrixAchat());
    }   

    @Test
    public void switchHypothequeStatusTest() throws IsBankruptException{
        Player Thierry = new VoidBot(0);
        PerfectBoard plateauFantome = new PerfectBoard(Thierry);

        plateauFantome.allColoredProprietes.get(0).setOwner(Thierry, true);

        plateauFantome.allColoredProprietes.get(0).switchHypothequeStatus();
        assertEquals(true, plateauFantome.allColoredProprietes.get(0).isHypothequed());
        assertEquals(1530, Thierry.getBankAccount());

        plateauFantome.allColoredProprietes.get(0).switchHypothequeStatus();
        assertEquals(false, plateauFantome.allColoredProprietes.get(0).isHypothequed());
        assertEquals(1494, Thierry.getBankAccount());

    }


    @Test 
    public void doCaseEffectTest() throws IsBankruptException{
        Player Thierry = new VoidBot(0);
        Player Didier = new VoidBot(1);
        PerfectBoard plateauFantome = new PerfectBoard(Thierry, Didier);

        plateauFantome.allColoredProprietes.get(0).setOwner(Didier, true);
        

        plateauFantome.allColoredProprietes.get(0).doCaseEffect(Thierry, plateauFantome);

        assertEquals(1498, Thierry.getBankAccount());

        plateauFantome.allColoredProprietes.get(0).switchHypothequeStatus();
        plateauFantome.allColoredProprietes.get(0).doCaseEffect(Thierry, plateauFantome);

        assertEquals(1498, Thierry.getBankAccount());        
    }

    @Test
    public void makePayTest() throws IsBankruptException{
        Player Thierry = new VoidBot(0);
        Player Didier = new VoidBot(0);
        PerfectBoard plateauFantome = new PerfectBoard(Thierry, Didier);
        CaseAchetable propriete = plateauFantome.allColoredProprietes.get(11);

        propriete.setOwner(Didier, true);
        propriete.doCaseEffect(Thierry, plateauFantome);

        assertEquals(1482, Thierry.getBankAccount());
    }
}
