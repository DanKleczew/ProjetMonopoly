package fr.pantheonsorbonne.miage.game.Monopoly.Cards;

import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CardWinLose implements Card {

    private int gainOuPerte;
    
    public CardWinLose(int gainOuPerte){
        this.gainOuPerte = gainOuPerte;
    }


    @Override
    public void cardEffect(Player joueur) throws IsBankruptException {
        joueur.bankAccountModify(gainOuPerte);
        
    }
    
}
