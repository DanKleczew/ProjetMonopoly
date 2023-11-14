package fr.pantheonsorbonne.miage.game.Monopoly.Cards;

import fr.pantheonsorbonne.miage.game.Monopoly.Board;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CardSteal implements Card {

    // Card Steal = Cartes du type "C'est votre anniversaire tout le monde vous
    // donne 10"

    private int stealAmount;

    public CardSteal(int stealAmount) {
        this.stealAmount = stealAmount;
    }

    private boolean verifMoneyEnough(Player joueur, int moneyNeeded) {
        return (joueur.getBankAccount() >= moneyNeeded);
    }
    @Override
    public void cardEffect(Player joueurGagnant) throws IsBankruptException {
        // Joueur piochant la carte gagne son argent
        // Autres joueurs perdent de l'argent
        for (Player joueur : Board.getListeJoueurs()) {
            if (!joueurGagnant.equals(joueur)) {
                if (verifMoneyEnough(joueur, stealAmount)) {
                    joueurGagnant.bankAccountModify(stealAmount);
                } else {
                    joueurGagnant.bankAccountModify(joueur.getBankAccount());
                }
                joueur.bankAccountModify(-stealAmount);
            }
        }
    }
}
