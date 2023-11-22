package fr.pantheonsorbonne.miage.game.Monopoly;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;

import fr.pantheonsorbonne.miage.game.Monopoly.Cards.Card;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.Deck;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.DeckCaisse;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.DeckChance;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.Case;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseAchetable;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.TypePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class PerfectBoard extends Board {

    final private Deck deckCaisse = new DeckCaisse();
    final private Deck deckChance = new DeckChance();
    final private Deque<Player> listeJoueurs = new ArrayDeque<Player>();
    

    public PerfectBoard(Player... tableJoueur) {
        // Crée deux bots identiques (à modifier)
        for (Player joueur : tableJoueur) {
            listeJoueurs.add(joueur);
            listeJoueurs.add(null); //Un flag pour détecter la fin d'un tour
        }
        // Pose les joueurs sur la case départ
        for (Player joueur : listeJoueurs) {
            setInitialPosition(joueur);
        }
    }

    public Deque<Player> getListeJoueurs() {
        return listeJoueurs;
    }

    
    public void deletePlayer(IsBankruptException exception) throws IsBankruptException {
        for (Case currCase : plateau){
            if (currCase instanceof CaseAchetable && ((CaseAchetable) currCase).getOwner() == exception.getPerdant()){
                ((CaseAchetable) currCase).setOwner(exception.getGagnant(), true);
                if (((CaseAchetable) currCase).isHypothequed()){
                    ((CaseAchetable) currCase).switchHypothequeStatus();
                    
                }
            }
        }

        listeJoueurs.remove(exception.getPerdant());
    }

    public Card pickAChanceCard() {
        return (deckChance.piocher());
    }

    public Card pickACaisseCard() {
        return (deckCaisse.piocher());
    }

    public Player getNextPlayer() {
        Player nextPlayer;
        listeJoueurs.add(nextPlayer = listeJoueurs.poll());
        return nextPlayer;
    }

    public boolean isGameFinished() {
        return listeJoueurs.size() == 2;
    }

    //Ajoute n maisons sur les propriétés d'une couleur donnée, autant de couleurs que nécessaire
    public void addNumerousHouses(Map<TypePropriete, Integer> map) throws IsBankruptException {
        // On parcourt la HashMap
        for (TypePropriete couleur : map.keySet()) {
            // On crée une Liste pour stocker les cases correspondant à la couleur
            List<CasePropriete> listeDeCaseDeCetteCouleur = new ArrayList<>();

            // Et un int pour le nombre de maisons à placer
            int nombreMaisonsAPlacer = map.get(couleur);

            // On parcourt le plateau à la recherche des bonnes cases
            for (Case currCase : plateau) {
                if (currCase instanceof CasePropriete && ((CasePropriete) currCase).getTypeOuCouleur() == couleur) {
                    // On append la liste des trois ou deux bonnes cases
                    listeDeCaseDeCetteCouleur.add((CasePropriete) currCase);
                }
            }

            addNumerousHousesComplexWay(listeDeCaseDeCetteCouleur, nombreMaisonsAPlacer);
        }
    }

    // Permet de placer n maisons sur des propriétés d'une couleur à condition que
    // toutes les propriétés aient initialement le
    // même nombre de maisons
    private void addNumerousHousesSimpleWay(List<CasePropriete> listeDeCases, int nombreMaisonsAPlacer)
            throws IsBankruptException {
        int i = 0;
        while (nombreMaisonsAPlacer != 0) {
            listeDeCases.get(i).addHouse();
            i++;
            i %= listeDeCases.size();
            nombreMaisonsAPlacer--;
        }
    }

    // Permet de placer n maisons sur des propriétés d'une même couleur, même si
    // elles n'ont pas le même nombre de maisons sur chaque
    private void addNumerousHousesComplexWay(List<CasePropriete> listeDeCases, int nombreMaisonsAPlacer)
            throws IsBankruptException {
        int nombreInitialMaisons = listeDeCases.get(0).getNombreMaisons();
        for (CasePropriete currCase : listeDeCases) {
            if (currCase.getNombreMaisons() != nombreInitialMaisons) {
                currCase.addHouse();
                nombreMaisonsAPlacer--;
            }
            if (nombreMaisonsAPlacer == 0)
                return;
        }
        if (nombreMaisonsAPlacer > 0) {
            addNumerousHousesSimpleWay(listeDeCases, nombreMaisonsAPlacer);
        } else {
            return;
        }
    }

    //Retire n maisons sur les propriétés d'une couleur donnée, autant de couleurs que nécessaire
    public void sellNumerousHouses(Map<TypePropriete, Integer> map) throws IsBankruptException {
        for (TypePropriete couleur : map.keySet()) {
            // On crée une Liste pour stocker les cases correspondant à la couleur
            List<CasePropriete> listeDeCaseDeCetteCouleur = new ArrayList<>();

            // Et un int pour le nombre de maisons à retirer
            int nombreMaisonsARetirer = map.get(couleur);

            // On crée la liste à l'envers pour pouvoir retirer les maisons dans l'ordre
            // inverse d'apparition
            for (int i = 39; i >= 0; i--) {
                if (plateau[i] instanceof CasePropriete && ((CasePropriete) plateau[i]).getTypeOuCouleur() == couleur) {
                    listeDeCaseDeCetteCouleur.add((CasePropriete) plateau[i]);
                }
            }

            sellNumerousHousesComplexWay(listeDeCaseDeCetteCouleur, nombreMaisonsARetirer);

        }
    }

    //Permet de retirer n maisons sur des propriétés d'une même couleur, même si
    // elles n'ont pas le même nombre de maisons sur chaque
    private void sellNumerousHousesComplexWay(List<CasePropriete> listeDeCases, int nombreMaisonsARetirer)
            throws IsBankruptException {

        int nombreInitialMaisons = listeDeCases.get(listeDeCases.size() - 1).getNombreMaisons();

        for (int i = listeDeCases.size() - 1; i >= 0; i--) {
            if (listeDeCases.get(i).getNombreMaisons() != nombreInitialMaisons) {
                listeDeCases.get(i).sellHouse();
                nombreMaisonsARetirer--;
            }
            if (nombreMaisonsARetirer == 0)
                return;
        }
        if (nombreMaisonsARetirer > 0) {
            sellNumerousHousesSimpleWay(listeDeCases, nombreMaisonsARetirer);
        } else {
            return;
        }
    }

    //Retire n maisons sur des propriétés d'une même couleur ayant toutes le même nombre de maisons
    private void sellNumerousHousesSimpleWay(List<CasePropriete> listeDeCases, int nombreMaisonsARetirer)
            throws IsBankruptException {

        int i = listeDeCases.size() - 1;

        while (nombreMaisonsARetirer > 0) {
            listeDeCases.get(i).sellHouse();
            i--;
            nombreMaisonsARetirer--;
            if (i == -1)
                i = listeDeCases.size() - 1;
        }
    }

}
