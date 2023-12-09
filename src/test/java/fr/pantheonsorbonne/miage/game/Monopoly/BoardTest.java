package fr.pantheonsorbonne.miage.game.Monopoly;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.Monopoly.Players.*;
import fr.pantheonsorbonne.miage.game.Monopoly.Boards.Board;
import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.*;

public class BoardTest {

    @Test
    public void getOwnedPropertiesTest() throws IsBankruptException{
        Player joueur = new VoidBot(0);
        PerfectBoard plateau = new PerfectBoard(joueur);
        CaseAchetable caseRandom =  plateau.getAllProprietes().get(1);
        
        caseRandom.setOwner(joueur);
        List<CaseAchetable> listeTest = new ArrayList<>();
        listeTest.add(caseRandom);
        caseRandom = plateau.getAllProprietes().get(3);
        caseRandom.setOwner(joueur);
        listeTest.add(caseRandom);

        assertEquals(listeTest, plateau.getOwnedProperties(joueur));
    }

    @Test 
    public void setSpecificPositionTest(){
        Player Thierry = new VoidBot(0);
        Board plateauFantome = new PerfectBoard(Thierry);
        assertEquals(0, plateauFantome.getPositionJoueur(Thierry));
        plateauFantome.setSpecificPosition(Thierry, 12);
        assertEquals(12, plateauFantome.getPositionJoueur(Thierry));
    }

    @Test 
    public void getNombrePrisonsTest(){
        PerfectBoard plateauFantome = new PerfectBoard();

        ((CasePropriete) plateauFantome.getCaseByName("Boulevard de Belleville")).setAsJail();

        assertEquals(1, plateauFantome.getNombrePrisons());
    }

    @Test
    public void getSommeTotaleLoyerActuelleTest(){
        PerfectBoard plateauFantome = new PerfectBoard();

        ((CasePropriete) plateauFantome.getCaseByIndice(1)).setOwner(new VoidBot(0));
        ((CasePropriete) plateauFantome.getCaseByIndice(1)).setHousesNoPay(3);
        ((CasePropriete) plateauFantome.getCaseByIndice(6)).setOwner(new VoidBot(0));
        ((CasePropriete) plateauFantome.getCaseByIndice(6)).setHousesNoPay(3);

        assertEquals(360, plateauFantome.getSommeTotaleLoyerActuelle());

    }

    @Test
    public void policeDoYourJobTest(){
        PerfectBoard plateauFantome = new PerfectBoard();
        ((CasePropriete) plateauFantome.getCaseByIndice(1)).setSquat();
        ((CasePropriete) plateauFantome.getCaseByIndice(1)).setOwner(new VoidBot(0));

        plateauFantome.policeDoYourJob();

        assertEquals(0, ((CasePropriete) plateauFantome.getCaseByIndice(1)).getLoyerAPayer(plateauFantome));

        for (int i = 0 ; i<7;i++)
            plateauFantome.policeDoYourJob();

        assertEquals(true, ((CasePropriete) plateauFantome.getCaseByIndice(1)).getLoyerAPayer(plateauFantome)>0);
    }

    @Test
    public void getRandomOwnedProprieteTest(){
        Player Thierry = new VoidBot(0);
        PerfectBoard plateauFantome = new PerfectBoard();
        ((CasePropriete) plateauFantome.getCaseByIndice(1)).setOwner(Thierry);

        assertEquals("Boulevard de Belleville", plateauFantome.getRandomOwnedPropriete().toString());
    }

    @Test 
    public void renteDesPrisonsTest() throws IsBankruptException{
        Player Thierry = new VoidBot(0);
        PerfectBoard plateauFantome = new PerfectBoard();
        ((CasePropriete) plateauFantome.getCaseByIndice(39)).setOwner(Thierry);
        ((CasePropriete) plateauFantome.getCaseByIndice(39)).setAsJail();
        plateauFantome.renteDesPrisons();
        assertEquals(1511, Thierry.getBankAccount());
    }
}
