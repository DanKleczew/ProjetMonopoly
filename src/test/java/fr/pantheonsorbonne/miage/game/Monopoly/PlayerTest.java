package fr.pantheonsorbonne.miage.game.Monopoly;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.*;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerTest {

    @Test
    public void getNumberSpecificTypePropertyTest() {
        Player Thierry = new VoidBot(1);

        List<CaseAchetable> liste = new ArrayList<>();
        CaseAchetable a = new CaseGare("Gare du Sud");
        CaseAchetable b = new CaseGare("Gare de l'Ouest");
        a.setOwner(Thierry);
        b.setOwner(Thierry);
        liste.addAll(Arrays.asList(a, b));

        assertEquals(2, Thierry.getNumberSpecificTypeProperty(TypePropriete.GARE, liste));
    }

    @Test
    public void hasPlayedTest() {
        Player Thierry = new VoidBot(0);

        assertEquals(false, Thierry.hasPlayed());

        Thierry.switchPlayingStatus();

        assertEquals(true, Thierry.hasPlayed());

        Thierry.switchPlayingStatus();

        assertEquals(false, Thierry.hasPlayed());
    }

    @Test
    public void getIdTest() {
        Player Thierry = new VoidBot(0);

        assertEquals(0, Thierry.getID());
    }

    @Test
    public void timeOutReduction() throws IsBankruptException {
        Player Thierry = new VoidBot(0);
        PerfectBoard plateauFantome = new PerfectBoard(Thierry);

        Thierry.setTimeOut(plateauFantome);
        assertEquals(3, Thierry.getTimeOut());
        assertEquals(10, plateauFantome.getPositionJoueur(Thierry));

        Thierry.timeOutReduction();
        assertEquals(2, Thierry.getTimeOut());

    }

    @Test
    public void sellProprietesTest() throws IsBankruptException {
        Player Thierry = new Dumb(0);

        PerfectBoard plateauFantome = new PerfectBoard(Thierry);

        ((CasePropriete) (plateauFantome.getCaseByIndice(1))).setOwner(Thierry);

        Thierry.thinkAndDo(plateauFantome);
        CasePropriete Belleville = (CasePropriete) (plateauFantome.getCaseByIndice(1));
        assertEquals(true, Belleville.isHypothequed());

    }

    @Test 
    public void throwDiceTest(){
        Player Thierry = new Dumb(0);
        PerfectBoard plateauFantome = new PerfectBoard(Thierry);
        int[] des = Thierry.throwDice(plateauFantome, 0);
        int[] desExpected = new int[]{1, 5};
        assertEquals(true, java.util.Arrays.equals(des, desExpected));
        assertEquals(6, plateauFantome.getSommeDesThisRound());
    }
}
