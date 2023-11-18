package fr.pantheonsorbonne.miage.game.Monopoly;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.Monopoly.Cases.Case;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.TypePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Manual;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class PerfectBoardTest {
    @Test
    void testGetListeJoueurs() {
    }

    @Test
    void testGetNextPlayer() {

    }

    @Test
    void testGetSommeDesThisRound() {

    }

    @Test
    void testIsGameFinished() {

    }

    @Test
    void testPickACaisseCard() {

    }

    @Test
    void testPickAChanceCard() {

    }

    @Test
    void testSetSommeDesThisRound() {

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
