package fr.pantheonsorbonne.miage.game.Monopoly;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.Monopoly.Players.*;
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
}
