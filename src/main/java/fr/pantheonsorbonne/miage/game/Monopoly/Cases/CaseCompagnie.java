package fr.pantheonsorbonne.miage.game.Monopoly.Cases;

import fr.pantheonsorbonne.miage.game.Monopoly.Board;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CaseCompagnie extends CaseAchetable {

    public CaseCompagnie(String name) {
        super(name, 150, TypePropriete.COMPAGNIE);
    }

    @Override
    protected void makePay(Player joueurQuiPaye) throws IsBankruptException {
        Player owner = this.getOwner();
        if (joueurQuiPaye.equals(owner)) { // Le proprio de la case est tombé sur une case à lui
            return;
        } else {
            int nombreCompagniesOwned = owner.getNumberSpecificTypeProperty(TypePropriete.COMPAGNIE,
                    Board.getOwnedProperties(owner));
            int nombreIndiqueParDes = Board.getSommeDesThisRound();
            int coefficient;
            if (nombreCompagniesOwned == 1) {
                coefficient = 4;

            } else {
                coefficient = 10;

            }
            joueurQuiPaye.transaction(owner, coefficient * nombreIndiqueParDes);
        }
    }

}
