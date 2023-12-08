package fr.pantheonsorbonne.miage.game.Monopoly.Cards;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CardDeplacementNextGare implements Card {
    
    @Override
    public void cardEffect(Player joueur, PerfectBoard plateauComplet) throws IsBankruptException {
        System.out.println("Rendez-vous à la prochaine gare");
            plateauComplet.assignNewPosition(joueur, plateauComplet.getIndiceNextGare(joueur));
        }
        
}
