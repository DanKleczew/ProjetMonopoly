package fr.pantheonsorbonne.miage.game.Monopoly.Cards;

import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CardGoToPrison implements Card {

    @Override
    public void cardEffect(Player joueur) throws IsBankruptException {
        joueur.setTimeOut();
    }
    
}
