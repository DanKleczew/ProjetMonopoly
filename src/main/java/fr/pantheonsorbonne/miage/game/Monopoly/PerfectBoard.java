package fr.pantheonsorbonne.miage.game.Monopoly;

import java.util.ArrayDeque;
import java.util.Queue;

import fr.pantheonsorbonne.miage.game.Monopoly.Cards.Card;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.Deck;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.DeckCaisse;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.DeckChance;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Manual;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class PerfectBoard extends Board {

    final private Deck deckCaisse = new DeckCaisse();
    final private Deck deckChance = new DeckChance();
    final private Queue<Player> listeJoueurs = new ArrayDeque<Player>();
    
    private int sumDiceThisRound;

    public PerfectBoard(){
        //Crée deux bots identiques (à modifier)
        for (int i = 1; i <= 2; i++) {
            listeJoueurs.add(new Manual(i));
        }
        //Pose les joueurs sur la case départ
        for (Player joueur : listeJoueurs) {
            setInitialPosition(joueur);
        }
    }
    
    
    public Queue<Player> getListeJoueurs() {
        return listeJoueurs;
    }
    public Card pickAChanceCard() {
        return (deckChance.piocher());
    }
    public Card pickACaisseCard() {
        return (deckCaisse.piocher());
    }
    
    //Appelée à chaque lancer de dés (Voir Player.throwDice(Board plateau))
    public void setSommeDesThisRound(int somme){
        sumDiceThisRound = somme;
    }
    public int getSommeDesThisRound(){
        return sumDiceThisRound;
    }
}

