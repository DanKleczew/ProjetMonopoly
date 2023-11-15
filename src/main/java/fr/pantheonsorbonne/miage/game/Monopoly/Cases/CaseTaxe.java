package fr.pantheonsorbonne.miage.game.Monopoly.Cases;

import fr.pantheonsorbonne.miage.game.Monopoly.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CaseTaxe extends Case{

    private int valeurTaxe;

    public CaseTaxe(String name, int valeurTaxe) {
        super(name);
        this.valeurTaxe = valeurTaxe;
    }

    @Override
    public void doCaseEffect(Player joueur, PerfectBoard plateau) throws IsBankruptException{
        super.doCaseEffect(joueur, plateau);
        joueur.bankAccountModify(-valeurTaxe);
    }

}
