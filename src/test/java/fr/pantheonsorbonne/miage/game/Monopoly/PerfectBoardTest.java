package fr.pantheonsorbonne.miage.game.Monopoly;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.*;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.*;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.*;

public class PerfectBoardTest {
    @Test
    void testGetListeJoueurs() {
        // getter
    }

    @Test
    void testGetNextPlayer() {
        // getter
    }

    @Test
    void testGetSommeDesThisRound() {
        // Player Thierry = new Manual(1);
        // PerfectBoard plateau = new PerfectBoard();
        // hmm c'est du random donc peut pas vraiment tester ...
    }

    @Test
    void testIsGameFinished() throws IsBankruptException {
        Player Thierry = new VoidBot(1);
        PerfectBoard plateau = new PerfectBoard(Thierry);
        assertEquals(true, plateau.isGameFinished());
    }

    @Test
    void testPickACaisseCard() {
        Player Thierry = new VoidBot(1);
        PerfectBoard plateau = new PerfectBoard(Thierry);
        assertEquals(true, plateau.pickACaisseCard() instanceof Card);
    }

    @Test
    void testPickAChanceCard() {
        Player Thierry = new VoidBot(1);
        PerfectBoard plateau = new PerfectBoard(Thierry);
        assertEquals(true, plateau.pickAChanceCard() instanceof Card);
    }

    @Test
    void testSetSommeDesThisRound() {
        //
    }

    @Test
    void testAddNumerousHouses() throws IsBankruptException {
        Player Thierry = new VoidBot(1);
        PerfectBoard plateau = new PerfectBoard();
        plateau.getAllColoredProprietes().get(plateau.getAllColoredProprietes().size()-1).setOwner(Thierry);
        plateau.getAllColoredProprietes().get(plateau.getAllColoredProprietes().size()-2).setOwner(Thierry);
        HashMap<TypePropriete, Integer> map = new HashMap<>();
        map.put(TypePropriete.BLEU, 2);
        plateau.addNumerousHouses(map);

        assertEquals(1100, Thierry.getBankAccount());

        assertEquals(1, plateau.getAllColoredProprietes().get(plateau.getAllColoredProprietes().size()-1).getNombreMaisons());
        assertEquals(1, plateau.getAllColoredProprietes().get(plateau.getAllColoredProprietes().size()-2).getNombreMaisons());
    }
}
