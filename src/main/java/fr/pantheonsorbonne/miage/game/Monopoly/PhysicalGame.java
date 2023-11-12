package fr.pantheonsorbonne.miage.game.Monopoly;

import java.util.ArrayDeque;
import java.util.Queue;

import fr.pantheonsorbonne.miage.game.Monopoly.Cards.Card;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Manual;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class PhysicalGame {
    static private Queue<Card> deckCaisseDeCommunaute;
    static private Queue<Card> deckChance;

    //public parce qu'on s'en sert dans le main
    static public Queue<Player> listeJoueurs = new ArrayDeque<Player>();

    public PhysicalGame(int nbJoueurs){
        //Crée tous les joueurs et les met dans la liste de joueurs  (pour le moment que des bots du même type)
        for (int i = 1; i <= nbJoueurs; i++){
            listeJoueurs.add(new Manual(i));
        }
        
        

    }

}
