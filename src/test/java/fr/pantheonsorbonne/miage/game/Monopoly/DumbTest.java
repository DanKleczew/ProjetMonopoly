package fr.pantheonsorbonne.miage.game.Monopoly;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.*;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.*;

public class DumbTest {

    @Test
    public void askGetOutOfJailTest() throws IsBankruptException {
        Player Thierry = new Dumb(0);
        assertEquals(true, Thierry.askGetOutOfJail());

        Thierry.bankAccountModify(-1450);
        assertEquals(false, Thierry.askGetOutOfJail());
    }

    @Test
    public void askBuyProperty() throws IsBankruptException {
        Player Thierry = new Dumb(0);
        PerfectBoard plateauFantome = new PerfectBoard(Thierry);
        assertEquals(true, Thierry.askBuyProperty((CaseAchetable) plateauFantome.plateau[1], plateauFantome));
    }

    @Test
    public void thinkAboutBuyingHousesTest() throws IsBankruptException {
        Player Thierry = new Dumb(0);
        Player Didier = new VoidBot(0);

        PerfectBoard plateauFantome = new PerfectBoard(Thierry, Didier);

        plateauFantome.allColoredProprietes.get(20).setOwner(Thierry, true);
        plateauFantome.allColoredProprietes.get(21).setOwner(Thierry, true);

        plateauFantome.allColoredProprietes.get(0).setOwner(Didier, false);

        Thierry.thinkAndDo(plateauFantome);

        assertEquals(1, plateauFantome.allColoredProprietes.get(20).getNombreMaisons());

        Thierry.bankAccountModify(10000);
        Map<TypePropriete, Integer> listeDeSouhaits = new HashMap<>();
        listeDeSouhaits.put(TypePropriete.BLEU, 9);

        plateauFantome.addNumerousHouses(listeDeSouhaits);

        Thierry.thinkAndDo(plateauFantome);

        assertEquals(5, plateauFantome.allColoredProprietes.get(20).getNombreMaisons());

        for (int i = 0; i < 10; i++) {
            plateauFantome.houseBreak(Thierry);
        }

        Thierry.bankAccountModify(-9500);

        Thierry.thinkAndDo(plateauFantome);

        assertEquals(0, plateauFantome.allColoredProprietes.get(20).getNombreMaisons());
    }

    @Test
    public void thinkAboutHypothequeProprietes() throws IsBankruptException {
        Player Thierry = new Dumb(0);
        PerfectBoard plateauFantome = new PerfectBoard(Thierry);

        List<CaseAchetable> allProprietes = plateauFantome.allProprietes;
        allProprietes.get(1).setOwner(Thierry, true);
        allProprietes.get(16).setOwner(Thierry, true);

        Thierry.thinkAndDo(plateauFantome);

        assertEquals(false, allProprietes.get(1).isHypothequed());
        assertEquals(false, allProprietes.get(16).isHypothequed());

        Player Didier = new VoidBot(0);
        allProprietes.get(1).setOwner(Didier, true);

        Thierry.thinkAndDo(plateauFantome);

        assertEquals(false, allProprietes.get(1).isHypothequed());
        assertEquals(false, allProprietes.get(16).isHypothequed());

    }

    @Test 
    public void askRemoveInstantlySquatTest() throws IsBankruptException{
        Player Thierry = new Dumb(0);
        PerfectBoard plateauFantome = new PerfectBoard(Thierry);

        assertEquals(true, Thierry.askRemoveInstantlySquat(plateauFantome.allColoredProprietes.get(0), plateauFantome));

        Thierry.bankAccountModify(-1500);

        assertEquals(false, Thierry.askRemoveInstantlySquat(plateauFantome.allColoredProprietes.get(0), plateauFantome));
    }
}
