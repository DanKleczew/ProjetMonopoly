package fr.pantheonsorbonne.miage.game.Monopoly;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.*;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.*;

public class CasesSimplesTest {
    
    @Test
    public void caseEffectNeutreTest() throws IsBankruptException{
        CaseNeutre a = new CaseNeutre("Départ");
    
        assertDoesNotThrow(()->a.doCaseEffect(new VoidBot(0), new PerfectBoard(new VoidBot(0))));
    }

    @Test
    public void caseEffectPrisonTest() throws IsBankruptException{
        CaseGoToPrison jail = new CaseGoToPrison("prison");
        Player Thierry = new VoidBot(0);
        PerfectBoard plateaufantome = new PerfectBoard(Thierry);

        jail.doCaseEffect(Thierry, plateaufantome);

        assertEquals(3, Thierry.getTimeOut());
        assertEquals(10, plateaufantome.getPositionJoueur(Thierry));
        assertEquals(1500, Thierry.getBankAccount());
    }

    @Test 
    public void caseTaxeTest() throws IsBankruptException{
        CaseTaxe taxe = new CaseTaxe("Taxe", 100);
        Player Thierry = new VoidBot(0);

        taxe.doCaseEffect(Thierry, new PerfectBoard(Thierry));

        assertEquals(1400, Thierry.getBankAccount());
    }

    @Test
    public void caseGaregetLoyerAPayerTest(){
        Player Thierry = new VoidBot(0);
        PerfectBoard plateauFantome = new PerfectBoard(Thierry);

        plateauFantome.getAllProprietes().get(2).setOwner(Thierry);
        
        assertEquals(25, plateauFantome.getAllProprietes().get(2).getLoyerAPayer(plateauFantome));

        plateauFantome.getAllProprietes().get(10).setOwner(Thierry);

        assertEquals(50, plateauFantome.getAllProprietes().get(2).getLoyerAPayer(plateauFantome));

        plateauFantome.getAllProprietes().get(17).setOwner(Thierry);
        plateauFantome.getAllProprietes().get(25).setOwner(Thierry);

        assertEquals(200, plateauFantome.getAllProprietes().get(2).getLoyerAPayer(plateauFantome));
    }

    @Test
    public void caseCompagniegetLoyerAPayerTest(){
        Player Thierry = new VoidBot(0);
        PerfectBoard plateauFantome = new PerfectBoard(Thierry);
        Thierry.throwDice(plateauFantome, 0);
        plateauFantome.getAllProprietes().get(7).setOwner(Thierry);
        assertEquals(24, plateauFantome.getAllProprietes().get(7).getLoyerAPayer(plateauFantome));

        plateauFantome.getAllProprietes().get(20).setOwner(Thierry);
        assertEquals(60, plateauFantome.getAllProprietes().get(7).getLoyerAPayer(plateauFantome));

    }

    @Test
    public void caseCardTest() throws IsBankruptException{
        Player Thierry = new VoidBot(0);
        PerfectBoard plateauFantome = new PerfectBoard(Thierry);

        
        assertDoesNotThrow(()->plateauFantome.getCaseByIndice(2).doCaseEffect(Thierry, plateauFantome));
        assertDoesNotThrow(()->plateauFantome.getCaseByIndice(7).doCaseEffect(Thierry, plateauFantome));
    }
}
