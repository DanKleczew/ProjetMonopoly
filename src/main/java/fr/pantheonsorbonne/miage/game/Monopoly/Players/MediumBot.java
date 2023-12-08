package fr.pantheonsorbonne.miage.game.Monopoly.Players;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseAchetable;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.TypePropriete;

public class MediumBot extends Player {
    int[] tableauBooleanReponse = new int[7];
    private int flagPriorityBuyHouse = 0;

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

            flagPriorityBuyHouse = 1;
            boucleDesCouleurs: for (TypePropriete couleur : TypePropriete.values()) {
                if (couleur.ordinal() > 2 && couleur.ordinal() < 5) {
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
                        flagPriorityBuyHouse = 0;
                        if (casesDeCetteCouleur.get(0).getPrixMaisonUnitaire() < moneyICanSpend) {
                            listeDeSouhaits.put(couleur, 1);
                            moneyICanSpend += -casesDeCetteCouleur.get(0).getPrixMaisonUnitaire();
                        }
                    }
                }

            }
        }
        if (flagPriorityBuyHouse == 1) {
            flagPriorityBuyHouse = 2;
            boucleDesCouleurs: for (TypePropriete couleur : TypePropriete.values()) {
                if ((couleur.ordinal() == 2 || couleur.ordinal() == 5 || couleur.ordinal() == 6)
                        || couleur.ordinal() >= 6 && couleur.ordinal() < 8) {
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
                        flagPriorityBuyHouse = 1;
                        if (casesDeCetteCouleur2.get(0).getPrixMaisonUnitaire() < moneyICanSpend) {
                            listeDeSouhaits.put(couleur, 1);
                            moneyICanSpend += -casesDeCetteCouleur2.get(0).getPrixMaisonUnitaire();

                        }
                    }
                }

            }
        }
        if (flagPriorityBuyHouse == 2) {
            boucleDesCouleurs: for (TypePropriete couleur : TypePropriete.values()) {
                if ((couleur.ordinal() == 0 || couleur.ordinal() == 1 || couleur.ordinal() == 7)
                        || couleur.ordinal() >= 6 && couleur.ordinal() < 8) {
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
        int money = this.getBankAccount();
        int minimumMoney = 300;
        Map<TypePropriete, Integer> listeDeSouhaits = new HashMap<>();

        if (money < minimumMoney) {
            boucleDesCouleurs: for (TypePropriete couleur : TypePropriete.values()) {
                if (couleur.ordinal() == 0 || couleur.ordinal() == 1
                        || couleur.ordinal() >= 6 && couleur.ordinal() < 8) {
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
                    if (casesDeCetteCouleur2.get(casesDeCetteCouleur2.size() - 1).getNombreMaisons() > 0) {
                        if (money < minimumMoney) {
                            money += casesDeCetteCouleur2.get(0).getPrixMaisonUnitaire() / 2;
                            listeDeSouhaits.put(couleur, 1);
                        } else {
                            break boucleDesCouleurs;
                        }
                    }
                }
            }
        }
        if (money < minimumMoney) {
            boucleDesCouleurs: for (TypePropriete couleur : TypePropriete.values()) {
                if (couleur.ordinal() == 2 || couleur.ordinal() == 5) {
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
                    if (casesDeCetteCouleur2.get(casesDeCetteCouleur2.size() - 1).getNombreMaisons() > 0) {
                        if (money < minimumMoney) {
                            money += casesDeCetteCouleur2.get(0).getPrixMaisonUnitaire() / 2;
                            listeDeSouhaits.put(couleur, 1);
                        } else {
                            break boucleDesCouleurs;
                        }
                    }
                }
            }
        }
        if (money < minimumMoney) {
            boucleDesCouleurs: for (TypePropriete couleur : TypePropriete.values()) {
                if (couleur.ordinal() == 3 || couleur.ordinal() == 4) {
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
                    if (casesDeCetteCouleur2.get(casesDeCetteCouleur2.size() - 1).getNombreMaisons() > 0) {
                        if (money < minimumMoney) {
                            money += casesDeCetteCouleur2.get(0).getPrixMaisonUnitaire() / 2;
                            listeDeSouhaits.put(couleur, 1);
                        } else {
                            break boucleDesCouleurs;
                        }
                    }
                }
            }

        }
        if (this.getBankAccount() < money) {
        }
        return listeDeSouhaits;
    }

    @Override
    protected CaseAchetable[] thinkAboutHypothequeProprietes(PerfectBoard plateauComplet) {
        int money = this.getBankAccount();
        int moneyMinimum = 300;
        if (this.getBankAccount() < moneyMinimum) {
            List<CasePropriete> listTransformJails = new ArrayList<CasePropriete>();
            List<CaseAchetable> listeDesProp = plateauComplet.getAllProprietes();
                brackPoint :for (TypePropriete couleur : TypePropriete.values()) {

                    if (couleur.ordinal() == 0 || couleur.ordinal() == 1
                        || couleur.ordinal() >= 6 && couleur.ordinal() < 8) {
                        List<CasePropriete> casesDeCetteCouleur = plateauComplet.getProprietesByColor(couleur);
                        bouclePossedeTouteLaCouleur: for (CasePropriete propCol : casesDeCetteCouleur) {
                            if (propCol.hasOwner()) {
                                if (!propCol.getOwner().equals(this)) {
                                        if(!propCol.isAJail()){
                                            if(!propCol.isHypothequed()){
                                                money += propCol.getPrixAchat()/2;
                                                listTransformJails.add(propCol);
                                            }
                                            
                                        }
                                }
                            }                            
                        }
                    }
                    if (money>moneyMinimum){
                        break brackPoint;
                    }
                }
                brackPoint :for (TypePropriete couleur : TypePropriete.values()) {

                    if (couleur.ordinal() == 5 || couleur.ordinal() == 2) {
                        List<CasePropriete> casesDeCetteCouleur = plateauComplet.getProprietesByColor(couleur);
                        bouclePossedeTouteLaCouleur: for (CasePropriete propCol : casesDeCetteCouleur) {
                            if (propCol.hasOwner()) {
                                if (!propCol.getOwner().equals(this)) {
                                        if(!propCol.isAJail()){
                                            if(!propCol.isHypothequed()){
                                                money += propCol.getPrixAchat()/2;
                                                listTransformJails.add(propCol);
                                            }
                                            
                                        }
                                }
                            }                            
                        }
                    }
                    if (money>moneyMinimum){
                        break brackPoint;
                    }
                }
                     brackPoint :for (TypePropriete couleur : TypePropriete.values()) {

                    if (couleur.ordinal() == 3 || couleur.ordinal() == 4){
                        List<CasePropriete> casesDeCetteCouleur = plateauComplet.getProprietesByColor(couleur);
                        bouclePossedeTouteLaCouleur: for (CasePropriete propCol : casesDeCetteCouleur) {
                            if (propCol.hasOwner()) {
                                if (!propCol.getOwner().equals(this)) {
                                        if(!propCol.isAJail()){
                                            if(!propCol.isHypothequed()){
                                                money += propCol.getPrixAchat()/2;
                                                listTransformJails.add(propCol);
                                            }
                                            
                                        }
                                }
                            }                            
                        
                    }
                    if (money>moneyMinimum){
                        break brackPoint;
                    }
                }
                }
            CasePropriete[] proprietesList = new CasePropriete[listTransformJails.size()];
            for (int i = 0; i < listTransformJails.size(); i++) {
                proprietesList[i] = listTransformJails.get(i);
            }

            return proprietesList;
        } else {
            return new CaseAchetable[0];
        }
    }

    @Override
    protected CasePropriete[] thinkAboutCreatingJails(PerfectBoard plateauComplet) {
        List<CasePropriete> listTransformJails = new ArrayList<CasePropriete>();
        List<CaseAchetable> listeDesProp = plateauComplet.getAllProprietes();
        int countHouse = 0;
        // int countNbTrioCarte = 0;
        boolean countOwner[] = new boolean[8];
        for (int i = 0; i < countOwner.length; i++) {
            countOwner[i] = true;
        }
        int indiceBoolean = 0;
        for (CaseAchetable caseAchetable : listeDesProp) {
            if (!(caseAchetable.getOwner() == null)) {
                countHouse++;
            }
        }
        if (countHouse > listeDesProp.size() - 2) {
            for (TypePropriete couleur : TypePropriete.values()) {

                if (couleur.ordinal() < 8) {
                    List<CasePropriete> casesDeCetteCouleur = plateauComplet.getProprietesByColor(couleur);
                    bouclePossedeTouteLaCouleur: for (CasePropriete propCol : casesDeCetteCouleur) {
                        if (propCol.hasOwner()) {
                            if (!propCol.getOwner().equals(this)) {
                                countOwner[indiceBoolean] = false;
                                continue bouclePossedeTouteLaCouleur;
                            }
                        } else {
                            countOwner[indiceBoolean] = false;
                        }
                    }
                    indiceBoolean++;
                }
            }
            indiceBoolean = 0;
            for (TypePropriete couleur : TypePropriete.values()) {

                if (couleur.ordinal() <= 2 || couleur.ordinal() >= 5) {
                    List<CasePropriete> casesDeCetteCouleur = plateauComplet.getProprietesByColor(couleur);
                    for (CasePropriete propCol : casesDeCetteCouleur) {
                        if (propCol.hasOwner()) {
                            if (propCol.getOwner().equals(this)) {
                                if (!propCol.isAJail()) {
                                    if (countOwner[indiceBoolean] == false) {
                                        listTransformJails.add(propCol);
                                    }
                                }
                            }
                        }
                    }
                }
                indiceBoolean++;
            }
        }
        CasePropriete[] proprietesList = new CasePropriete[listTransformJails.size()];
        for (int i = 0; i < listTransformJails.size(); i++) {
            proprietesList[i] = listTransformJails.get(i);
        }

        return proprietesList;
    }

    @Override
    public boolean askRemoveInstantlySquat(CasePropriete ProprieteSquatee, PerfectBoard plateauComplet) {
        System.out.println(this.getBankAccount());
        if (ProprieteSquatee.getTypeOuCouleur().equals(TypePropriete.ORANGE)
                || ProprieteSquatee.getTypeOuCouleur().equals(TypePropriete.JAUNE)
                || ProprieteSquatee.getTypeOuCouleur().equals(TypePropriete.ROUGE)) {
            if (this.getBankAccount() > 800) {
                if (ProprieteSquatee.getNombreMaisons() >= 2) {
                    return true;
                }
            }
        }

        return false;
    }
}
