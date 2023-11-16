package fr.pantheonsorbonne.miage.game.Monopoly.Cards;

import fr.pantheonsorbonne.miage.game.Monopoly.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CardWinLose implements Card{

    private int gainOuPerte;
    
    public CardWinLose(int gainOuPerte){
        this.gainOuPerte = gainOuPerte;
    }

    @Override
    public void cardEffect(Player joueur, PerfectBoard plateau) throws IsBankruptException {
        if (gainOuPerte > 0){
            System.out.println("Vous gagnez " + gainOuPerte + " $ !");
        }
        else{
            System.out.println("Vous perdez " + gainOuPerte + "$.");
        }
        joueur.bankAccountModify(gainOuPerte);
        
    }
    
}
