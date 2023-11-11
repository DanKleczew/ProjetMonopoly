package fr.pantheonsorbonne.miage.game.Monopoly.Cards;

import fr.pantheonsorbonne.miage.game.Monopoly.Board;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CardSteal implements Card {

    private int stealAmount;

    public CardSteal(int stealAmount){
        this.stealAmount = stealAmount;
    }

    @Override
    public void cardEffect(Player joueurGagnant) throws IsBankruptException {
        winMoney(joueurGagnant);
        allOthersLoseMoney(joueurGagnant);
    }

    private void winMoney(Player joueurGagnant) throws IsBankruptException{
        joueurGagnant.bankAccountModify(stealAmount * (Board.listeJoueurs.size() - 1));
    }
    
    private void allOthersLoseMoney(Player joueurGagnant) throws IsBankruptException{
        for (Player joueur : Board.listeJoueurs){
            if (! joueurGagnant.equals(joueur)){
                joueur.bankAccountModify(-stealAmount);
            }
        }
    }
}
