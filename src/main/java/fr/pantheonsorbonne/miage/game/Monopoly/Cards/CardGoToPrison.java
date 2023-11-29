package fr.pantheonsorbonne.miage.game.Monopoly.Cards;

import fr.pantheonsorbonne.miage.game.Monopoly.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CardGoToPrison implements Card {

    @Override
    public void cardEffect(Player joueur, PerfectBoard plateau) throws IsBankruptException {
        System.out.println("Rendez-vous en prison");
        joueur.setTimeOut(plateau);
    }
    
}
