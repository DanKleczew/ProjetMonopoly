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

    @Test 
    public void getPlayerByIDTest(){
        Player Thierry = new VoidBot(0);
        PerfectBoard pb = new PerfectBoard(Thierry);

        assertEquals(Thierry, pb.getPlayerByID(0));
    }

    @Test 
    public void deletePlayer() throws IsBankruptException{
        Player t1 = new VoidBot(0);
        Player t2 = new VoidBot(1);
        Player t3 = new VoidBot(2);

        PerfectBoard pb = new PerfectBoard(t1, t2, t3);

        pb.getAllColoredProprietes().get(0).setOwner(t3);
        pb.getAllColoredProprietes().get(0).switchHypothequeStatus();
        pb.getAllColoredProprietes().get(0).setHousesNoPay(5);

        assertEquals(t3, pb.getAllColoredProprietes().get(0).getOwner());
        pb.deletePlayer(new IsBankruptException(t3));

        assertEquals(false,  pb.getAllColoredProprietes().get(0).isHypothequed());
        assertEquals(false,  pb.getAllColoredProprietes().get(0).hasOwner());
        assertEquals(pb.getAllColoredProprietes().get(0).getNombreMaisons(), 0);
    }

    @Test 
    public void getNextPlayerTest(){
        Player t1 = new VoidBot(0);
        Player t2 = new VoidBot(1);
        Player t3 = new VoidBot(2);

        PerfectBoard pb = new PerfectBoard(t1, t2, t3);

        assertEquals(t1, pb.getNextPlayer());
        assertEquals(t2, pb.getNextPlayer());
        assertEquals(t3, pb.getNextPlayer());
        assertEquals(t1, pb.getNextPlayer());
    }

    @Test 
    public void resetPlayingStatusAllPlayersTest(){
        Player t1 = new VoidBot(0);
        t1.switchPlayingStatus();

        PerfectBoard pb = new PerfectBoard(t1);

        pb.resetPlayingStatusAllPlayers();

        assertEquals(false, t1.hasPlayed());
    }

    @Test 
    public void hasHousesTest() throws IsBankruptException{
        Player t1 = new VoidBot(0);
        PerfectBoard pb = new PerfectBoard(t1);

        assertEquals(false, pb.hasHouses(t1));
        pb.getAllColoredProprietes().get(0).setOwner(t1);
        pb.getAllColoredProprietes().get(0).addHouse();
        assertEquals(true, pb.hasHouses(t1));
    }

    @Test 
    public void getLoyerDeBaseProprieteLaPlusChereTest(){
        Player t1 = new VoidBot(0);
        PerfectBoard pb = new PerfectBoard(t1);

        pb.getAllColoredProprietes().get(0).setOwner(t1);
        pb.getAllColoredProprietes().get(1).setOwner(t1);
        pb.getAllColoredProprietes().get(0).setHousesNoPay(1);

        assertEquals(4, pb.getLoyerDeBaseProprieteLaPlusChere(t1));
        
    }

    @Test
    public void sellNumerousHousesTest() throws IsBankruptException{
        Player t1 = new VoidBot(0);
        PerfectBoard pb = new PerfectBoard(t1);

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
