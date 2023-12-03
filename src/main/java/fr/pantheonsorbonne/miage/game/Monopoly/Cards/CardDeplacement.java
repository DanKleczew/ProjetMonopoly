package fr.pantheonsorbonne.miage.game.Monopoly.Cards;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CardDeplacement implements Card {
    
    private int indiceDestinationPrecise = -1;
    private int nombreDeCases = -1;

    //Constructeur appelé en cas de téléportation ("Rendez-vous Rue de la Paix" par exemple)
    public CardDeplacement(String nomDestinationCase, int indiceDestinationCase){
        this.indiceDestinationPrecise = indiceDestinationCase;
    }
    //Constructeur appelé en cas d'avancée / reculée d'un nombre défini de case ("Reculez de 3 cases" par exemple)
    public CardDeplacement(int nombreDeCase) {
        this.nombreDeCases = nombreDeCase;
    }
    //Constructeur appelé en cas d'avancée "jusqu'à la prochaine gare"
    public CardDeplacement(String nomDestinationCase){

    }

    @Override
    public void cardEffect(Player joueur, PerfectBoard plateauComplet) throws IsBankruptException {
        int indiceDestination; //La destination à calculer selon le constructeur qui a été appellé

        if (indiceDestinationPrecise != -1){
            System.out.println("Rendez-vous à " + plateauComplet.getCaseByIndice(indiceDestinationPrecise).toString());
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
