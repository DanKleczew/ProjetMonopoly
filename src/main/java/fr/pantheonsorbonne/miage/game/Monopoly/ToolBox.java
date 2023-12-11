package fr.pantheonsorbonne.miage.game.Monopoly;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseAchetable;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.PlayersManager;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.VoidBot;

public class ToolBox {
    private ToolBox(){
    }

    public static PerfectBoard mapToPerfectBoard(Map<String, String> map, Player moi) {
        Player pasMoi = new VoidBot(-1);
        PerfectBoard plateauEphemere = new PerfectBoard(new PlayersManager(moi, pasMoi));
        List<CaseAchetable> listeDesProp = plateauEphemere.getAllProprietes();
        for (int i = 0; i < listeDesProp.size(); i++) {
            CaseAchetable currProp = listeDesProp.get(i);
            String[] ownerANDhouses = map.get(""+i).split(";");

            //----- Partie Owner

            switch (ownerANDhouses[0]) {
                case "1":
                    currProp.setOwner(moi);
                    break;
                case "2":
                    currProp.setOwner(pasMoi);
                    break;
                default:
                    break;
            }
            // ------ Partie statut Case

            if (ownerANDhouses[1].equals("6")) {
                currProp.switchHypothequeStatusFree();
            }
            else if (ownerANDhouses[1].equals("7")) {
                ((CasePropriete) currProp).setAsJail();
            }
            else if (currProp instanceof CasePropriete){
                ((CasePropriete) currProp).setHousesNoPay(Integer.parseInt(ownerANDhouses[1]));
            }
        }
        return plateauEphemere;
    }

    public static Map<String, String> perfectBoardToMap(PerfectBoard board, int playerID) {
        List<CaseAchetable> listeDesProp = board.getAllProprietes();
        Map<String, String> map = new HashMap<String, String>();
        int i = 0;
        for (CaseAchetable proprieteParticuliere : listeDesProp) {
            
            //------OWNER
            if (! proprieteParticuliere.hasOwner()){
                map.put(""+i, "0;0");
                i++;
                continue;
            }
            
            StringBuilder builder = new StringBuilder();

            if (proprieteParticuliere.getOwner().getID() == playerID){
                builder.append("1;");
            }
            else {
                builder.append("2;");
            }
            
            //-----Maisons / Case Neutralisée

            if (proprieteParticuliere.isHypothequed()){
                builder.append("6");
                map.put(""+i, builder.toString());
                i++;
                continue;
            }

            if (proprieteParticuliere instanceof CasePropriete) {
                CasePropriete proprieteColoree = (CasePropriete) proprieteParticuliere;

                if (proprieteColoree.isAJail()){
                    builder.append("7");
                }
                else {
                    builder.append(""+proprieteColoree.getNombreMaisons());
                }

                map.put(""+i, builder.toString());
                i++;
                continue;
            }
   
            else{
                builder.append("0");
            }

            map.put(""+i, builder.toString());
            i++;
        }
        return map;
    }
}
