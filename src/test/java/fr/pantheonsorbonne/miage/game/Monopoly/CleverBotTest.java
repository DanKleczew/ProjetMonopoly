package fr.pantheonsorbonne.miage.game.Monopoly;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;²
import fr.pantheonsorbonne.miage.game.Monopoly.Players.*;

import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseAchetable;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.TypePropriete;

public class CleverBotTest {

    @Test
    public void askGetOutOfJailTest() throws IsBankruptException {
        CleverBot Thierry = new CleverBot(1);
        PerfectBoard plateauFantome = new PerfectBoard(Thierry);
        Thierry.setTimeOut(plateauFantome);
        assertEquals(Thierry.askGetOutOfJail(plateauFantome), true);
        Thierry.bankAccountModify(-1500);
        assertEquals(Thierry.askGetOutOfJail(plateauFantome), false);
    }

    @Test
    public void askBuyPropertyTest() throws IsBankruptException {
        CleverBot Thierry = new CleverBot(1);
        PerfectBoard plateauFantome = new PerfectBoard(Thierry);
        CaseAchetable proprieteLibre = new CasePropriete("Boulevard de Belleville", 60, TypePropriete.MARRON);
        assertEquals(Thierry.askBuyProperty(proprieteLibre, plateauFantome), true);
        Thierry.bankAccountModify(-1500);
        assertEquals(Thierry.askBuyProperty(proprieteLibre, plateauFantome), false);
    }

    @Test
    protected void thinkAboutBuyingHousesTest() throws IsBankruptException {
        CleverBot Thierry = new CleverBot(1);
        PerfectBoard plateauFantome = new PerfectBoard(Thierry);
        Thierry.bankAccountModify(1000000);
        Thierry.thinkAndDo(plateauFantome);
        for (int i = 0 ; i<plateauFantome.getAllColoredProprietes().size(); i++) {
            assertEquals(0, plateauFantome.getAllColoredProprietes().get(i).getNombreMaisons());
        }
        for (CaseAchetable caseAchetable : plateauFantome.getAllColoredProprietes()) {
            caseAchetable.setOwner(Thierry);
        }
        Thierry.thinkAndDo(plateauFantome);
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(5).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(6).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(7).getNombreMaisons());

        assertEquals(1, plateauFantome.getAllColoredProprietes().get(8).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(9).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(10).getNombreMaisons());

        assertEquals(1, plateauFantome.getAllColoredProprietes().get(11).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(12).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(13).getNombreMaisons());

        assertEquals(0, plateauFantome.getAllColoredProprietes().get(14).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(15).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(16).getNombreMaisons());
        Thierry.thinkAndDo(plateauFantome);
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(5).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(6).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(7).getNombreMaisons());

        assertEquals(1, plateauFantome.getAllColoredProprietes().get(8).getNombreMaisons());
        assertEquals(1, plateauFantome.getAllColoredProprietes().get(9).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(10).getNombreMaisons());

        assertEquals(1, plateauFantome.getAllColoredProprietes().get(11).getNombreMaisons());
        assertEquals(1, plateauFantome.getAllColoredProprietes().get(12).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(13).getNombreMaisons());

        assertEquals(0, plateauFantome.getAllColoredProprietes().get(14).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(15).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(16).getNombreMaisons());
        Thierry.thinkAndDo(plateauFantome);
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(5).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(6).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(7).getNombreMaisons());

        assertEquals(1, plateauFantome.getAllColoredProprietes().get(8).getNombreMaisons());
        assertEquals(1, plateauFantome.getAllColoredProprietes().get(9).getNombreMaisons());
        assertEquals(1, plateauFantome.getAllColoredProprietes().get(10).getNombreMaisons());

        assertEquals(1, plateauFantome.getAllColoredProprietes().get(11).getNombreMaisons());
        assertEquals(1, plateauFantome.getAllColoredProprietes().get(12).getNombreMaisons());
        assertEquals(1, plateauFantome.getAllColoredProprietes().get(13).getNombreMaisons());

        assertEquals(0, plateauFantome.getAllColoredProprietes().get(14).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(15).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(16).getNombreMaisons());
        Thierry.thinkAndDo(plateauFantome);
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(5).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(6).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(7).getNombreMaisons());

        assertEquals(2, plateauFantome.getAllColoredProprietes().get(8).getNombreMaisons());
        assertEquals(1, plateauFantome.getAllColoredProprietes().get(9).getNombreMaisons());
        assertEquals(1, plateauFantome.getAllColoredProprietes().get(10).getNombreMaisons());

        assertEquals(2, plateauFantome.getAllColoredProprietes().get(11).getNombreMaisons());
        assertEquals(1, plateauFantome.getAllColoredProprietes().get(12).getNombreMaisons());
        assertEquals(1, plateauFantome.getAllColoredProprietes().get(13).getNombreMaisons());

        assertEquals(0, plateauFantome.getAllColoredProprietes().get(14).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(15).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(16).getNombreMaisons());
        for (int i = 0 ; i<100 ; i++){
            Thierry.thinkAndDo(plateauFantome);
        }
        Thierry.bankAccountModify(100000000);
        for (int i = 0 ; i< plateauFantome.getAllColoredProprietes().size(); i++){
            assertEquals(5, plateauFantome.getAllColoredProprietes().get(i).getNombreMaisons());
        }
    }

    @Test
   protected void thinkAboutSellingHousesTest() throws IsBankruptException {
        CleverBot Thierry = new CleverBot(1);
        PerfectBoard plateauFantome = new PerfectBoard(Thierry);
        Thierry.thinkAndDo(plateauFantome);
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(8).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(9).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(10).getNombreMaisons());

        assertEquals(0, plateauFantome.getAllColoredProprietes().get(11).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(12).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(13).getNombreMaisons());
        for (CaseAchetable caseAchetable : plateauFantome.getAllColoredProprietes()) {
            caseAchetable.setOwner(Thierry);
        }
        Thierry.thinkAndDo(plateauFantome);
        assertEquals(1, plateauFantome.getAllColoredProprietes().get(8).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(9).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(10).getNombreMaisons());

        assertEquals(1, plateauFantome.getAllColoredProprietes().get(11).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(12).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(13).getNombreMaisons());
        Thierry.bankAccountModify(+plateauFantome.getAllColoredProprietes().get(8).getPrixMaisonUnitaire());
        Thierry.bankAccountModify(+plateauFantome.getAllColoredProprietes().get(11).getPrixMaisonUnitaire());
        Thierry.bankAccountModify(-1500);
        Thierry.thinkAndDo(plateauFantome);
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(8).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(9).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(10).getNombreMaisons());

        assertEquals(0, plateauFantome.getAllColoredProprietes().get(11).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(12).getNombreMaisons());
        assertEquals(0, plateauFantome.getAllColoredProprietes().get(13).getNombreMaisons());
        Thierry.thinkAndDo(plateauFantome);
    }

    @Test
    protected void thinkAboutHypothequeProprietes() throws IsBankruptException {
        CleverBot Thierry = new CleverBot(1);
        PerfectBoard plateauFantome = new PerfectBoard(Thierry);
        for (CaseAchetable caseAchetable : plateauFantome.getAllColoredProprietes()) {
            caseAchetable.setOwner(Thierry);
        }
        Thierry.bankAccountModify(-1500);
        assertEquals(Thierry.getBankAccount(), 0);
        Thierry.thinkAndDo(plateauFantome);
        assertEquals(Thierry.getBankAccount(), 595);
        Thierry.bankAccountModify(-595);
        Thierry.thinkAndDo(plateauFantome);
        assertEquals(Thierry.getBankAccount(), 620);
        Thierry.bankAccountModify(-620);
        Thierry.thinkAndDo(plateauFantome);
        assertEquals(Thierry.getBankAccount(), 460);
        Thierry.bankAccountModify(-460);
        Thierry.thinkAndDo(plateauFantome);
        assertEquals(Thierry.getBankAccount(), 620);
        Thierry.bankAccountModify(-620);
        Thierry.thinkAndDo(plateauFantome);
        assertEquals(Thierry.getBankAccount(), 0);
    }


    @Test
    protected void thinkAboutCreatingJails() throws IsBankruptException {
        CleverBot Thierry = new CleverBot(1);
        CleverBot Thierry2 = new CleverBot(2);
        PerfectBoard plateauFantome = new PerfectBoard(Thierry,Thierry2);
        for (CaseAchetable caseAchetable : plateauFantome.getAllColoredProprietes()) {
            caseAchetable.setOwner(Thierry);
        }
        plateauFantome.getAllColoredProprietes().get(0).setOwner(Thierry2);
        plateauFantome.getAllColoredProprietes().get(2).setOwner(Thierry2);
        Thierry.thinkAndDo(plateauFantome);
        assertEquals(true, plateauFantome.getAllColoredProprietes().get(1).isAJail());
        assertEquals(true, plateauFantome.getAllColoredProprietes().get(3).isAJail());
        assertEquals(true, plateauFantome.getAllColoredProprietes().get(4).isAJail());
    }

    @Test
    public void askRemoveInstantlySquat() {
        CleverBot Thierry = new CleverBot(1);
        PerfectBoard plateauFantome = new PerfectBoard(Thierry);
        for (CaseAchetable caseAchetable : plateauFantome.getAllColoredProprietes()) {
            caseAchetable.setOwner(Thierry);
        }
        plateauFantome.getAllColoredProprietes().get(12).setSquat();
        plateauFantome.getAllColoredProprietes().get(12).setHousesNoPay(3);
        assertEquals(Thierry.askRemoveInstantlySquat(plateauFantome.getAllColoredProprietes().get(12), plateauFantome), true);
        assertEquals(Thierry.askRemoveInstantlySquat(plateauFantome.getAllColoredProprietes().get(10), plateauFantome), false);
    }

 }
