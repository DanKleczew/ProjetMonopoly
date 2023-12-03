package fr.pantheonsorbonne.miage.game.Monopoly;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.Case;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseAchetable;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.VoidBot;

public class ToolBox {

    public static PerfectBoard mapToPerfectBoard(Map<String, String> map, Player moi) throws IsBankruptException {
        Player pasMoi = new VoidBot(-1);
        PerfectBoard plateauEphemere = new PerfectBoard();
        List<CaseAchetable> listeDesProp = plateauEphemere.getAllProprietes();
        for (Integer i = 0; i < listeDesProp.size(); i++) {
            CaseAchetable currProp = listeDesProp.get(i);
            String[] ownerANDhouses = map.get(Integer.toString(i)).split(";");

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
                ((CasePropriete) currProp).setAsJail();
            }
            else if (ownerANDhouses[1].equals("7")) {
                currProp.switchHypothequeStatusFree();
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
                map.put(Integer.toString(i), "0;0");
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

            if (proprieteParticuliere instanceof CasePropriete) {
                CasePropriete proprieteColoree = (CasePropriete) proprieteParticuliere;

                if (proprieteColoree.isAJail()){
                    builder.append("7");
                }
                else {
                    builder.append(""+proprieteColoree.getNombreMaisons());
                }

                map.put(Integer.toString(i), builder.toString());
                i++;
                continue;
            }
            
            if (proprieteParticuliere.isHypothequed()){
                builder.append("6");
            }
            else{
                builder.append("0");
            }

            map.put(Integer.toString(i), builder.toString());
            i++;
        }
        return map;
    }

    public static CasePropriete StringToCasePropriete(String body) {
        PerfectBoard board = new PerfectBoard();
        Case[] plateau = board.getPlateau();
        for (Case casePlateau : plateau) {
            String nomCasePlateau = casePlateau.toString();
            if (nomCasePlateau.equals(body)) {
                return (CasePropriete) casePlateau;
            }
        }
        return null;
    }

    public static String CaseAchetableToString(CaseAchetable caseEnQuestion) {
        return null;
    }

}
