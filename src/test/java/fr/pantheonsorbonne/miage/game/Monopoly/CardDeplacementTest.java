package fr.pantheonsorbonne.miage.game.Monopoly;

//import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.Monopoly.Players.*;

import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;



public class CardDeplacementTest{
    
    private int indiceDestinationPrecise = -1;
    private int nombreDeCases = -1;

    @Test
    public void cardEffect(Player joueur, PerfectBoard plateauComplet) throws IsBankruptException {
        int indiceDestination; //La destination à calculer selon le constructeur qui a été appellé

        if (indiceDestinationPrecise != -1){
            System.out.println("Rendez-vous à " + plateauComplet.getCase(indiceDestinationPrecise).toString());
            indiceDestination = indiceDestinationPrecise;
        }
        else if (nombreDeCases != -1){
            System.out.println("Avancez de "  + nombreDeCases + " cases.");
            indiceDestination = plateauComplet.getPositionJoueur(joueur) + nombreDeCases;
        }
        else {
            System.out.println("Rendez-vous à la prochaine gare");
            indiceDestination = plateauComplet.getIndiceNextGare(joueur);
        }

        plateauComplet.assignNewPosition(joueur, indiceDestination);
        }
        
    
    
}
