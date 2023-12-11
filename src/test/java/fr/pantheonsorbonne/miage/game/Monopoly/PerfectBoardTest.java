package fr.pantheonsorbonne.miage.game.Monopoly;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.*;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.*;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.*;

public class PerfectBoardTest {
    

    

    
    @Test
    void testPickACaisseCard() {
        Player Thierry = new VoidBot(1);
        PerfectBoard plateau = new PerfectBoard(new PlayersManager(Thierry));
        assertEquals(true, plateau.pickACaisseCard() instanceof Card);
    }

    @Test
    void testPickAChanceCard() {
        Player Thierry = new VoidBot(1);
        PerfectBoard plateau = new PerfectBoard(new PlayersManager(Thierry));
        assertEquals(true, plateau.pickAChanceCard() instanceof Card);
    }


    @Test
    void testAddNumerousHouses() throws IsBankruptException {
        Player Thierry = new VoidBot(1);
        PerfectBoard plateau = new PerfectBoard(new PlayersManager(Thierry));
        plateau.getAllColoredProprietes().get(plateau.getAllColoredProprietes().size()-1).setOwner(Thierry);
        plateau.getAllColoredProprietes().get(plateau.getAllColoredProprietes().size()-2).setOwner(Thierry);
        HashMap<TypePropriete, Integer> map = new HashMap<>();
        map.put(TypePropriete.BLEU, 2);
        plateau.addNumerousHouses(map);

        assertEquals(1100, Thierry.getBankAccount());

        assertEquals(1, plateau.getAllColoredProprietes().get(plateau.getAllColoredProprietes().size()-1).getNombreMaisons());
        assertEquals(1, plateau.getAllColoredProprietes().get(plateau.getAllColoredProprietes().size()-2).getNombreMaisons());
    }

    

    

    

   

    @Test 
    public void hasHousesTest() throws IsBankruptException{
        Player t1 = new VoidBot(0);
        PerfectBoard pb = new PerfectBoard(new PlayersManager(t1));

        assertEquals(false, pb.hasHouses(t1));
        pb.getAllColoredProprietes().get(0).setOwner(t1);
        pb.getAllColoredProprietes().get(0).addHouse();
        assertEquals(true, pb.hasHouses(t1));
    }

    @Test 
    public void getLoyerDeBaseProprieteLaPlusChereTest(){
        Player t1 = new VoidBot(0);
        PerfectBoard pb = new PerfectBoard(new PlayersManager(t1));

        pb.getAllColoredProprietes().get(0).setOwner(t1);
        pb.getAllColoredProprietes().get(1).setOwner(t1);
        pb.getAllColoredProprietes().get(0).setHousesNoPay(1);

        assertEquals(4, pb.getLoyerDeBaseProprieteLaPlusChere(t1));
        
    }

    @Test
    public void sellNumerousHousesTest() throws IsBankruptException{
        Player t1 = new VoidBot(0);
        PerfectBoard pb = new PerfectBoard(new PlayersManager(t1));

        pb.getAllColoredProprietes().get(0).setOwner(t1);
        pb.getAllColoredProprietes().get(1).setOwner(t1);
        pb.getAllColoredProprietes().get(0).setHousesNoPay(3);
        pb.getAllColoredProprietes().get(0).setHousesNoPay(3);
        Map<TypePropriete, Integer> map = new HashMap<>();
        map.put(TypePropriete.MARRON, 6);
        pb.sellNumerousHouses(map);
        assertEquals(0, pb.getAllColoredProprietes().get(0).getNombreMaisons());
        assertEquals(1650, t1.getBankAccount());
    }

}
