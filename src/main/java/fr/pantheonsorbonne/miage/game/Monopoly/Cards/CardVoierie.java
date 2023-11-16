package fr.pantheonsorbonne.miage.game.Monopoly.Cards;

import fr.pantheonsorbonne.miage.game.Monopoly.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CardVoierie implements Card{
    
    //Card Voierie = Cartes qui demandent de payer un certain montant pour chaque maison et chaque hôtel du joueur

    private int prixParMaison;
    private int prixParHotel;

    public CardVoierie(int prixParMaison, int prixParHotel) {
        this.prixParMaison = prixParMaison;
        this.prixParHotel = prixParHotel;
    }

    @Override
    public void cardEffect(Player joueur, PerfectBoard plateau) throws IsBankruptException {
        System.out.println("Payez " + prixParMaison + "par maison et " + prixParHotel + "par hôtel que vous possédez");
        int[] nombreImmobilier = plateau.getNombreMaisonsHotels(joueur);
        int prixAPayer = nombreImmobilier[1]*prixParHotel + nombreImmobilier[0]*prixParMaison;
        joueur.bankAccountModify(-prixAPayer);
    }
    
}
