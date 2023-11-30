package fr.pantheonsorbonne.miage.game.Monopoly;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.pantheonsorbonne.miage.game.Monopoly.Boards.Board;
import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.Case;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseAchetable;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseCompagnie;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseGare;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.VoidBot;

public class ToolBox {

    public static PerfectBoard mapToPerfectBoard(Map<String, String> map) throws IsBankruptException {
        Player toi = new VoidBot(0);
        Player pasToi = new VoidBot(1);
        PerfectBoard plateauTampon = new PerfectBoard();
        List<CaseAchetable> listeDesProp = plateauTampon.getAllProprietes();
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
            } else if (stringpetee[1].equals("7")){
                continue;
            }
            else {
                ((CasePropriete) currProp).addHouseNoPay(Integer.parseInt(stringpetee[1]));
            }
        }
        return null;
    }

    public static Map<String,String> perfectBoardToMap(PerfectBoard board, Player player) {
        Deque<Player> listeJoueurs = board.getListeJoueurs();
        List<CaseAchetable> listeDesProp = board.getAllProprietes();
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
                else if(proprieteParticuliere instanceof CaseGare || proprieteParticuliere instanceof CaseCompagnie){
                    builder.append("7");
                }
                else{
                builder.append(Integer.toString(propriete.getNombreMaisons()));
                }
            }

            map.put(Integer.toString(i), builder.toString());
            i++;
        }
        return map;
    }

    public static CasePropriete StringToCasePropriete(String body) {
        PerfectBoard board = new PerfectBoard();
        Case[] plateau = board.getPlateau();
        for (Case casePlateau : plateau){
            String nomCasePlateau = casePlateau.toString();
            if (nomCasePlateau.equals(body)){
                return (CasePropriete) casePlateau;
            }
        }
        return null;
    }

}
