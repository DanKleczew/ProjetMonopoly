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
    private boolean flagPriorityBuyHouse = true;

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
            if (!(caseAchetable.getOwner() == null)) {
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
        if (proprieteLibre.getTypeOuCouleur().equals(TypePropriete.BLEU)) { // si c'est une bleu oui car il va faire
                                                                            // +200 juste après
            if (balance >= proprieteLibre.getPrixAchat() + 400) { // au cas ou qlq'un tombe sur une carte steal + 10
                return true;
            }

        }
        if (balance > proprieteLibre.getPrixAchat() + 600) { // pas assez d'argent
            return true;
        } else { // on considère que si il reste 200 c'est suffisant
            return false;
        }
    }

    @Override
    protected Map<TypePropriete, Integer> thinkAboutBuyingHouses(PerfectBoard plateauComplet) {
        int moneyICanSpend = this.getBankAccount() - 600;
        Map<TypePropriete, Integer> listeDeSouhaits = new HashMap<>();
        List<CaseAchetable> listeDesProp = plateauComplet.getAllProprietes();
        int countHouse = 0;
        for (CaseAchetable caseAchetable : listeDesProp) {
            if (!(caseAchetable.getOwner() == null)) {
                countHouse++;
            }
        }
        if (countHouse < listeDesProp.size() - 2) {
            return listeDeSouhaits;
        } else {
            flagPriorityBuyHouse = true;
                boucleDesCouleurs: for (TypePropriete couleur : TypePropriete.values()) {
                    if (/*couleur.ordinal() > 1 && */couleur.ordinal() < 6) {
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
                            flagPriorityBuyHouse = false;
                            if (casesDeCetteCouleur.get(0).getPrixMaisonUnitaire() < moneyICanSpend) {
                                listeDeSouhaits.put(couleur, 1);
                                moneyICanSpend += -casesDeCetteCouleur.get(0).getPrixMaisonUnitaire();
                            }
                        }
                    }

                }
            } if (flagPriorityBuyHouse) {
                boucleDesCouleurs: for (TypePropriete couleur : TypePropriete.values()) {
                    if ((couleur.ordinal() >= 0 && couleur.ordinal() <= 1) || couleur.ordinal() >= 6 && couleur.ordinal() <8) {
                        List<CasePropriete> casesDeCetteCouleur2 = plateauComplet.getProprietesByColor(couleur);
                        for (CasePropriete propCol : casesDeCetteCouleur2) {
                            if (propCol.hasOwner()) {
                                if (!propCol.getOwner().equals(this)) {
                                    continue boucleDesCouleurs;
                                }
                            } else {
                                continue boucleDesCouleurs;
                            }
                        }
                        if (casesDeCetteCouleur2.get(casesDeCetteCouleur2.size() - 1).getNombreMaisons() < 5) {
                            if (casesDeCetteCouleur2.get(0).getPrixMaisonUnitaire() < moneyICanSpend) {
                                listeDeSouhaits.put(couleur, 1);
                                moneyICanSpend += -casesDeCetteCouleur2.get(0).getPrixMaisonUnitaire();

                            }
                        }
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

}
