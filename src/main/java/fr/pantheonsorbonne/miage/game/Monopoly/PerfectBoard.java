package fr.pantheonsorbonne.miage.game.Monopoly;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

import fr.pantheonsorbonne.miage.game.Monopoly.Cards.Card;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.Deck;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.DeckCaisse;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.DeckChance;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.TypePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Manual;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class PerfectBoard extends Board {

    final private Deck deckCaisse = new DeckCaisse();
    final private Deck deckChance = new DeckChance();
    final private Deque<Player> listeJoueurs = new ArrayDeque<Player>();

    private int sumDiceThisRound;

    public PerfectBoard() {
        // Crée deux bots identiques (à modifier)
        for (int i = 1; i <= 2; i++) {
            listeJoueurs.add(new Manual(i));
        }
        // Pose les joueurs sur la case départ
        for (Player joueur : listeJoueurs) {
            setInitialPosition(joueur);
        }
    }

    public Deque<Player> getListeJoueurs() {
        return listeJoueurs;
    }

    public Card pickAChanceCard() {
        return (deckChance.piocher());
    }

    public Card pickACaisseCard() {
        return (deckCaisse.piocher());
    }

    // Appelée à chaque lancer de dés (Voir Player.throwDice(Board plateau))
    public void setSommeDesThisRound(int somme) {
        sumDiceThisRound = somme;
    }

    public int getSommeDesThisRound() {
        return sumDiceThisRound;
    }

    public Player getNextPlayer() {
        Player nextPlayer;
        listeJoueurs.add(nextPlayer = listeJoueurs.poll());
        return nextPlayer;
    }

    public boolean isGameFinished() {
        return listeJoueurs.size() == 1;
    }

    public void addNumerousHouses(HashMap<TypePropriete, Integer> housesCouleurNumber) throws IsBankruptException {
        // On parcourt la HashMap
        for (TypePropriete couleur : housesCouleurNumber.keySet()) {
            // On parcourt autant de fois que nécessaire le plateau pour bien ajouter les
            // maisons
            // une par une dans l'ordre dans lequel sont disposées les propriétés
            for (int i = 0;; i++, i %= 40) {
                // Si la case est une case de la bonne couleur on rajoute une maison dessus
                // Et on modifie la HashMap pour réduire le nombre de maisons à placer
                if (plateau[i] instanceof CasePropriete && ((CasePropriete) plateau[i]).getTypeOuCouleur() == couleur) {
                    ((CasePropriete) plateau[i]).addHouse();
                    housesCouleurNumber.replace(couleur, housesCouleurNumber.get(couleur) - 1);
                }
                // Si le nombre de maisons à placer sur une couleur atteint Zéro on passe à
                // l'entrée suivante dans la HashMap.
                if (housesCouleurNumber.get(couleur) == 0) {
                    break;
                }
            }
        }
    }

    public void sellNumerousHouses(HashMap<TypePropriete, Integer> housesCouleurNumber) throws IsBankruptException {
        // On parcourt la HashMap
        for (TypePropriete couleur : housesCouleurNumber.keySet()) {
            // On parcourt autant de fois que nécessaire le plateau pour bien retirer les
            // maisons
            // une par une dans l'ordre inverse de celui dans lequel sont disposées les
            // propriétés
            for (int i = 39;; i--) {
                // Petit if pour ne pas sortir de l'Array
                if (i == -1) {
                    i = 39;
                }
                // Si la case est une case de la bonne couleur on enlève une maison dessus
                // Et on modifie la HashMap pour réduire le nombre de maisons à enlever
                if (plateau[i] instanceof CasePropriete && ((CasePropriete) plateau[i]).getTypeOuCouleur() == couleur) {
                    ((CasePropriete) plateau[i]).sellHouse();
                    housesCouleurNumber.replace(couleur, housesCouleurNumber.get(couleur) - 1);
                }
                // Si le nombre de maisons à placer sur une couleur atteint Zéro on passe à
                // l'entrée suivante dans la HashMap.
                if (housesCouleurNumber.get(couleur) == 0) {
                    break;
                }
            }
        }
    }

}
