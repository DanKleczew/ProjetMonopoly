package fr.pantheonsorbonne.miage.game.Monopoly.Cards;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CardDeplacementTeleportation implements Card{

    private int indiceDestinationPrecise;

    public CardDeplacementTeleportation(int indiceDestinationCase){
        this.indiceDestinationPrecise = indiceDestinationCase;
    }
    
    @Override
    public void cardEffect(Player joueur, PerfectBoard plateauComplet) throws IsBankruptException {
        System.out.println("Rendez-vous à " + plateauComplet.getCaseByIndice(indiceDestinationPrecise).toString());
            plateauComplet.assignNewPosition(joueur, indiceDestinationPrecise);    
    }
}
