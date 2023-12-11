package fr.pantheonsorbonne.miage.game.Monopoly;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.PlayersManager;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.VoidBot;

public class PlayersManagerTest {
    
     @Test 
    public void resetPlayingStatusAllPlayersTest(){
        Player t1 = new VoidBot(0);
        t1.switchPlayingStatus();
        PlayersManager liste = new PlayersManager(t1);

        liste.resetPlayingStatusAllPlayers();

        assertEquals(false, t1.hasPlayed());
    }

    @Test 
    public void getNextPlayerTest(){
        Player t1 = new VoidBot(0);
        Player t2 = new VoidBot(1);
        Player t3 = new VoidBot(2);

        PlayersManager pm = new PlayersManager(t1, t2, t3);

        assertEquals(t1, pm.getNextPlayer());
        assertEquals(t2, pm.getNextPlayer());
        assertEquals(t3, pm.getNextPlayer());
        assertEquals(t1, pm.getNextPlayer());
    }

    @Test 
    public void deletePlayer() throws IsBankruptException{
        Player t1 = new VoidBot(0);
        Player t2 = new VoidBot(1);
        Player t3 = new VoidBot(2);
        Player t4 = new VoidBot(3);

        PlayersManager pm = new PlayersManager(t1, t2, t3, t4);
        PerfectBoard pb = new PerfectBoard(pm);

        pb.getAllColoredProprietes().get(0).setOwner(t3);
        pb.getAllColoredProprietes().get(0).switchHypothequeStatus();
        pb.getAllColoredProprietes().get(0).setHousesNoPay(5);

        assertEquals(t3, pb.getAllColoredProprietes().get(0).getOwner());
        pm.deletePlayer(new IsBankruptException(t3), pb);

        assertEquals(false,  pb.getAllColoredProprietes().get(0).isHypothequed());
        assertEquals(false,  pb.getAllColoredProprietes().get(0).hasOwner());
        assertEquals(pb.getAllColoredProprietes().get(0).getNombreMaisons(), 0);

        pb.getAllColoredProprietes().get(0).setOwner(t2);
        pb.getAllColoredProprietes().get(0).switchHypothequeStatus();
        pb.getAllColoredProprietes().get(0).setHousesNoPay(5);

        
        IsBankruptException e = new IsBankruptException(t2);
        e.setGagnant(t1);
        pm.deletePlayer(e, pb);

        assertEquals(t1, pb.getAllColoredProprietes().get(0).getOwner());
        assertEquals(0, pb.getAllColoredProprietes().get(0).getNombreMaisons());
        assertEquals(false,  pb.getAllColoredProprietes().get(0).isHypothequed());
    }

    @Test 
    public void getPlayerByIDTest(){
        Player Thierry = new VoidBot(0);
        PlayersManager pm = new PlayersManager(Thierry);

        assertEquals(Thierry, pm.getPlayerByID(0));
    }

    @Test
    void testIsGameFinished() throws IsBankruptException {
        Player Thierry = new VoidBot(1);
        PlayersManager pm = new PlayersManager(Thierry);
        assertEquals(true, pm.isGameFinished());
    }

    
    @Test
    void testGetListeJoueurs() {
        Player Thierry = new VoidBot(1);
        Player Didier = new VoidBot(0);
        PlayersManager pm = new PlayersManager(Thierry, Didier);
        Deque<Player> dq = new ArrayDeque<>();
        dq.offer(Thierry); 
        dq.offer(Didier);
        
        assertArrayEquals(dq.toArray(), pm.getListeJoueurs().toArray());
    }

}
