package fr.pantheonsorbonne.miage.game.Monopoly;

import java.util.ArrayDeque;
import java.util.Queue;

import fr.pantheonsorbonne.miage.game.Monopoly.Cards.Card;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.Case;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseCaisseDeCommunaute;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseChance;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseCompagnie;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseGare;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseGoToPrison;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseNeutre;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseTaxe;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.TypePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Manual;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class Board {

    final private Case[] plateau = new Case[] {
        new CaseNeutre("Départ"),
        new CasePropriete("Boulevard de Belleville", 60, TypePropriete.MARRON),
        new CaseCaisseDeCommunaute("CDC1"),
        new CasePropriete("Rue Lecourbe", 60, TypePropriete.MARRON),
        new CaseTaxe("Impôts sur le Revenu", 200),
        new CaseGare("Gare Montparnasse"),
        new CasePropriete("Rue de Vaugirard", 100, TypePropriete.CYAN),
        new CaseChance("CC1"),
        new CasePropriete("Rue de Courcelles", 100, TypePropriete.CYAN),
        new CasePropriete("Avenue de la République", 120, TypePropriete.CYAN),
        new CaseNeutre("Visite Simple"),
        new CasePropriete("Boulevard de la Vilette", 140, TypePropriete.ROSE),
        new CaseCompagnie("Compagnie de Disribution d'Électricité"),
        new CasePropriete("Avenue de Neuilly", 140, TypePropriete.ROSE),
        new CasePropriete("Rue de Paradis", 160, TypePropriete.ROSE),
        new CaseGare("Gare de Lyon"),
        new CasePropriete("Avenue Mozart", 180, TypePropriete.ORANGE),
        new CaseCaisseDeCommunaute("CDC2"),
        new CasePropriete("Boulevard Saint-Michel", 180, TypePropriete.ORANGE),
        new CasePropriete("Place Pigalle", 200, TypePropriete.ORANGE),
        new CaseNeutre("Parc Gratuit"),
        new CasePropriete("Avenue Matignon", 220, TypePropriete.ROUGE),
        new CaseChance("CC2"),
        new CasePropriete("Boulevard Malesherbes", 220, TypePropriete.ROUGE),
        new CasePropriete("Avenue Henry-Martin", 240, TypePropriete.ROUGE), 
        new CaseGare("Gare du Nord"),
        new CasePropriete("Faubourg Saint-Honoré", 260, TypePropriete.JAUNE),
        new CasePropriete("Place de la Bourse", 260, TypePropriete.JAUNE),
        new CaseCompagnie("Compagnie de Distribution des Eaux"),
        new CasePropriete("Rue La Fayette", 280, TypePropriete.JAUNE),
        new CaseGoToPrison("Go to Prison !"), 
        new CasePropriete("Avenue de Breteuil", 300, TypePropriete.VERT),
        new CasePropriete("Avenue Foch", 300, TypePropriete.VERT),
        new CaseCaisseDeCommunaute("CDC3"),
        new CasePropriete("Bouelvard des Capucines", 320,TypePropriete.VERT),
        new CaseGare("Gare Saint-Lazare"),
        new CaseChance("CC3"),
        new CasePropriete("Avenue des Champs-Élysées", 350, TypePropriete.BLEU),
        new CaseTaxe("Taxe de Luxe", 150),
        new CasePropriete("Rue de la Paix", 400, TypePropriete.BLEU)
    };
    

    static private Queue<Card> deckCaisseDeCommunaute;
    static private Queue<Card> deckChance;

    //public parce qu'on s'en sert dans le main
    static public Queue<Player> listeJoueurs = new ArrayDeque<Player>();

    public Board(int nbJoueurs){
        //Crée tous les joueurs et les met dans la liste de joueurs  (pour le moment que des bots du même type)
        for (int i = 1; i <= nbJoueurs; i++){
            listeJoueurs.add(new Manual(i));
        }
        
        

    }

}
