package fr.pantheonsorbonne.miage.game.Monopoly.Cases;

import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CaseCompagnie extends CaseAchetable {

    public CaseCompagnie(String name) {
        super(name, 150);
        //TODO Auto-generated constructor stub
    }

    @Override
    protected void doCaseEffect(Player joueur) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'doCaseEffect'");
    }
    
    @Override
    protected void pay(Player joueurQuiPaye) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pay'");
    }

}
