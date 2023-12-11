package fr.pantheonsorbonne.miage.game.Monopoly;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.*;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.*;

public class CaseProprieteTest {
    
    @Test
    public void setAsJailTest(){
        CasePropriete a = new CasePropriete("Boulevard", 200 , TypePropriete.BLEU);

        a.setAsJail();

        assertEquals(true, a.isAJail());
    }

    @Test
    public void setSquatTest() throws IsBankruptException{
        CasePropriete a = new CasePropriete("Boulevard", 200 , TypePropriete.BLEU);
        Player Thierry = new VoidBot(0);
        Player Didier = new VoidBot(1);
        PlayersManager liste = new PlayersManager(Thierry, Didier);
        a.setOwner(Thierry);
        a.setSquat();

        a.doCaseEffect(Didier, new PerfectBoard(liste));

        assertEquals(1500, Didier.getBankAccount());
    }

    @Test 
    public void removeSquatTest() throws IsBankruptException{
        CasePropriete a = new CasePropriete("Boulevard", 200 , TypePropriete.BLEU);
        Player Thierry = new VoidBot(0);
        Player Didier = new VoidBot(1);
        PlayersManager liste = new PlayersManager(Thierry, Didier);
        PerfectBoard plateaufantome = new PerfectBoard(liste);

        a.setOwner(Thierry);

        a.setSquat();

        a.removeSquat(true, plateaufantome);

        assertEquals(1300, Thierry.getBankAccount());
        
        a.doCaseEffect(Didier, plateaufantome);

        assertEquals(1465, Didier.getBankAccount());

    }

    @Test 
    public void policeJobTest() throws IsBankruptException{
        CasePropriete a = new CasePropriete("Boulevard", 200 , TypePropriete.BLEU);
        Player Thierry = new VoidBot(0);
        Player Didier = new VoidBot(1);
        PlayersManager liste = new PlayersManager(Thierry, Didier);
        a.setOwner(Thierry);
        a.setSquat();

        for (int i = 0 ; i<8; i++){
            a.policeJob();
        }

        a.doCaseEffect(Didier, new PerfectBoard(liste));
        assertEquals(1465, Didier.getBankAccount());
    }   

    @Test
    public void getNombreMaisonsTest() throws IsBankruptException{
        CasePropriete a = new CasePropriete("Boulevard", 200 , TypePropriete.MARRON);

        a.setOwner(new VoidBot(0));
        a.addHouse();
        assertEquals(1, a.getNombreMaisons());
    }

    @Test 
    public void resetNombreMaisonAndNoPayTest() throws IsBankruptException{
        CasePropriete a = new CasePropriete("Boulevard", 200 , TypePropriete.MARRON);

        a.setOwner(new VoidBot(0));
        a.setHousesNoPay(2);
        assertEquals(2, a.getNombreMaisons());
        a.resetNombreMaisons();
        assertEquals(0, a.getNombreMaisons());

    }

    @Test
    public void getLoyerAPayerTest() throws IsBankruptException{

        Player Thierry = new VoidBot(0);
        PerfectBoard plateauFantome = new PerfectBoard(new PlayersManager(Thierry));
        CaseAchetable a = plateauFantome.getAllProprietes().get(0);
        CaseAchetable b = plateauFantome.getAllProprietes().get(1);
        
        a.setOwner(Thierry);
        b.setOwner(Thierry);

        
        assertEquals(4, a.getLoyerAPayer(plateauFantome));
    }
}
