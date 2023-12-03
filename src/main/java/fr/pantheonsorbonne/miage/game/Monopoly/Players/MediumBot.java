package fr.pantheonsorbonne.miage.game.Monopoly.Players;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseAchetable;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.TypePropriete;


public class MediumBot extends Player {
    int[] tableauBooleanReponse = new int[7];

    public MediumBot(int ID) {
        super(ID);
    }

    @Override
    public boolean askGetOutOfJail(PerfectBoard board) {
        int balance = this.getBankAccount();
        int countHouse = 0;
        if (balance < 500) {
            return false;
        }
        List<CaseAchetable> listeDesProp = board.getAllProprietes();
        for (CaseAchetable caseAchetable : listeDesProp) {
            if (! (caseAchetable.getOwner() == null)) {
                countHouse++;
            }
        }

        if (countHouse == listeDesProp.size()) {
            return false;
        }

        return true;
    }

    @Override
    public boolean askBuyProperty(CaseAchetable proprieteLibre, PerfectBoard plateauComplet) {
        int balance = this.getBankAccount();
        if (proprieteLibre.getTypeOuCouleur().equals(TypePropriete.BLEU)){  //si c'est une bleu oui car il va faire +200 juste après
            if(balance >= proprieteLibre.getPrixAchat()+400){ // au cas ou qlq'un tombe sur une carte steal + 10
                return true;
            }

        }
        if (balance < proprieteLibre.getPrixAchat() + 600){  //pas assez d'argent
            return false;            
        }
        else if(balance > proprieteLibre.getPrixAchat() + 600){ //on considère que si il reste 200 c'est suffisant
            return true;
        }
        return false;
    }

    @Override
    protected Map<TypePropriete, Integer> thinkAboutBuyingHouses(PerfectBoard plateauComplet) {
        Map<TypePropriete, Integer> listeDeSouhaits = new HashMap<>();
        boucleDesCouleurs: 
        for (TypePropriete couleur : TypePropriete.values()) {
            if (couleur.ordinal() < 8) {
                List<CasePropriete> casesDeCetteCouleur = plateauComplet.getProprietesByColor(couleur);
                for (CasePropriete propCol : casesDeCetteCouleur) {
                    if (propCol.hasOwner()) {
                        if (!propCol.getOwner().equals(this)) {
                            continue boucleDesCouleurs;
                        }
                    } else {
                        continue boucleDesCouleurs;
                    }
                }
                if (casesDeCetteCouleur.get(casesDeCetteCouleur.size() - 1).getNombreMaisons() < 5) {
                    if (casesDeCetteCouleur.get(0).getPrixMaisonUnitaire() < this.getBankAccount())
                        listeDeSouhaits.put(couleur, 1);
                        break;
                }
            }
        }

        return listeDeSouhaits;
    }
    

    @Override
    protected Map<TypePropriete, Integer> thinkAboutSellingHouses(PerfectBoard plateauComplet) {
        return new HashMap<TypePropriete, Integer>();
    }

    @Override
    protected CaseAchetable[] thinkAboutHypothequeProprietes(PerfectBoard plateauComplet) {
        return new CaseAchetable[0];
    }

    @Override
    protected CasePropriete[] thinkAboutCreatingJails(PerfectBoard plateauComplet) {
        return new CasePropriete[0];
    }

    @Override
    public boolean askRemoveInstantlySquat(CasePropriete ProprieteSquatee, PerfectBoard plateauComplet) {
        
        return false;
    }

    // public boolean[] think(PerfectBoard board, int balance, int positionPlayer)
    // throws IsBankruptException {
    // boolean[] choix = new boolean[7];
    // double[] proba = new double[11];

    // for (int i = 2; i < 13; i++) {
    // PerfectBoard board1 = new PerfectBoard();
    // int balanceBoucle1 = balance;

    // proba[i] = new ProbaPlayer(i).getProbaDes();

    // board1.walk(this, i);
    // board1.getCase(board1.getPositionJoueur(this)).doCaseEffect(this, board1);;

    // // for (int j = 0; j < 11; j++) {
    // // int balanceBoucle2 = balanceBoucle1;
    // // for (int k = 0; k < 11; k++) {
    // // int balanceBoucle3 = balanceBoucle2;
    // // for (int l = 0; l < 11; l++) {
    // // int balanceBoucle4 = balanceBoucle3;

    // // }
    // // }
    // // }
    // }

    // return choix;
    // }

}
