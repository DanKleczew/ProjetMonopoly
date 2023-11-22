package fr.pantheonsorbonne.miage.game.Monopoly.Cases;

import fr.pantheonsorbonne.miage.game.Monopoly.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CaseGare extends CaseAchetable {

    public CaseGare(String name) {
        super(name, 200, TypePropriete.GARE);
    }

    @Override
    public int getLoyerAPayer(PerfectBoard plateauComplet) {
        Player owner = this.getOwner();
        int nombreGaresOwned = owner.getNumberSpecificTypeProperty(TypePropriete.GARE, plateauComplet.getOwnedProperties(owner));
        int aPayer = (int) (25 * ( Math.pow(2, nombreGaresOwned - 1))); //25 * 2 ^ nbGaresOwned
                
            return aPayer;
    }

    

}
