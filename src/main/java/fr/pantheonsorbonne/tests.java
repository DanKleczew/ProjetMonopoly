package fr.pantheonsorbonne;

import fr.pantheonsorbonne.miage.game.Monopoly.PhysicalGame;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class tests {
    
    public static void main(String[] args) {
        new PhysicalGame(2);
        for (Player b : PhysicalGame.positionJoueurs.keySet()){
            System.out.println(PhysicalGame.plateau.getBoard()[PhysicalGame.positionJoueurs.get(b)].toString());
        }
    }
}
