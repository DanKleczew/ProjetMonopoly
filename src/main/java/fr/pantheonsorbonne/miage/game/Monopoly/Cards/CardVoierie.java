package fr.pantheonsorbonne.miage.game.Monopoly.Cards;

import fr.pantheonsorbonne.miage.game.Monopoly.PhysicalGame;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.Case;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CardVoierie implements Card{
    private int prixParMaison;
    private int prixParHotel;
    public CardVoierie(int maison, int hotel) {
    }

    @Override
    public void cardEffect(Player joueur) throws IsBankruptException {
        int nombreMaisons = 0; int nombreHotels = 0;
        for (Case caseParticuliere : PhysicalGame.plateau.getBoard()){
            if (caseParticuliere instanceof CasePropriete && ((CasePropriete) caseParticuliere).getOwner().equals(joueur)){
                nombreMaisons += ((CasePropriete) caseParticuliere).getNombreMaisons();
                if (((CasePropriete) caseParticuliere).hasHotel()){
                    nombreHotels++;
                }
            }
        }
        joueur.bankAccountModify(-(nombreHotels*prixParHotel + nombreMaisons*prixParMaison));
    }
    
}
