package fr.pantheonsorbonne.miage.game.Monopoly.Players;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseAchetable;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.TypePropriete;

public class Dumb extends Player {

    public Dumb(int ID) {
        super(ID);
    }

    @Override
    public boolean askGetOutOfJail( PerfectBoard board) {
        return (this.getBankAccount() > 50);
    }

    @Override
    public boolean askBuyProperty(CaseAchetable proprieteLibre, PerfectBoard plateauComplet) {
        return proprieteLibre.getPrixAchat() < this.getBankAccount()+1;
    }

    @Override
    protected Map<TypePropriete, Integer> thinkAboutBuyingHouses(PerfectBoard plateauComplet) {
        Map<TypePropriete, Integer> listeDeSouhaits = new HashMap<>();
        boucleDesCouleurs: for (TypePropriete couleur : TypePropriete.values()) {
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
        List<CaseAchetable> mesProprietes = plateauComplet.getOwnedProperties(this);
        if (mesProprietes.size() != 1)
            return new CaseAchetable[0];
        for (CaseAchetable currCase : mesProprietes) {
            if (currCase.toString().equals("Boulevard de Belleville")) {
                return new CaseAchetable[] { currCase };
            }
        }
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

}
