package fr.pantheonsorbonne.miage.game.Monopoly.Players;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;



import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseAchetable;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;

public class PlayersManager {
    private Deque<Player> listeJoueurs = new ArrayDeque<Player>();

    public PlayersManager(Player... tableJoueur){
        for (Player joueur : tableJoueur) {
            listeJoueurs.add(joueur);
        }
    }

    public Deque<Player> getListeJoueurs() {
        return listeJoueurs;
    }

    

    public Player getPlayerByID(int ID) {
        for (Player joueur : listeJoueurs) {
            if (joueur.getID() == ID) {
                return joueur;
            }
        }
        return null;
    }

    public void deletePlayer(IsBankruptException exception, PerfectBoard plateauComplet) throws IsBankruptException {
        if (listeJoueurs.size() > 2) {
            List<CaseAchetable> listeProprietesPerdant = plateauComplet.getOwnedProperties(exception.getPerdant());
            for (CaseAchetable proprieteDuPerdant : listeProprietesPerdant) {
                if (exception.getGagnant() != null) {
                    proprieteDuPerdant.setOwner(exception.getGagnant());
                } else {
                    proprieteDuPerdant.resetOwner();
                }

                if (proprieteDuPerdant.isHypothequed()) {
                    proprieteDuPerdant.switchHypothequeStatusFree();
                }
                if (proprieteDuPerdant instanceof CasePropriete) {

                    ((CasePropriete) proprieteDuPerdant).resetNombreMaisons();
                    ((CasePropriete) proprieteDuPerdant).resetJail();
                }
            }
        }
        listeJoueurs.remove(exception.getPerdant());
    }

    public Player getNextPlayer() {
        Player nextPlayer;
        listeJoueurs.add(nextPlayer = listeJoueurs.poll());
        return nextPlayer;
    }

    public void resetPlayingStatusAllPlayers() {
        for (Player joueur : listeJoueurs) {
            joueur.switchPlayingStatus();
        }
    }

    public boolean isGameFinished() {
        return listeJoueurs.size() == 1;
    }
}
