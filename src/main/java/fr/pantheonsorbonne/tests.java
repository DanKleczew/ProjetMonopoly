package fr.pantheonsorbonne;


import fr.pantheonsorbonne.miage.game.Monopoly.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Manual;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class tests {
    
    public static void main(String[] args) {
        Player joueur = new Manual(1);
        PerfectBoard plateauComplet = new PerfectBoard();
        
        plateauComplet.setInitialPosition(joueur);
        try{
        for (int i = 0 ; i < 300 ; i++){
        plateauComplet.assignNewPosition(joueur, 2);
        System.out.println(joueur.getBankAccount());
        }
        
        
        }
        catch (IsBankruptException e){
            System.out.println("prblm");
        }
    }
}