package fr.pantheonsorbonne.miage.game.Monopoly.Cases;

import java.util.Objects;

import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CaseGare extends CaseAchetable {

    public CaseGare(String name) {
        super(name, 200, TypePropriete.GARE);
        //TODO Auto-generated constructor stub
    }


    @Override
    public void pay(Player joueurQuiPaye) throws IsBankruptException {
        Player owner = this.getOwner();
        int nombreGare = owner.getNumberSpecificProperty(TypePropriete.GARE,owner.getOwnedProperties());
        if(Objects.equals(joueurQuiPaye, owner)){
            return;
        }
        else{
            int aPayer = 25*((int) Math.pow(2, nombreGare-1));
            joueurQuiPaye.bankAccountModify(-aPayer);

        }
    }
    
}
