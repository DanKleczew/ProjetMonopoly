package fr.pantheonsorbonne.miage.game.Monopoly.Cases;

import fr.pantheonsorbonne.miage.game.Monopoly.Board;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CaseGare extends CaseAchetable {

    public CaseGare(String name) {
        super(name, 200, TypePropriete.GARE);
    }

    @Override
    public void makePay(Player joueurQuiPaye) throws IsBankruptException {
        Player owner = this.getOwner();
        if (joueurQuiPaye.equals(owner)) { //Le proprio de la case est tombé sur une case à lui
            return;
        } else {
            int nombreGaresOwned = owner.getNumberSpecificTypeProperty(TypePropriete.GARE, Board.getOwnedProperties(owner));
            int aPayer = (int) (25 * ( Math.pow(2, nombreGaresOwned - 1))); //25 * 2 ^ nbGaresOwned
                
            joueurQuiPaye.transaction(owner, aPayer);
        }
    }

}
