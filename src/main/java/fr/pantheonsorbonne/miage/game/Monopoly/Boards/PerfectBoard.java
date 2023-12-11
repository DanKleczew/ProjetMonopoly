package fr.pantheonsorbonne.miage.game.Monopoly.Boards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.pantheonsorbonne.miage.game.Monopoly.Cards.Card;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.Deck;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.DeckCaisse;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.DeckChance;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.TypePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.PlayersManager;

public class PerfectBoard extends Board {
    /*
     * La classe PerfectBoard permet la manipulation de plus de données que le Board
     * simple :
     * Elle s'occupe des decks de cartes, et
     * des maisons (achat, vente, cassage)
     */

    final private Deck deckCaisse;
    final private Deck deckChance;
    
    final private Map<Player, String> couleurDeTexte = new HashMap<>();
    final private List<String> couleursANSI = new ArrayList<String>(
            Arrays.asList("\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001B[36m"));

    public PerfectBoard(PlayersManager ensembleDesJoueurs) {
        this.deckCaisse = new DeckCaisse(ensembleDesJoueurs);
        this.deckChance = new DeckChance();

        int i = 0;
        for (Player joueur : ensembleDesJoueurs.getListeJoueurs()) {
            this.setInitialPosition(joueur);
            couleurDeTexte.put(joueur, couleursANSI.get(i++));
        }
    }

    public String getCouleur(Player joueur) {
        return couleurDeTexte.get(joueur);
    }

    public Card pickAChanceCard() {
        return (deckChance.piocher());
    }

    public Card pickACaisseCard() {
        return (deckCaisse.piocher());
    }

    

    // Ajoute n maisons sur les propriétés d'une couleur donnée, autant de couleurs
    // que nécessaire
    public void addNumerousHouses(Map<TypePropriete, Integer> map) throws IsBankruptException {
        for (TypePropriete couleur : map.keySet()) {
            List<CasePropriete> listeDeCaseDeCetteCouleur = this.getProprietesByColor(couleur);
            int nombreMaisonsAPlacer = map.get(couleur);

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
            if (nombreMaisonsAPlacer == 0) {
                return;
            }
        }
        if (nombreMaisonsAPlacer > 0) {
            addNumerousHousesSimpleWay(listeDeCases, nombreMaisonsAPlacer);
        } else {
            return;
        }
    }

    // Retire n maisons sur les propriétés d'une couleur donnée, autant de couleurs
    // que nécessaire
    public void sellNumerousHouses(Map<TypePropriete, Integer> map) throws IsBankruptException {
        for (TypePropriete couleur : map.keySet()) {
            // On crée une Liste pour stocker les cases correspondant à la couleur
            List<CasePropriete> listeDeCaseDeCetteCouleur = new ArrayList<>();

            // Et un int pour le nombre de maisons à retirer
            int nombreMaisonsARetirer = map.get(couleur);

            // On crée la liste à l'envers pour pouvoir retirer les maisons dans l'ordre
            // inverse d'apparition
            List<CasePropriete> allColoredProprietes = this.getAllColoredProprietes();
            for (int i = allColoredProprietes.size() - 1; i >= 0; i--) {
                if (allColoredProprietes.get(i).getTypeOuCouleur() == couleur) {
                    listeDeCaseDeCetteCouleur.add(allColoredProprietes.get(i));
                }
            }

            sellNumerousHousesComplexWay(listeDeCaseDeCetteCouleur, nombreMaisonsARetirer);

        }
    }

    // Permet de retirer n maisons sur des propriétés d'une même couleur, même si
    // elles n'ont pas le même nombre de maisons sur chaque
    private void sellNumerousHousesComplexWay(List<CasePropriete> listeDeCases, int nombreMaisonsARetirer)
            throws IsBankruptException {
        int nombreInitialMaisons = listeDeCases.get(0).getNombreMaisons();

        for (CasePropriete propriete : listeDeCases) {

            if (propriete.getNombreMaisons() > nombreInitialMaisons) {
                propriete.sellHouse();
                nombreMaisonsARetirer--;
            }
            if (nombreMaisonsARetirer == 0) {
                return;
            }
        }

        if (nombreMaisonsARetirer > 0) {
            sellNumerousHousesSimpleWay(listeDeCases, nombreMaisonsARetirer);
        } else {
            return;
        }
    }

    // Retire n maisons sur des propriétés d'une même couleur ayant toutes le même
    // nombre de maisons
    private void sellNumerousHousesSimpleWay(List<CasePropriete> listeDeCases, int nombreMaisonsARetirer)
            throws IsBankruptException {

        int i = 0;

        while (nombreMaisonsARetirer > 0) {
            listeDeCases.get(i).sellHouse();
            i++;
            i %= listeDeCases.size();
            nombreMaisonsARetirer--;
        }
    }

    private List<CasePropriete> findFirstHousedColor(Player joueur) {
        List<CasePropriete> listeProprietesColoreesOwned = this.getOwnedColoredProperties(joueur);
        List<CasePropriete> listeDeCasesDeCetteCouleur = new ArrayList<>();
        TypePropriete couleur = null;

        for (CasePropriete proprieteColoree : listeProprietesColoreesOwned) {
            if (proprieteColoree.getNombreMaisons() > 0) {
                couleur = proprieteColoree.getTypeOuCouleur();
                break;
            }
        }

        for (int i = listeProprietesColoreesOwned.size() - 1; i >= 0; i--) {
            if (listeProprietesColoreesOwned.get(i).getTypeOuCouleur() == couleur) {
                // System.out.println("AAAA" + listeProprietesColoreesOwned.get(i).toString()
                // +""+ i);
                listeDeCasesDeCetteCouleur.add(listeProprietesColoreesOwned.get(i));
            }
        }
        return listeDeCasesDeCetteCouleur;
    }

    public void houseBreak(Player joueur) throws IsBankruptException {
        List<CasePropriete> casesUneCouleur = this.findFirstHousedColor(joueur);
        this.sellNumerousHousesComplexWay(casesUneCouleur, 1);
        joueur.bankAccountModify(-(casesUneCouleur.get(0).getPrixMaisonUnitaire() / 2));
        // On se sert de sell pour ne pas avoir à recréer de méthode spécifique pour
        // break
        // Mais le joueur n'est pas censé gagner d'argent quand une de ses maisons se
        // fait casser
    }

    public boolean hasHouses(Player joueur) {
        for (CasePropriete proprieteColoree : this.getOwnedColoredProperties(joueur)) {
            if (proprieteColoree.getNombreMaisons() > 0) {
                return true;
            }
        }
        return false;
    }

    public double getLoyerDeBaseProprieteLaPlusChere(Player joueur) {
        return this.findFirstHousedColor(joueur).get(0).getEchelleDeLoyer()[0];
        // .get(0) car la liste est construite à l'envers du plateau
        // La propriété la plus chère et avec le loyer le plus cher arrive en premier
    }

}
