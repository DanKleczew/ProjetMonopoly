package fr.pantheonsorbonne.miage.game.Monopoly;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.*;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.*;


public class ToolBoxTest {

    @Test
    public void perfectBoardToMapTest() {
        Player Thierry = new VoidBot(0);
        Player Didier = new VoidBot(1);
        PlayersManager list = new PlayersManager(Thierry, Didier);
        PerfectBoard plateauFantome = new PerfectBoard(list);
        
        plateauFantome.getAllColoredProprietes().get(0).setOwner(Thierry);
        plateauFantome.getAllColoredProprietes().get(1).setOwner(Thierry);
        plateauFantome.getAllColoredProprietes().get(2).setOwner(Thierry);
        plateauFantome.getAllColoredProprietes().get(3).setOwner(Thierry);

        plateauFantome.getAllColoredProprietes().get(0).setHousesNoPay(3);
        plateauFantome.getAllColoredProprietes().get(1).setHousesNoPay(3);
        plateauFantome.getAllColoredProprietes().get(2).switchHypothequeStatusFree();
        plateauFantome.getAllColoredProprietes().get(3).setAsJail();

        plateauFantome.getAllColoredProprietes().get(4).setOwner(Didier);
        plateauFantome.getAllProprietes().get(2).setOwner(Didier);
        
        Map<String, String> mapAttendue = new HashMap<>();

        mapAttendue.put("0", "1;3");
        mapAttendue.put("1", "1;3");
        mapAttendue.put("2", "2;0");
        mapAttendue.put("3", "1;6");
        mapAttendue.put("4", "1;7");
        mapAttendue.put("5", "2;0");
        for (int i = 6 ; i<28; i++){
            mapAttendue.put(""+i, "0;0");
        }

        assertEquals(true, plateauFantome.getAllColoredProprietes().get(2).isHypothequed());
        assertEquals(mapAttendue, ToolBox.perfectBoardToMap(plateauFantome, Thierry.getID()));
    }

    @Test
    public void mapToPerfectBoardTest(){

        Map<String, String> mapAttendue = new HashMap<>();
        Player Thierry = new VoidBot(0);
        mapAttendue.put("0", "1;3");
        mapAttendue.put("1", "1;3");
        mapAttendue.put("2", "0;0");
        mapAttendue.put("3", "1;6");
        mapAttendue.put("4", "1;7");
        mapAttendue.put("5", "2;0");
        for (int i = 6 ; i<28; i++){
            mapAttendue.put(""+i, "0;0");
        }

        PerfectBoard plateauReforme = ToolBox.mapToPerfectBoard(mapAttendue, Thierry);

        assertEquals(Thierry, plateauReforme.getAllColoredProprietes().get(0).getOwner());
        assertEquals(3, plateauReforme.getAllColoredProprietes().get(1).getNombreMaisons());
        assertEquals(true, plateauReforme.getAllColoredProprietes().get(2).isHypothequed());
        assertEquals(true, plateauReforme.getAllColoredProprietes().get(3).isAJail());
    }
}
