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

        assertEquals(60, plateauFantome.getAllProprietes().get(0).getPrixAchat());
    }   

    @Test
    public void switchHypothequeStatusTest() throws IsBankruptException{
        Player Thierry = new VoidBot(0);
        PerfectBoard plateauFantome = new PerfectBoard(Thierry);

        plateauFantome.getAllColoredProprietes().get(0).setOwner(Thierry);

        plateauFantome.getAllColoredProprietes().get(0).switchHypothequeStatus();
        assertEquals(true, plateauFantome.getAllColoredProprietes().get(0).isHypothequed());
        assertEquals(1530, Thierry.getBankAccount());

        plateauFantome.getAllColoredProprietes().get(0).switchHypothequeStatus();
        assertEquals(false, plateauFantome.getAllColoredProprietes().get(0).isHypothequed());
        assertEquals(1494, Thierry.getBankAccount());

    }


    @Test 
    public void doCaseEffectTest() throws IsBankruptException{
        Player Thierry = new VoidBot(0);
        Player Didier = new VoidBot(1);
        PerfectBoard plateauFantome = new PerfectBoard(Thierry, Didier);

        plateauFantome.getAllColoredProprietes().get(0).setOwner(Didier);
        

        plateauFantome.getAllColoredProprietes().get(0).doCaseEffect(Thierry, plateauFantome);

        assertEquals(1498, Thierry.getBankAccount());

        plateauFantome.getAllColoredProprietes().get(0).switchHypothequeStatus();
        plateauFantome.getAllColoredProprietes().get(0).doCaseEffect(Thierry, plateauFantome);

        assertEquals(1498, Thierry.getBankAccount());        
    }

    @Test
    public void makePayTest() throws IsBankruptException{
        Player Thierry = new VoidBot(0);
        Player Didier = new VoidBot(0);
        PerfectBoard plateauFantome = new PerfectBoard(Thierry, Didier);
        CaseAchetable propriete = plateauFantome.getAllColoredProprietes().get(11);

        propriete.setOwner(Didier);
        propriete.doCaseEffect(Thierry, plateauFantome);

        assertEquals(1482, Thierry.getBankAccount());
    }

    @Test
    public void setOwnerTest() throws IsBankruptException{
        Player Thierry = new VoidBot(0);
        CaseAchetable proprieteFantome = new CasePropriete("Belleville", 100, TypePropriete.MARRON);
        assertEquals(1500, Thierry.getBankAccount());

        proprieteFantome.setOwner(Thierry);
        assertEquals(Thierry.getBankAccount(), 1400);
    }
}
