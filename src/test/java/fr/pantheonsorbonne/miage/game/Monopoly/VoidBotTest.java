package fr.pantheonsorbonne.miage.game.Monopoly;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.*;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.*;

public class VoidBotTest {

    @Test
    public void thoughts() throws IsBankruptException {

        VoidBot Thierry = new VoidBot(0);
        PerfectBoard plateauFantome = new PerfectBoard(Thierry);
        ((CaseAchetable) plateauFantome.plateau[1]).setOwner(Thierry, true);
        ((CaseAchetable) plateauFantome.plateau[3]).setOwner(Thierry, true);
        ((CaseAchetable) plateauFantome.plateau[5]).setOwner(Thierry, true);

        ((CasePropriete) plateauFantome.plateau[1]).addHouse();

        Thierry.thinkAndDo(plateauFantome);

        assertEquals(1, plateauFantome.getOwnedColoredProperties(Thierry).get(0).getNombreMaisons());
        assertEquals(0, plateauFantome.getOwnedColoredProperties(Thierry).get(1).getNombreMaisons());
        assertEquals(false, plateauFantome.allProprietes.get(3).isHypothequed());
        assertEquals(false, ((CasePropriete) plateauFantome.allProprietes.get(3)).isAJail());
    }

    @Test 
    public void askRemoveInstantlySquat() {
        
        VoidBot Thierry = new VoidBot(0);
        PerfectBoard plateauFantome = new PerfectBoard(Thierry);
        CasePropriete caseSquatee = new CasePropriete("a", 1, TypePropriete.MARRON);

        assertEquals(false, Thierry.askRemoveInstantlySquat(caseSquatee, plateauFantome));
    }
}
