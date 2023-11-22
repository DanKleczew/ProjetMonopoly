package fr.pantheonsorbonne.miage.game.Monopoly.Cases;

import fr.pantheonsorbonne.miage.game.Monopoly.Board;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CaseCompagnie extends CaseAchetable {

    public CaseCompagnie(String name) {
        super(name, 150, TypePropriete.COMPAGNIE);
    }

    @Override
    public int getLoyerAPayer(Board plateauComplet) {
        Player owner = this.getOwner();
        int nombreCompagniesOwned = owner.getNumberSpecificTypeProperty(TypePropriete.COMPAGNIE,
                    plateauComplet.getOwnedProperties(owner));
        int nombreIndiqueParDes = plateauComplet.getSommeDesThisRound();
        int coefficient;
        if (nombreCompagniesOwned == 1) {
            coefficient = 4;

        } else {
            coefficient = 10;
        }
        return coefficient * nombreIndiqueParDes;
    }

}
