package fr.pantheonsorbonne.miage.game.Monopoly;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.Monopoly.Cards.Card;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.Deck;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.DeckCaisse;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.DeckChance;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.Case;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.TypePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Manual;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class PerfectBoardTest {
    @Test
    void testGetListeJoueurs() {
        //getter
    }

    @Test
    void testGetNextPlayer() {
        //getter
    }

    @Test
    void testGetSommeDesThisRound() {
        Player Thierry = new Manual(1);
        PerfectBoard plateau = new PerfectBoard();
        // hmm c'est du random donc peut pas vraiment tester ...
    }

    @Test
    void testIsGameFinished() throws IsBankruptException {
        Player Thierry = new Manual(1);
        PerfectBoard plateau = new PerfectBoard(Thierry);
        assertEquals(true, plateau.isGameFinished());
    }

    @Test
    void testPickACaisseCard() {
        Player Thierry = new Manual(1);
        PerfectBoard plateau = new PerfectBoard(Thierry);
        assertEquals(true, plateau.pickACaisseCard() instanceof Card);
    }

    @Test
    void testPickAChanceCard() {
        Player Thierry = new Manual(1);
        PerfectBoard plateau = new PerfectBoard(Thierry);
        assertEquals(true, plateau.pickAChanceCard() instanceof Card);
    }

    @Test
    void testSetSommeDesThisRound() {
        //
    }

    @Test
    void testAddNumerousHouses() throws IsBankruptException{
        Player Thierry = new Manual(1);
        PerfectBoard plateau = new PerfectBoard();
        for (Case currCase : plateau.getPlateau()){
            if (currCase instanceof CasePropriete && ((CasePropriete) currCase).getTypeOuCouleur() == TypePropriete.BLEU){
                ((CasePropriete) currCase).setOwner(Thierry);
            }
        }
            HashMap<TypePropriete, Integer> map = new HashMap<>();
            map.put(TypePropriete.BLEU, 2);
            plateau.addNumerousHouses(map);

            assertEquals(1100, Thierry.getBankAccount());
            

        for(Case currCase : plateau.getPlateau()){
            if (currCase instanceof CasePropriete && ((CasePropriete) currCase).getTypeOuCouleur() == TypePropriete.BLEU){
                assertEquals(1, ((CasePropriete) currCase).getNombreMaisons());
            }
        }  
    }
}   
