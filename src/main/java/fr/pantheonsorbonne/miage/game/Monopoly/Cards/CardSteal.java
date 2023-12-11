package fr.pantheonsorbonne.miage.game.Monopoly.Cards;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.PlayersManager;

public class CardSteal implements Card {

    // Card Steal = Cartes du type "C'est votre anniversaire tout le monde vous
    // donne 10"

    private int stealAmount;
    private final PlayersManager ensembleDesJoueurs;

    public CardSteal(int stealAmount, PlayersManager ensembleDesJoueurs) {
        this.stealAmount = stealAmount;
        this.ensembleDesJoueurs = ensembleDesJoueurs;
    }

    @Override
    public void cardEffect(Player joueurGagnant, PerfectBoard plateauComplet) throws IsBankruptException {
        // Joueur piochant la carte gagne son argent
        // Autres joueurs perdent de l'argent
        System.out.println("Tout le monde vous doit " + stealAmount + " $ !");

        // On crée cet Array pour éviter de modifier le Deque pendant qu'on l'itère :
        // risque de ConcurrentModificationException
        Player[] listeDeJoueurs = new Player[ensembleDesJoueurs.getListeJoueurs().size()];
        for (int i = 0; i < ensembleDesJoueurs.getListeJoueurs().size(); i++) {
            listeDeJoueurs[i] = (Player) ensembleDesJoueurs.getListeJoueurs().toArray()[i];
        }

        for (Player joueurCourant : listeDeJoueurs) {
            try {
                if (!joueurGagnant.equals(joueurCourant)) {
                    joueurCourant.transaction(joueurGagnant, stealAmount);
                }
            } catch (IsBankruptException exception) {
                exception.setGagnant(joueurGagnant);
                ensembleDesJoueurs.deletePlayer(exception, plateauComplet);
            }
            /*
             * On a un try/catch ici parce que c'est le seul moyen pour un joueur de perdre
             * /* Alors que ce n'est pas son tour. Pour toutes les autres manières de
             * perdre,
             * L'exception est traitée dans l'engine
             */
        }
    }
}
