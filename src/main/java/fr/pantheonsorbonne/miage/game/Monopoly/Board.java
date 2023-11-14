package fr.pantheonsorbonne.miage.game.Monopoly;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import fr.pantheonsorbonne.miage.game.Monopoly.Cards.Card;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.Deck;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.DeckCaisse;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.DeckChance;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.Case;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseAchetable;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseCaisseDeCommunaute;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseChance;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseCompagnie;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseGare;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseGoToPrison;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseNeutre;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseTaxe;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.TypePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Manual;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class Board {
    private static final Case[] plateau = new Case[] {
        new CaseNeutre("Départ"),
        new CasePropriete("Boulevard de Belleville", 60, TypePropriete.MARRON),
        new CaseCaisseDeCommunaute("CDC1"),
        new CasePropriete("Rue Lecourbe", 60, TypePropriete.MARRON, 
            new int[] { 4, 20, 60, 180, 320, 450 }),
        new CaseTaxe("Impôts sur le Revenu", 200),
        new CaseGare("Gare Montparnasse"),
        new CasePropriete("Rue de Vaugirard", 100, TypePropriete.CYAN),
        new CaseChance("CC1"),
        new CasePropriete("Rue de Courcelles", 100, TypePropriete.CYAN),
        new CasePropriete("Avenue de la République", 120, TypePropriete.CYAN,
            new int[] { 8, 40, 100, 300, 450, 600 }),
        new CaseNeutre("Visite Simple"),
        new CasePropriete("Boulevard de la Vilette", 140, TypePropriete.ROSE),
        new CaseCompagnie("Compagnie de Disribution d'Électricité"),
        new CasePropriete("Avenue de Neuilly", 140, TypePropriete.ROSE),
        new CasePropriete("Rue de Paradis", 160, TypePropriete.ROSE, 
            new int[] { 12, 60, 180, 500, 700, 900 }),
        new CaseGare("Gare de Lyon"),
        new CasePropriete("Avenue Mozart", 180, TypePropriete.ORANGE),
        new CaseCaisseDeCommunaute("CDC2"),
        new CasePropriete("Boulevard Saint-Michel", 180, TypePropriete.ORANGE),
        new CasePropriete("Place Pigalle", 200, TypePropriete.ORANGE, 
            new int[] { 16, 80, 220, 600, 800, 1000 }),
        new CaseNeutre("Parc Gratuit"),
        new CasePropriete("Avenue Matignon", 220, TypePropriete.ROUGE),
        new CaseChance("CC2"),
        new CasePropriete("Boulevard Malesherbes", 220, TypePropriete.ROUGE),
        new CasePropriete("Avenue Henry-Martin", 240, TypePropriete.ROUGE,
            new int[] { 20, 100, 300, 750, 925, 1100 }),
        new CaseGare("Gare du Nord"),
        new CasePropriete("Faubourg Saint-Honoré", 260, TypePropriete.JAUNE),
        new CasePropriete("Place de la Bourse", 260, TypePropriete.JAUNE),
        new CaseCompagnie("Compagnie de Distribution des Eaux"),
        new CasePropriete("Rue La Fayette", 280, TypePropriete.JAUNE, 
            new int[] { 24, 120, 360, 850, 1025, 1200 }),
        new CaseGoToPrison("Go to Prison !"),
        new CasePropriete("Avenue de Breteuil", 300, TypePropriete.VERT),
        new CasePropriete("Avenue Foch", 300, TypePropriete.VERT),
        new CaseCaisseDeCommunaute("CDC3"),
        new CasePropriete("Boulevard des Capucines", 320, TypePropriete.VERT,
            new int[] { 28, 150, 450, 1000, 1200, 1400 }),
        new CaseGare("Gare Saint-Lazare"),
        new CaseChance("CC3"),
        new CasePropriete("Avenue des Champs-Élysées", 350, TypePropriete.BLEU),
        new CaseTaxe("Taxe de Luxe", 150),
        new CasePropriete("Rue de la Paix", 400, TypePropriete.BLEU, new int[] { 50, 200, 600, 1400, 1700, 2000 })
    };
    final private static Deck deckCaisse = new DeckCaisse();
    final private static Deck deckChance = new DeckChance();
    final private static Queue<Player> listeJoueurs = new ArrayDeque<Player>();
    private static Map<Player, Integer> positionJoueurs = new HashMap<>();

    private static int sumDiceThisRound;

    public Board(int nbJoueurs) {
        for (int i = 1; i <= nbJoueurs; i++) {
            listeJoueurs.add(new Manual(i));
        }
        for (Player joueur : listeJoueurs) {
            positionJoueurs.put(joueur, 0);
        }
    }

    public static int getNombreJoueurs() {
        return listeJoueurs.size();
    }

    public static Queue<Player> getListeJoueurs() {
        return listeJoueurs;
    }

    public static Card pickAChanceCard() {
        return (deckChance.piocher());
    }

    public static Card pickACaisseCard() {
        return (deckCaisse.piocher());
    }

    public static int getPositionJoueur(Player joueur) {
        return positionJoueurs.get(joueur);
    }

    //Appelée à chaque lancer de dés (Voir Player.throwDice())
    public static void setSommeDesThisRound(int somme){
        sumDiceThisRound = somme;
    }

    public static int getSommeDesThisRound(){
        return sumDiceThisRound;
    }

    public static void assignNewPosition(Player joueur, int indiceCase) throws IsBankruptException {
        if (indiceCase < positionJoueurs.get(joueur)) // C'est à dire on est passé par la case départ
            joueur.bankAccountModify(200);

        positionJoueurs.put(joueur, indiceCase);
    }

    public static int getIndiceNextGare(Player joueur) {
        for (int i = getPositionJoueur(joueur);; i++, i %= 40) {
            if (plateau[i] instanceof CaseGare) {
                return i;
            }
        }
    }

    public static int[] getNombreMaisonsHotels(Player joueur) {
        int nombreMaisons = 0;
        int nombreHotels = 0;
        for (Case caseParticuliere : plateau) {
            if (caseParticuliere instanceof CasePropriete
                    && ((CasePropriete) caseParticuliere).getOwner().equals(joueur)) {
                nombreMaisons += ((CasePropriete) caseParticuliere).getNombreMaisons();
                if (((CasePropriete) caseParticuliere).hasHotel()) {
                    nombreHotels++;
                }
            }
        }
        return new int[] { nombreMaisons, nombreHotels };
    }

    public static Case[] getPlateau(){
        return plateau;
    }
    public static void setNewHouse(TypePropriete couleur, int nombreMaison){

    }

    public static List<CaseAchetable> getOwnedProperties(Player joueur) {
        List<CaseAchetable> listeProprietes = new ArrayList<>();
        for (Case caseActuelle : plateau) {
            if (caseActuelle instanceof CaseAchetable) {
                if (((CaseAchetable) caseActuelle).getOwner().equals(joueur)){
                    listeProprietes.add((CaseAchetable) caseActuelle);
                }                
            }
        }
        return listeProprietes;
    }
}
