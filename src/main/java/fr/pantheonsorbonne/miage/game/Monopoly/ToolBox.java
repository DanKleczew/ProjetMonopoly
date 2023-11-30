package fr.pantheonsorbonne.miage.game.Monopoly;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.pantheonsorbonne.miage.game.Monopoly.Cases.Case;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseAchetable;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseGare;
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

    public static Map<String,String> perfectBoardToMap(PerfectBoard board, Player player) {
        Deque<Player> listeJoueurs = board.getListeJoueurs();
        List<CaseAchetable> listeDesProp = board.allProprietes;
        Map<String, String> map = new HashMap<String,String>();
        int i = 0;
        for (CaseAchetable proprieteParticuliere : listeDesProp ) {
            StringBuilder builder = new StringBuilder();
            if (proprieteParticuliere.getOwner().equals(player.getID())){
                builder.append("1;");
            }else{
                builder.append("2;");
            }
            if(proprieteParticuliere instanceof CasePropriete){
                CasePropriete propriete = (CasePropriete) proprieteParticuliere;
                if(propriete.isAJail()){
                    builder.append("6");
                }
                else{
                builder.append(Integer.toString(propriete.getNombreMaisons()));
                }
            }
            else if(proprieteParticuliere instanceof CaseGare){
                CaseGare propriete = (CaseGare) proprieteParticuliere;
                
            }

            map.put(Integer.toString(i), builder.toString());
            i++;
        }
        return map;


            // CaseAchetable currProp = listeDesProp.get(i);
            // String[] stringpetee = map.get(Integer.toString(i)).split(";");
            // switch (stringpetee[0]) {
            //     case "1":
            //         currProp.setOwner(toi, true);
            //         break;
            //     case "2":
            //         currProp.setOwner(pasToi, true);
            //         break;
            //     default:
            //         break;
            // }
            // if (stringpetee[1].equals("6")) {
            //     ((CasePropriete) currProp).setAsJail();
            // } else {
            //     for (int j = 0; j < Integer.parseInt(stringpetee[1]); j++) {
            //         ((CasePropriete) currProp).addHouse();
            //     }
            // }

    }

    public static CasePropriete StringToCasePropriete(String body) {

        return null;
    }

}
