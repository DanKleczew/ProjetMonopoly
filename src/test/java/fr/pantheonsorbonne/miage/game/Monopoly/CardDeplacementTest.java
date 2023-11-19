package fr.pantheonsorbonne.miage.game.Monopoly;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.Monopoly.Cards.Card;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.CardDeplacement;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.CardGoToPrison;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.CardSteal;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.CardVoierie;
import fr.pantheonsorbonne.miage.game.Monopoly.Cards.CardWinLose;
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
import fr.pantheonsorbonne.miage.game.Monopoly.Board;
import fr.pantheonsorbonne.miage.game.Monopoly.PerfectBoard;

public class CardDeplacementTest{
    
    private int indiceDestinationPrecise = -1;
    private int nombreDeCases = -1;

    @Test
    public void cardEffect(Player joueur, PerfectBoard plateauComplet) throws IsBankruptException {
        int indiceDestination; //La destination à calculer selon le constructeur qui a été appellé

        if (indiceDestinationPrecise != -1){
            System.out.println("Rendez-vous à " + plateauComplet.getCase(indiceDestinationPrecise).toString());
            indiceDestination = indiceDestinationPrecise;
        }
        else if (nombreDeCases != -1){
            System.out.println("Avancez de "  + nombreDeCases + " cases.");
            indiceDestination = plateauComplet.getPositionJoueur(joueur) + nombreDeCases;
        }
        else {
            System.out.println("Rendez-vous à la prochaine gare");
            indiceDestination = plateauComplet.getIndiceNextGare(joueur);
        }

        plateauComplet.assignNewPosition(joueur, indiceDestination);
        }
        
    
    
}
