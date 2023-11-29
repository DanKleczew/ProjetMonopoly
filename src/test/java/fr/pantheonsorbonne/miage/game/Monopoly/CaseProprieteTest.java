package fr.pantheonsorbonne.miage.game.Monopoly;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.Monopoly.Cases.*;

public class CaseProprieteTest {
    
    @Test
    public void setAsJailTest(){
        CasePropriete a = new CasePropriete("Boulevard", 200 , TypePropriete.BLEU);

        a.setAsJail();

        assertEquals(true, a.isAJail());
    }
}
