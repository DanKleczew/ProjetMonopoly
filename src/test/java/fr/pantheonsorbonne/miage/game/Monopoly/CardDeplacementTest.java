package fr.pantheonsorbonne.miage.game.Monopoly;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.*;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.*;




public class CardDeplacementTest{
    

    @Test
    public void cardEffectTest() throws IsBankruptException {

        Player Thierry = new VoidBot(1);
        PerfectBoard plateauFantome = new PerfectBoard(new PlayersManager(Thierry));

        Card carte;
        
        plateauFantome.walk(Thierry, 3);
        
        //Test "Avancez de x cases"
        assertEquals(3, plateauFantome.getPositionJoueur(Thierry));

        carte = new CardDeplacementRecul(3);
        carte.cardEffect(Thierry, plateauFantome);
        assertEquals(0, plateauFantome.getPositionJoueur(Thierry));

        carte = new CardDeplacementTeleportation(39);

        carte.cardEffect(Thierry, plateauFantome);
        
        //Test téléportation
        assertEquals(39, plateauFantome.getPositionJoueur(Thierry));

        carte = new CardDeplacementNextGare();

        carte.cardEffect(Thierry, plateauFantome);
        
        //Test next gare
        assertEquals(5, plateauFantome.getPositionJoueur(Thierry));
        }
        
    
    
}
