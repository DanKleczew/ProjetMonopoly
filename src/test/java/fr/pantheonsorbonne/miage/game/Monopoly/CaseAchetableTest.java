package fr.pantheonsorbonne.miage.game.Monopoly;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.*;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.*;

public class CaseAchetableTest {
    
    @Test
    public void getPrixAchatTest(){

        PerfectBoard plateauFantome = new PerfectBoard(new PlayersManager());

        assertEquals(60, plateauFantome.getAllProprietes().get(0).getPrixAchat());
    }   

    @Test
    public void switchHypothequeStatusTest() throws IsBankruptException{
        Player Thierry = new VoidBot(0);
        PerfectBoard plateauFantome = new PerfectBoard(new PlayersManager(Thierry));

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
        PlayersManager liste = new PlayersManager(Thierry, Didier);
        PerfectBoard plateauFantome = new PerfectBoard(liste);

        plateauFantome.getAllColoredProprietes().get(0).setOwner(Didier);
        

        plateauFantome.getAllColoredProprietes().get(0).doCaseEffect(Thierry, plateauFantome );

        assertEquals(1498, Thierry.getBankAccount());

        plateauFantome.getAllColoredProprietes().get(0).switchHypothequeStatus();
        plateauFantome.getAllColoredProprietes().get(0).doCaseEffect(Thierry, plateauFantome);

        assertEquals(1498, Thierry.getBankAccount());        
    }

    @Test
    public void makePayTest() throws IsBankruptException{
        Player Thierry = new VoidBot(0);
        Player Didier = new VoidBot(0);
        PlayersManager liste = new PlayersManager(Thierry, Didier);
        PerfectBoard plateauFantome = new PerfectBoard(liste);
        CaseAchetable propriete = plateauFantome.getAllColoredProprietes().get(11);

        propriete.setOwner(Didier);
        propriete.doCaseEffect(Thierry, plateauFantome );
        propriete.doCaseEffect(Didier, plateauFantome);

        assertEquals(1482, Thierry.getBankAccount());
        assertEquals(1500 + (1500-1482), Didier.getBankAccount());
    }

    @Test
    public void buyTheProprieteTest() throws IsBankruptException{
        Player Thierry = new VoidBot(0);
        CaseAchetable proprieteFantome = new CasePropriete("Belleville", 100, TypePropriete.MARRON);
        assertEquals(1500, Thierry.getBankAccount());

        proprieteFantome.buyThePropriete(Thierry, true);
        assertEquals(1400, Thierry.getBankAccount());
    }

    @Test 
    public void resetOwnerTest(){
        Player Thierry = new VoidBot(0);
        CaseAchetable proprieteFantome = new CasePropriete("Belleville", 100, TypePropriete.MARRON);
        proprieteFantome.setOwner(Thierry);
        assertEquals(Thierry, proprieteFantome.getOwner());
        proprieteFantome.resetOwner();
        assertEquals(null, proprieteFantome.getOwner());
    }

    @Test
    public void switchHypothequeStatusFree(){
        CaseAchetable proprieteFantome = new CasePropriete("Belleville", 100, TypePropriete.MARRON);
        Player Thierry = new VoidBot(0);

        proprieteFantome.switchHypothequeStatusFree();
        assertEquals(true, proprieteFantome.isHypothequed());
        assertEquals(1500, Thierry.getBankAccount());

        proprieteFantome.switchHypothequeStatusFree();
        assertEquals(false, proprieteFantome.isHypothequed());
    }
}
