package fr.pantheonsorbonne.miage.game.Monopoly.Players;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseAchetable;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.TypePropriete;

public class CleverBot extends Player {
    int[] tableauBooleanReponse = new int[7];
    private int flagPriorityBuyHouse = 0;

    public CleverBot(int ID) {
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
            if ((caseAchetable.hasOwner())) {
                countHouse++;
            }
        }
        if (countHouse > listeDesProp.size() - 2) {
            return listeDeSouhaits;
        }
        Map<TypePropriete, Integer> listeDeSouhaitTresRentable = new HashMap<>();
        Map<TypePropriete, Integer> listeDeSouhaitRentable = new HashMap<>();
        Map<TypePropriete, Integer> listeDeSouhaitPeuRentable = new HashMap<>();
        this.flagPriorityBuyHouse = 0;
        listeDeSouhaitTresRentable = mapBuyHouseForSpecificColor(plateauComplet, moneyICanSpend, 3, 4, 3);
        if (this.flagPriorityBuyHouse == 1) {
            listeDeSouhaitRentable = mapBuyHouseForSpecificColor(plateauComplet, moneyICanSpend, 2, 5, 6);
        }
        if (this.flagPriorityBuyHouse == 2) {
            listeDeSouhaitPeuRentable = mapBuyHouseForSpecificColor(plateauComplet, moneyICanSpend, 0, 1, 7);
        }
        listeDeSouhaits = mergeTwoMapColor(listeDeSouhaits, listeDeSouhaitTresRentable);
        listeDeSouhaits = mergeTwoMapColor(listeDeSouhaits, listeDeSouhaitRentable);
        listeDeSouhaits = mergeTwoMapColor(listeDeSouhaits, listeDeSouhaitPeuRentable);
        return listeDeSouhaits;
    }


    private Map<TypePropriete, Integer> mapBuyHouseForSpecificColor(PerfectBoard plateauComplet, int moneyICanSpend,
            int numColor1, int numColor2, int numColor3) {
        this.flagPriorityBuyHouse++;
        Map<TypePropriete, Integer> listeDeSouhaits = new HashMap<>();
        boucleDesCouleurs: for (TypePropriete couleur : TypePropriete.values()) {
            if (couleur.ordinal() == numColor1 || couleur.ordinal() == numColor2 || couleur.ordinal() == numColor3) {
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
                    this.flagPriorityBuyHouse--;
                    if (casesDeCetteCouleur.get(0).getPrixMaisonUnitaire() < moneyICanSpend) {
                        listeDeSouhaits.put(couleur, 1);
                        moneyICanSpend += -casesDeCetteCouleur.get(0).getPrixMaisonUnitaire();
                    }
                }
            }

        }
        return listeDeSouhaits;
    }

    private Map<TypePropriete, Integer> mergeTwoMapColor(Map<TypePropriete, Integer> map1,
            Map<TypePropriete, Integer> map2) {
        for (Map.Entry<TypePropriete, Integer> entry : map2.entrySet()) {
            TypePropriete k = entry.getKey();
            Integer v = entry.getValue();
            map1.put(k, v);
        }
        return map1;
    }

    @Override
    protected Map<TypePropriete, Integer> thinkAboutSellingHouses(PerfectBoard plateauComplet) {
        int money = this.getBankAccount();
        int minimumMoney = 300;
        Map<TypePropriete, Integer> listeDeSouhaits = new HashMap<>();
        Map<TypePropriete, Integer> listeDeSouhaitTresRentable = new HashMap<>();
        Map<TypePropriete, Integer> listeDeSouhaitRentable = new HashMap<>();
        Map<TypePropriete, Integer> listeDeSouhaitPeuRentable = new HashMap<>();
        if (money < minimumMoney) {
            listeDeSouhaitPeuRentable = mapSellingHouseForSpecificColor(plateauComplet, money, minimumMoney, 0, 1, 7);
        }

        if (money < minimumMoney) {
            listeDeSouhaitRentable = mapSellingHouseForSpecificColor(plateauComplet, money, minimumMoney, 6, 2, 5);
        }
        if (money < minimumMoney) {
            listeDeSouhaitTresRentable = mapSellingHouseForSpecificColor(plateauComplet, money, minimumMoney, 3, 4, 4);
        }
        listeDeSouhaits = mergeTwoMapColor(listeDeSouhaits, listeDeSouhaitTresRentable);
        listeDeSouhaits = mergeTwoMapColor(listeDeSouhaits, listeDeSouhaitRentable);
        listeDeSouhaits = mergeTwoMapColor(listeDeSouhaits, listeDeSouhaitPeuRentable);
        return listeDeSouhaits;
    }

    private Map<TypePropriete, Integer> mapSellingHouseForSpecificColor(PerfectBoard plateauComplet, int moneyIHave,
            int minimumMoneyINedd, int numColor1, int numColor2, int numColor3) {
        Map<TypePropriete, Integer> listeDeSouhaits = new HashMap<>();
        BreakPoint: for (TypePropriete couleur : TypePropriete.values()) {
            int indiceCaseCouleur = -1;
            if (couleur.ordinal() == numColor1 || couleur.ordinal() == numColor2 || couleur.ordinal() == numColor3) {

                List<CasePropriete> casesDeCetteCouleur2 = plateauComplet.getProprietesByColor(couleur);

                for (int i = 0; i < casesDeCetteCouleur2.size(); i++) {
                    indiceCaseCouleur++;
                    if (casesDeCetteCouleur2.get(indiceCaseCouleur).hasOwner()) {
                        if ((casesDeCetteCouleur2.get(indiceCaseCouleur).getOwner().getID() == this.getID())) {
                            if (casesDeCetteCouleur2.get(indiceCaseCouleur).getNombreMaisons() > 0) {
                                if (moneyIHave < minimumMoneyINedd) {
                                    moneyIHave += casesDeCetteCouleur2.get(0).getPrixMaisonUnitaire() / 2;
                                    listeDeSouhaits.put(couleur, 1);
                                } else {
                                    break BreakPoint;
                                }
                            }
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        return listeDeSouhaits;
    }

    @Override
    protected CaseAchetable[] thinkAboutHypothequeProprietes(PerfectBoard plateauComplet) {
        int money = this.getBankAccount();
        int moneyMinimum = 300;
        Map<CasePropriete, Integer> listeHypothequeNonRentableProprietes = new HashMap<>();
        Map<CasePropriete, Integer> listeHypothequeRentableProprietes = new HashMap<>();
        Map<CasePropriete, Integer> listeHypothequeTresRentableProprietes = new HashMap<>();
        if (money < moneyMinimum) {

            listeHypothequeNonRentableProprietes = thinkAboutHypothequeSpecificProperties(plateauComplet, money,
                    moneyMinimum, 0, 1, 7);
            money += getSommeHashMapHypothequeHouse(listeHypothequeNonRentableProprietes);

            if (money < moneyMinimum) {
                listeHypothequeRentableProprietes = thinkAboutHypothequeSpecificProperties(plateauComplet, money,
                        moneyMinimum, 5, 2, 6);
                money += getSommeHashMapHypothequeHouse(listeHypothequeRentableProprietes);
            }
            if (money < moneyMinimum) {
                listeHypothequeTresRentableProprietes = thinkAboutHypothequeSpecificProperties(plateauComplet, money,
                        moneyMinimum, 3, 4, 4);
                money += getSommeHashMapHypothequeHouse(listeHypothequeTresRentableProprietes);
            }

            CasePropriete[] proprietesList = mergeListIntoTable(listeHypothequeNonRentableProprietes,
                    listeHypothequeRentableProprietes, listeHypothequeTresRentableProprietes);
            return proprietesList;
        } else {
            return new CaseAchetable[0];
        }
    }

    private int getSommeHashMapHypothequeHouse(Map<CasePropriete, Integer> map) {
        int somme = 0;
        for (Map.Entry<CasePropriete, Integer> entry : map.entrySet()) {
            Integer v = entry.getValue();
            somme += v;
        }

        return somme;
    }

    private Map<CasePropriete, Integer> thinkAboutHypothequeSpecificProperties(PerfectBoard plateauComplet,
            int moneyIHave,
            int minimumMoneyINedd, int numColor1, int numColor2, int numColor3) {
        Map<CasePropriete, Integer> listTransformJails = new HashMap<>();
        brackPoint: for (TypePropriete couleur : TypePropriete.values()) {
            if (moneyIHave < minimumMoneyINedd) {
                if (couleur.ordinal() == numColor1 || couleur.ordinal() == numColor2
                        || couleur.ordinal() == numColor3) {
                    List<CasePropriete> casesDeCetteCouleur = plateauComplet.getProprietesByColor(couleur);
                    for (CasePropriete propCol : casesDeCetteCouleur) {
                        if (propCol.hasOwner()) {
                            if (propCol.getOwner().equals(this)) {
                                if (!propCol.isAJail()) {
                                    if (!propCol.isHypothequed()) {
                                        moneyIHave += propCol.getPrixAchat() / 2;
                                        listTransformJails.put(propCol, propCol.getPrixAchat() / 2);
                                    }

                                }
                            }
                        }
                    }
                }
            }
            if (moneyIHave > minimumMoneyINedd) {
                break brackPoint;
            }
        }
        return listTransformJails;
    }

    private CasePropriete[] mergeListIntoTable(Map<CasePropriete, Integer> list1, Map<CasePropriete, Integer> list2,
            Map<CasePropriete, Integer> list3) {

        Map<CasePropriete, Integer> combinedMap = new HashMap<>();
        combinedMap.putAll(list1);
        combinedMap.putAll(list2);
        combinedMap.putAll(list3);

        CasePropriete[] finalList = new CasePropriete[combinedMap.size()];
        int indiceMap = 0;
        for (Map.Entry<CasePropriete, Integer> entry : combinedMap.entrySet()) {
            CasePropriete v = entry.getKey();
            finalList[indiceMap] = v;
            indiceMap++;
        }
        return finalList;
    }

    @Override
    protected CasePropriete[] thinkAboutCreatingJails(PerfectBoard plateauComplet) {

        List<CasePropriete> listTransformJails = new ArrayList<CasePropriete>();
        List<CasePropriete> listeDesProp = plateauComplet.getAllColoredProprietes();
        int countHouse = 0;
        boolean countOwner[] = new boolean[8];
        for (int i = 0; i < countOwner.length; i++) {
            countOwner[i] = true;
        }
        int indiceBoolean = 0;
        for (CaseAchetable caseAchetable : listeDesProp) {
            if (caseAchetable.hasOwner()) {
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
