package fr.pantheonsorbonne.miage.game.Monopoly;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import fr.pantheonsorbonne.miage.game.Monopoly.Cards.Deck;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.DeckCaisse;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.DeckChance;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Manual;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class PhysicalGame {
    //Tout ca est public parce qu'on s'en sert dans le main (Cette Classe est la "Base de Données" de la partie)
    public static Deck deckCaisseDeCommunaute = new DeckCaisse();
    public static Deck deckChance = new DeckChance();
    public static Board plateau = new Board();
    public static Queue<Player> listeJoueurs = new ArrayDeque<Player>();
    public static Map<Player, Integer> positionJoueurs = new HashMap<>();
    

    public PhysicalGame(int nbJoueurs){
        //Crée tous les joueurs et les met dans la liste de joueurs  (pour le moment que des bots du même type)
        for (int i = 1; i <= nbJoueurs; i++){
            listeJoueurs.add(new Manual(i));
        }
        for (Player joueur : listeJoueurs){
            positionJoueurs.put(joueur, 0);
        }
        
        

    }

}
