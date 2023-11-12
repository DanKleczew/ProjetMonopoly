package fr.pantheonsorbonne.miage.game.Monopoly.Cards;

import fr.pantheonsorbonne.miage.game.Monopoly.PhysicalGame;
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
        joueurGagnant.bankAccountModify(stealAmount * (PhysicalGame.listeJoueurs.size() - 1));
    }
    
    private void allOthersLoseMoney(Player joueurGagnant) throws IsBankruptException{
        for (Player joueur : PhysicalGame.listeJoueurs){
            if (! joueurGagnant.equals(joueur)){
                joueur.bankAccountModify(-stealAmount);
            }
        }
    }
}
