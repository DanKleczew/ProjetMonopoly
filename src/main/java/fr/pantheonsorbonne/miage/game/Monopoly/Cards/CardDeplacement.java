package fr.pantheonsorbonne.miage.game.Monopoly.Cards;

import fr.pantheonsorbonne.miage.game.Monopoly.PhysicalGame;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseGare;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CardDeplacement implements Card {
    
    private int indiceDestination = -1;
    private int nombreDeCases = -1;

    public CardDeplacement(String nomDestinationCase, int indiceDestinationCase){
        this.indiceDestination = indiceDestinationCase;
    }
    public CardDeplacement(int nombreDeCase) {
        this.nombreDeCases = nombreDeCase;
    }
    public CardDeplacement(String nomDestinationCase){

    }

    @Override
    public void cardEffect(Player joueur) throws IsBankruptException {
        if (indiceDestination != -1){
            PhysicalGame.positionJoueurs.replace(joueur, indiceDestination);
        }
        else if (nombreDeCases != -1){
            int positionActuelle = PhysicalGame.positionJoueurs.get(joueur);
            PhysicalGame.positionJoueurs.replace(joueur, positionActuelle + nombreDeCases);
        }
        else {
            int positionActuelle = PhysicalGame.positionJoueurs.get(joueur);
            for (int i = positionActuelle; ; i++){
                if (PhysicalGame.plateau.getBoard()[i] instanceof CaseGare){
                    PhysicalGame.positionJoueurs.replace(joueur, i);
                    break;
                }
            }
        }
    }
    
}
