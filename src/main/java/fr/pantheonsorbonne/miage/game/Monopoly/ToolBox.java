package fr.pantheonsorbonne.miage.game.Monopoly;

import java.util.List;
import java.util.Map;

import fr.pantheonsorbonne.miage.game.Monopoly.Cases.Case;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseAchetable;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Dumb;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.VoidBot;

public class ToolBox {

    // public static Map<Integer, int[]> perfectBordToMap(String donne){

    // Map<Integer, int[]> map = new Map<Integer,int[]>() {

    // };

    // return map;
    // }
    public static PerfectBoard mapToPerfectBoard(Map<String, String> map) throws IsBankruptException {
        Player toi = new VoidBot(0);
        Player pasToi = new VoidBot(1);
        PerfectBoard plateauTampon = new PerfectBoard();
        List<CaseAchetable> listeDesProp = plateauTampon.allProprietes;
        for (Integer i = 0; i < listeDesProp.size(); i++) {
            CaseAchetable currProp = listeDesProp.get(i);
            String[] stringpetee = map.get(Integer.toString(i)).split(";");
            switch (stringpetee[0]) {
                case "1":
                    currProp.setOwner(toi, true);
                    break;
                case "2":
                    currProp.setOwner(pasToi, true);
                    break;
                default:
                    break;
            }
            if (stringpetee[1].equals("6")) {
                ((CasePropriete) currProp).setAsJail();
            } else {
                for (int j = 0; j < Integer.parseInt(stringpetee[1]); j++) {
                    ((CasePropriete) currProp).addHouse();
                }
            }
        }
        return null;
    }

    public static String perfectBoardToMap(PerfectBoard board) {

        return null;
    }

    public static CasePropriete StringToCasePropriete(String body) {

        return null;
    }

}
