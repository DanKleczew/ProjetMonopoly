// package fr.pantheonsorbonne.miage.game.Monopoly;

// import static org.junit.jupiter.api.Assertions.*;

// import org.junit.jupiter.api.Test;

// import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
// import fr.pantheonsorbonne.miage.game.Monopoly.Cases.*;
// import fr.pantheonsorbonne.miage.game.Monopoly.Players.*;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Properties;

// //import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
// import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseAchetable;
// import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
// import fr.pantheonsorbonne.miage.game.Monopoly.Cases.TypePropriete;

// public class MediumBotTest {

//     @Test
//     public void askGetOutOfJail() throws IsBankruptException {
//         MediumBot Thierry = new MediumBot(1);
//         PerfectBoard plateauFantome = new PerfectBoard(Thierry);
//         Thierry.setTimeOut(plateauFantome);
//         assertEquals(Thierry.askGetOutOfJail(plateauFantome), true);
//         Thierry.bankAccountModify(-1500);
//         assertEquals(Thierry.askGetOutOfJail(plateauFantome), false);
//     }

//     @Test
//     public void askBuyProperty() throws IsBankruptException {
//         MediumBot Thierry = new MediumBot(1);
//         PerfectBoard plateauFantome = new PerfectBoard(Thierry);
//         CaseAchetable proprieteLibre = new CasePropriete("Boulevard de Belleville", 60, TypePropriete.MARRON);
//         assertEquals(Thierry.askBuyProperty(proprieteLibre, plateauFantome), true);
//         Thierry.bankAccountModify(-1500);
//         assertEquals(Thierry.askBuyProperty(proprieteLibre, plateauFantome), false);
//     }

//     @Test
//     protected void thinkAboutBuyingHouses() throws IsBankruptException {
//         MediumBot Thierry = new MediumBot(1);
//         MediumBot Thierry2 = new MediumBot(1);
//         PerfectBoard plateauFantome = new PerfectBoard(Thierry, Thierry2);
//         for (CaseAchetable caseAchetable : plateauFantome.getAllColoredProprietes()) {
//             caseAchetable.setOwner(Thierry);
//         }
//         Thierry.thinkAndDo(plateauFantome);
//         assertEquals(0, plateauFantome.getAllColoredProprietes().get(5).getNombreMaisons());
//         assertEquals(0, plateauFantome.getAllColoredProprietes().get(6).getNombreMaisons());
//         assertEquals(0, plateauFantome.getAllColoredProprietes().get(7).getNombreMaisons());
//         assertEquals(1, plateauFantome.getAllColoredProprietes().get(8).getNombreMaisons());
//         assertEquals(0, plateauFantome.getAllColoredProprietes().get(9).getNombreMaisons());
//         assertEquals(0, plateauFantome.getAllColoredProprietes().get(10).getNombreMaisons());
//         assertEquals(1, plateauFantome.getAllColoredProprietes().get(11).getNombreMaisons());
//         assertEquals(0, plateauFantome.getAllColoredProprietes().get(12).getNombreMaisons());
//         assertEquals(0, plateauFantome.getAllColoredProprietes().get(13).getNombreMaisons());
//         assertEquals(0, plateauFantome.getAllColoredProprietes().get(14).getNombreMaisons());
//         assertEquals(0, plateauFantome.getAllColoredProprietes().get(15).getNombreMaisons());
//         assertEquals(0, plateauFantome.getAllColoredProprietes().get(16).getNombreMaisons());
//         Thierry.thinkAndDo(plateauFantome);
//         assertEquals(0, plateauFantome.getAllColoredProprietes().get(5).getNombreMaisons());
//         assertEquals(0, plateauFantome.getAllColoredProprietes().get(6).getNombreMaisons());
//         assertEquals(0, plateauFantome.getAllColoredProprietes().get(7).getNombreMaisons());
//         assertEquals(1, plateauFantome.getAllColoredProprietes().get(8).getNombreMaisons());
//         assertEquals(1, plateauFantome.getAllColoredProprietes().get(9).getNombreMaisons());
//         assertEquals(0, plateauFantome.getAllColoredProprietes().get(10).getNombreMaisons());
//         assertEquals(1, plateauFantome.getAllColoredProprietes().get(11).getNombreMaisons());
//         assertEquals(1, plateauFantome.getAllColoredProprietes().get(12).getNombreMaisons());
//         assertEquals(0, plateauFantome.getAllColoredProprietes().get(13).getNombreMaisons());
//         assertEquals(0, plateauFantome.getAllColoredProprietes().get(14).getNombreMaisons());
//         assertEquals(0, plateauFantome.getAllColoredProprietes().get(15).getNombreMaisons());
//         assertEquals(0, plateauFantome.getAllColoredProprietes().get(16).getNombreMaisons());
//         Thierry.thinkAndDo(plateauFantome);
//         assertEquals(0, plateauFantome.getAllColoredProprietes().get(5).getNombreMaisons());
//         assertEquals(0, plateauFantome.getAllColoredProprietes().get(6).getNombreMaisons());
//         assertEquals(0, plateauFantome.getAllColoredProprietes().get(7).getNombreMaisons());
//         assertEquals(1, plateauFantome.getAllColoredProprietes().get(8).getNombreMaisons());
//         assertEquals(1, plateauFantome.getAllColoredProprietes().get(9).getNombreMaisons());
//         assertEquals(1, plateauFantome.getAllColoredProprietes().get(10).getNombreMaisons());
//         assertEquals(1, plateauFantome.getAllColoredProprietes().get(11).getNombreMaisons());
//         assertEquals(1, plateauFantome.getAllColoredProprietes().get(12).getNombreMaisons());
//         assertEquals(1, plateauFantome.getAllColoredProprietes().get(13).getNombreMaisons());
//         assertEquals(0, plateauFantome.getAllColoredProprietes().get(14).getNombreMaisons());
//         assertEquals(0, plateauFantome.getAllColoredProprietes().get(15).getNombreMaisons());
//         assertEquals(0, plateauFantome.getAllColoredProprietes().get(16).getNombreMaisons());
//         Thierry.thinkAndDo(plateauFantome);
//         assertEquals(0, plateauFantome.getAllColoredProprietes().get(5).getNombreMaisons());
//         assertEquals(0, plateauFantome.getAllColoredProprietes().get(6).getNombreMaisons());
//         assertEquals(0, plateauFantome.getAllColoredProprietes().get(7).getNombreMaisons());
//         assertEquals(2, plateauFantome.getAllColoredProprietes().get(8).getNombreMaisons());
//         assertEquals(1, plateauFantome.getAllColoredProprietes().get(9).getNombreMaisons());
//         assertEquals(1, plateauFantome.getAllColoredProprietes().get(10).getNombreMaisons());
//         assertEquals(2, plateauFantome.getAllColoredProprietes().get(11).getNombreMaisons());
//         assertEquals(1, plateauFantome.getAllColoredProprietes().get(12).getNombreMaisons());
//         assertEquals(1, plateauFantome.getAllColoredProprietes().get(13).getNombreMaisons());
//         assertEquals(0, plateauFantome.getAllColoredProprietes().get(14).getNombreMaisons());
//         assertEquals(0, plateauFantome.getAllColoredProprietes().get(15).getNombreMaisons());
//         assertEquals(0, plateauFantome.getAllColoredProprietes().get(16).getNombreMaisons());
//     }

    // public Map<TypePropriete, Integer> mapBuyHouseForSpecificColor(PerfectBoard plateauComplet, int moneyICanSpend,
    //         int numColor1, int numColor2, int numColor3, int flagPriorityBuyHouse) {
    //     flagPriorityBuyHouse++;
    //     Map<TypePropriete, Integer> listeDeSouhaits = new HashMap<>();
    //     boucleDesCouleurs: for (TypePropriete couleur : TypePropriete.values()) {
    //         if (couleur.ordinal() == numColor1 || couleur.ordinal() == numColor2 || couleur.ordinal() == numColor3) {
    //             List<CasePropriete> casesDeCetteCouleur = plateauComplet.getProprietesByColor(couleur);
    //             for (CasePropriete propCol : casesDeCetteCouleur) {
    //                 if (propCol.hasOwner()) {
    //                     if (!propCol.getOwner().equals(this)) {
    //                         continue boucleDesCouleurs;
    //                     }
    //                 } else {
    //                     continue boucleDesCouleurs;
    //                 }
    //             }
    //             if (casesDeCetteCouleur.get(casesDeCetteCouleur.size() - 1).getNombreMaisons() < 5) {
    //                 flagPriorityBuyHouse--;
    //                 if (casesDeCetteCouleur.get(0).getPrixMaisonUnitaire() < moneyICanSpend) {
    //                     listeDeSouhaits.put(couleur, 1);
    //                     moneyICanSpend += -casesDeCetteCouleur.get(0).getPrixMaisonUnitaire();
    //                 }
    //             }
    //         }

    //     }
    //     return listeDeSouhaits;
    // }

    // public Map<TypePropriete, Integer> mergeTwoMapColor(Map<TypePropriete, Integer> map1,
    //         Map<TypePropriete, Integer> map2) {
    //     for (Map.Entry<TypePropriete, Integer> entry : map2.entrySet()) {
    //         TypePropriete k = entry.getKey();
    //         Integer v = entry.getValue();
    //         map1.put(k, v);
    //     }
    //     return map1;
    // }

    
//    protected Map<TypePropriete, Integer> thinkAboutSellingHouses(PerfectBoard plateauComplet) {
//         //int money = this.getBankAccount();
//         int minimumMoney = 300;
//         Map<TypePropriete, Integer> listeDeSouhaits = new HashMap<>();
//         Map<TypePropriete, Integer> listeDeSouhaitTresRentable = new HashMap<>();
//         Map<TypePropriete, Integer> listeDeSouhaitRentable = new HashMap<>();
//         Map<TypePropriete, Integer> listeDeSouhaitPeuRentable = new HashMap<>();
//         // if (money < minimumMoney) {
//         //     listeDeSouhaitPeuRentable = mapSellingHouseForSpecificColor(plateauComplet, money, minimumMoney, 0, 1, 7);
//         // }
//         // if (money < minimumMoney) {
//         //     listeDeSouhaitRentable = mapSellingHouseForSpecificColor(plateauComplet, money, minimumMoney, 6, 2, 5);
//         // }
//         // if (money < minimumMoney) {
//             listeDeSouhaitTresRentable = mapSellingHouseForSpecificColor(plateauComplet, money, minimumMoney, 3, 4, 4);
//         }
//         listeDeSouhaits = mergeTwoMapColor(listeDeSouhaits, listeDeSouhaitTresRentable);
//         listeDeSouhaits = mergeTwoMapColor(listeDeSouhaits, listeDeSouhaitRentable);
//         listeDeSouhaits = mergeTwoMapColor(listeDeSouhaits, listeDeSouhaitPeuRentable);
//         return listeDeSouhaits;
//     }

//     public Map<TypePropriete, Integer> mapSellingHouseForSpecificColor(PerfectBoard plateauComplet, int moneyIHave,
//             int minimumMoneyINedd, int numColor1, int numColor2, int numColor3) {
//         Map<TypePropriete, Integer> listeDeSouhaits = new HashMap<>();
//         boucleDesCouleurs: for (TypePropriete couleur : TypePropriete.values()) {
//             if (couleur.ordinal() == numColor1 || couleur.ordinal() == numColor2 || couleur.ordinal() == numColor3) {
//                 List<CasePropriete> casesDeCetteCouleur2 = plateauComplet.getProprietesByColor(couleur);
//                 for (CasePropriete propCol : casesDeCetteCouleur2) {
//                     if (propCol.hasOwner()) {
//                         if (!propCol.getOwner().equals(this)) {
//                             continue boucleDesCouleurs;
//                         }
//                     } else {
//                         continue boucleDesCouleurs;
//                     }
//                 }
//                 if (casesDeCetteCouleur2.get(casesDeCetteCouleur2.size() - 1).getNombreMaisons() > 0) {
//                     if (moneyIHave < minimumMoneyINedd) {
//                         moneyIHave += casesDeCetteCouleur2.get(0).getPrixMaisonUnitaire() / 2;
//                         listeDeSouhaits.put(couleur, 1);
//                     } else {
//                         break boucleDesCouleurs;
//                     }
//                 }
//             }
//         }
//         return listeDeSouhaits;
//     }

//     @Override
//     protected CaseAchetable[] thinkAboutHypothequeProprietes(PerfectBoard plateauComplet) {
//         int money = this.getBankAccount();
//         int moneyMinimum = 300;
//         List<CasePropriete> listeHypothequeNonRentableProprietes = new ArrayList<CasePropriete>();
//         List<CasePropriete> listeHypothequeRentableProprietes = new ArrayList<CasePropriete>();
//         List<CasePropriete> listeHypothequeTresRentableProprietes = new ArrayList<CasePropriete>();
//         if (money < moneyMinimum) {

//             listeHypothequeNonRentableProprietes = thinkAboutHypothequeSpecificProperties(plateauComplet, money,
//                     moneyMinimum, 0, 1, 7);
//             if (money < moneyMinimum) {
//                 listeHypothequeRentableProprietes = thinkAboutHypothequeSpecificProperties(plateauComplet, money,
//                         moneyMinimum, 5, 2, 6);
//             }
//             if (money < moneyMinimum) {
//                 listeHypothequeTresRentableProprietes = thinkAboutHypothequeSpecificProperties(plateauComplet, money,
//                         moneyMinimum, 3, 4, 4);
//             }

//             CasePropriete[] proprietesList = mergeListIntoTable(listeHypothequeNonRentableProprietes,
//                     listeHypothequeRentableProprietes, listeHypothequeTresRentableProprietes);
//             return proprietesList;
//         } else {
//             return new CaseAchetable[0];
//         }
//     }

//     public List<CasePropriete> thinkAboutHypothequeSpecificProperties(PerfectBoard plateauComplet, int moneyIHave,
//             int minimumMoneyINedd, int numColor1, int numColor2, int numColor3) {
//         List<CasePropriete> listTransformJails = new ArrayList<CasePropriete>();
//         brackPoint: for (TypePropriete couleur : TypePropriete.values()) {

//             if (couleur.ordinal() == numColor1 || couleur.ordinal() == numColor2 || couleur.ordinal() == numColor3) {
//                 List<CasePropriete> casesDeCetteCouleur = plateauComplet.getProprietesByColor(couleur);
//                 for (CasePropriete propCol : casesDeCetteCouleur) {
//                     if (propCol.hasOwner()) {
//                         if (!propCol.getOwner().equals(this)) {
//                             if (!propCol.isAJail()) {
//                                 if (!propCol.isHypothequed()) {
//                                     moneyIHave += propCol.getPrixAchat() / 2;
//                                     listTransformJails.add(propCol);
//                                 }

//                             }
//                         }
//                     }
//                 }
//             }
//             if (moneyIHave > minimumMoneyINedd) {
//                 break brackPoint;
//             }
//         }
//         return listTransformJails;
//     }

//     public CasePropriete[] mergeListIntoTable(List<CasePropriete>... list1) {
//         List<CasePropriete> combinedList = new ArrayList<>();
//         for (List<CasePropriete> list : list1) {
//             combinedList.addAll(list);
//         }
//         CasePropriete[] finalList = new CasePropriete[combinedList.size()];
//         finalList = combinedList.toArray(finalList);
//         return finalList;
//     }

//     @Override
//     protected CasePropriete[] thinkAboutCreatingJails(PerfectBoard plateauComplet) {
//         List<CasePropriete> listTransformJails = new ArrayList<CasePropriete>();
//         List<CaseAchetable> listeDesProp = plateauComplet.getAllProprietes();
//         int countHouse = 0;
//         // int countNbTrioCarte = 0;
//         boolean countOwner[] = new boolean[8];
//         for (int i = 0; i < countOwner.length; i++) {
//             countOwner[i] = true;
//         }
//         int indiceBoolean = 0;
//         for (CaseAchetable caseAchetable : listeDesProp) {
//             if (!(caseAchetable.getOwner() == null)) {
//                 countHouse++;
//             }
//         }
//         if (countHouse > listeDesProp.size() - 2) {
//             for (TypePropriete couleur : TypePropriete.values()) {

//                 if (couleur.ordinal() < 8) {
//                     List<CasePropriete> casesDeCetteCouleur = plateauComplet.getProprietesByColor(couleur);
//                     bouclePossedeTouteLaCouleur: for (CasePropriete propCol : casesDeCetteCouleur) {
//                         if (propCol.hasOwner()) {
//                             if (!propCol.getOwner().equals(this)) {
//                                 countOwner[indiceBoolean] = false;
//                                 continue bouclePossedeTouteLaCouleur;
//                             }
//                         } else {
//                             countOwner[indiceBoolean] = false;
//                         }
//                     }
//                     indiceBoolean++;
//                 }
//             }
//             indiceBoolean = 0;
//             for (TypePropriete couleur : TypePropriete.values()) {

//                 if (couleur.ordinal() <= 2 || couleur.ordinal() >= 5) {
//                     List<CasePropriete> casesDeCetteCouleur = plateauComplet.getProprietesByColor(couleur);
//                     for (CasePropriete propCol : casesDeCetteCouleur) {
//                         if (propCol.hasOwner()) {
//                             if (propCol.getOwner().equals(this)) {
//                                 if (!propCol.isAJail()) {
//                                     if (countOwner[indiceBoolean] == false) {
//                                         listTransformJails.add(propCol);
//                                     }
//                                 }
//                             }
//                         }
//                     }
//                 }
//                 indiceBoolean++;
//             }
//         }
//         CasePropriete[] proprietesList = new CasePropriete[listTransformJails.size()];
//         for (int i = 0; i < listTransformJails.size(); i++) {
//             proprietesList[i] = listTransformJails.get(i);
//         }

//         return proprietesList;
//     }

//     @Override
//     public boolean askRemoveInstantlySquat(CasePropriete ProprieteSquatee, PerfectBoard plateauComplet) {
//         System.out.println(this.getBankAccount());
//         if (ProprieteSquatee.getTypeOuCouleur().equals(TypePropriete.ORANGE)
//                 || ProprieteSquatee.getTypeOuCouleur().equals(TypePropriete.JAUNE)
//                 || ProprieteSquatee.getTypeOuCouleur().equals(TypePropriete.ROUGE)) {
//             if (this.getBankAccount() > 800) {
//                 if (ProprieteSquatee.getNombreMaisons() >= 2) {
//                     return true;
//                 }
//             }
//         }

//         return false;
//     }

// }
