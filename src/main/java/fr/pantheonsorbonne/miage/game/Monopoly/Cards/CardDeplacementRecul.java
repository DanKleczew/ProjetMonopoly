package fr.pantheonsorbonne.miage.game.Monopoly.Cards;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CardDeplacementRecul implements Card{
    
    private int recul;

    public CardDeplacementRecul(int nombreDeCases){
        this.recul = nombreDeCases;
    }

    @Override 
    public void cardEffect(Player joueur, PerfectBoard plateauComplet) throws IsBankruptException {
        System.out.println("Reculez de "  + recul + " cases.");
            plateauComplet.assignNewPosition(joueur, plateauComplet.getPositionJoueur(joueur) - recul);
    }
}
