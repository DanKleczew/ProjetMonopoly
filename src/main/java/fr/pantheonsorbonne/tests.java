package fr.pantheonsorbonne;

import java.util.HashMap;

import fr.pantheonsorbonne.miage.game.Monopoly.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.Case;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.TypePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Manual;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class tests {
    
    public static void main(String[] args) throws IsBankruptException {
        Player Thierry = new Manual(1);
        PerfectBoard plateau = new PerfectBoard();
        for (Case currCase : plateau.getPlateau()){
            if (currCase instanceof CasePropriete && ((CasePropriete) currCase).getTypeOuCouleur() == TypePropriete.BLEU){
                ((CasePropriete) currCase).setOwner(Thierry, true);
            }
        }
            System.out.println("Après achat prop bleues :" + Thierry.getBankAccount());
            HashMap<TypePropriete, Integer> map = new HashMap<>();
            map.put(TypePropriete.BLEU, 2);
            plateau.addNumerousHouses(map);

            System.out.println("Après achat maisons" + Thierry.getBankAccount());

            for(Case currCase : plateau.getPlateau()){
                if (currCase instanceof CasePropriete && ((CasePropriete) currCase).getTypeOuCouleur() == TypePropriete.BLEU){
                    System.out.println(((CasePropriete) currCase).toString() + ((CasePropriete) currCase).getNombreMaisons());
                }
            }  
    }

}
