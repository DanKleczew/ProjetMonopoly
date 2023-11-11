package fr.pantheonsorbonne.miage.game.Monopoly.Cases;

import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CaseTaxe extends Case{

    private int valeurTaxe;

    public CaseTaxe(String name, int valeurTaxe) {
        super(name);
        this.valeurTaxe = valeurTaxe;
    }

    @Override
    protected void doCaseEffect(Player joueur) throws IsBankruptException{
        joueur.bankAccountModify(-valeurTaxe);
    }

}
