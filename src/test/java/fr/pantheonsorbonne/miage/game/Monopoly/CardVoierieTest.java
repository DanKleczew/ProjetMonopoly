package fr.pantheonsorbonne.miage.game.Monopoly;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.CardVoierie;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.TypePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.*;

public class CardVoierieTest{
    

    @Test
    public void testCardEffect() throws IsBankruptException {
        Player Thierry = new VoidBot(1);

        PerfectBoard plateauFantome = new PerfectBoard(Thierry);
        CasePropriete Belleville = plateauFantome.getAllColoredProprietes().get(0);
        CasePropriete Lecourbe = plateauFantome.getAllColoredProprietes().get(1);
        Belleville.setOwner(Thierry);
        Lecourbe.setOwner(Thierry);

        Map<TypePropriete, Integer> listeDeSouhait = new HashMap<>();
        listeDeSouhait.put(TypePropriete.MARRON, 9);
        plateauFantome.addNumerousHouses(listeDeSouhait);
        // 1500 - 9*50 =  1050
        assertEquals(1050, Thierry.getBankAccount());

        CardVoierie carte = new CardVoierie(50, 200);
        carte.cardEffect(Thierry, plateauFantome);

        assertEquals(650, Thierry.getBankAccount());
    }

    @Test
    public void testCardEffectThrow() throws IsBankruptException{
        Player Thierry = new VoidBot(1);
        PerfectBoard plateauFantome = new PerfectBoard(Thierry);
        
        CasePropriete Belleville = plateauFantome.getAllColoredProprietes().get(0);
        CasePropriete Lecourbe = plateauFantome.getAllColoredProprietes().get(1);
        Belleville.setOwner(Thierry);
        Lecourbe.setOwner(Thierry);

        Map<TypePropriete, Integer> listeDeSouhait = new HashMap<>();
        listeDeSouhait.put(TypePropriete.MARRON, 9);
        plateauFantome.addNumerousHouses(listeDeSouhait);
        // 1500 - 9*50 =  1050
        assertEquals(1050, Thierry.getBankAccount());
        Thierry.bankAccountModify(-1049);

        CardVoierie carte = new CardVoierie(50, 100);
        assertThrows(IsBankruptException.class, ()->carte.cardEffect(Thierry, plateauFantome));
    }
}
    