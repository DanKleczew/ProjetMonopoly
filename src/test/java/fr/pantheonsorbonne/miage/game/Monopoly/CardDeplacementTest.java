package fr.pantheonsorbonne.miage.game.Monopoly;


import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.Monopoly.Cards.*;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.*;



public class CardDeplacementTest{
    

    @Test
    public void cardEffectTest() throws IsBankruptException {

        Player Thierry = new VoidBot(1);
        PerfectBoard plateauFantome = new PerfectBoard(Thierry);

        CardDeplacement carte = new CardDeplacement(3);
        
        plateauFantome.walk(Thierry, 3);
        carte.cardEffect(Thierry, plateauFantome);
        
        //Test "Avancez de x cases"
        assertEquals(6, plateauFantome.getPositionJoueur(Thierry));

        carte = new CardDeplacement(-3);
        carte.cardEffect(Thierry, plateauFantome);
        assertEquals(3, plateauFantome.getPositionJoueur(Thierry));

        carte = new CardDeplacement("Rue de la Paix", 39);

        carte.cardEffect(Thierry, plateauFantome);
        
        //Test téléportation
        assertEquals(39, plateauFantome.getPositionJoueur(Thierry));

        carte = new CardDeplacement("Prochaine Gare");

        carte.cardEffect(Thierry, plateauFantome);
        
        //Test next gare
        assertEquals(5, plateauFantome.getPositionJoueur(Thierry));
        }
        
    
    
}
