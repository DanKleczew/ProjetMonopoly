package fr.pantheonsorbonne.miage.game.Monopoly;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.Monopoly.Players.*;

public class IsBankruptExceptionTest {
    
    @Test 
    public void settersGettersTest(){
        Player Thierry = new VoidBot(0);
        Player Didier = new VoidBot(1);
        try {
            throw new IsBankruptException(Thierry);
        }
        catch (IsBankruptException e){
            e.setGagnant(Didier);

            assertEquals(Didier, e.getGagnant());
        }


    }
}
