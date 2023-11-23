package fr.pantheonsorbonne.miage.game.Monopoly;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.Monopoly.Players.*;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.*;

public class BoardTest {

    @Test
    public void getOwnedPropertiesTest(){
        Player joueur = new VoidBot(0);
        PerfectBoard plateau = new PerfectBoard(joueur);
        CaseAchetable caseRandom =  (CaseAchetable) plateau.getPlateau()[3];
        
        caseRandom.setOwner(joueur, true);
        List<CaseAchetable> listeTest = new ArrayList<>();
        listeTest.add(caseRandom);
        caseRandom = (CaseAchetable) plateau.getPlateau()[5];
        caseRandom.setOwner(joueur, true);
        listeTest.add(caseRandom);

        assertEquals(listeTest, plateau.getOwnedProperties(joueur));
    }
}
