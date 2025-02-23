package fr.pantheonsorbonne.miage.game.Monopoly.Cards;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CardSteal implements Card {

    // Card Steal = Cartes du type "C'est votre anniversaire tout le monde vous
    // donne 10"

    private int stealAmount;

    public CardSteal(int stealAmount) {
        this.stealAmount = stealAmount;
    }

    @Override
    public void cardEffect(Player joueurGagnant, PerfectBoard plateauComplet) throws IsBankruptException {
        // Joueur piochant la carte gagne son argent
        // Autres joueurs perdent de l'argent
        System.out.println("Tout le monde vous doit " + stealAmount + " $ !");

        //On crée cet Array pour éviter de modifier le Deque pendant qu'on l'itère : risque de ConcurrentModificationException
        Player[] listeDeJoueurs = new Player[plateauComplet.getListeJoueurs().size()];
        for (int i = 0 ; i<plateauComplet.getListeJoueurs().size() ; i++){
            listeDeJoueurs[i] = (Player) plateauComplet.getListeJoueurs().toArray()[i];
        }
        
        for (Player joueurCourant : listeDeJoueurs) {
            try {
                if (!joueurGagnant.equals(joueurCourant)) {
                    joueurCourant.transaction(joueurGagnant, stealAmount);
                }
            }
            catch (IsBankruptException exception){
                exception.setGagnant(joueurGagnant);
                plateauComplet.deletePlayer(exception);
            }
            /* On a un try/catch ici parce que c'est le seul moyen pour un joueur de perdre
            /* Alors que ce n'est pas son tour. Pour toutes les autres manières de perdre,
            L'exception est traitée dans l'engine */
        }
    }
}
