package fr.pantheonsorbonne.miage.game.Monopoly;

import java.util.Set;
import java.util.HashMap;
import java.util.Map;

import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.HostFacade;
import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.Case;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseAchetable;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseNeutre;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.TypePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.VoidBot;
import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;

public class NetworkMonopolyEngine extends MonopolyEngine {

    private static final int NUMBER_PLAYERS = 2;

    private final HostFacade hostFacade;
    private final Game monopoly;

    protected NetworkMonopolyEngine(HostFacade hostFacade, Player[] listeJoueurs, Game monopoly) {
        super(new PerfectBoard(listeJoueurs));
        this.hostFacade = hostFacade;
        this.monopoly = monopoly;
    }

    public static void main(String[] args) throws IsBankruptException {
        HostFacade hostFacade = Facade.getFacade();
        hostFacade.waitReady();
        hostFacade.createNewPlayer("Host");

        Game monopoly = hostFacade.createNewGame("Monopoly");

        hostFacade.waitForExtraPlayerCount(NUMBER_PLAYERS);
        
        Set<String> setJoueurs = monopoly.getPlayers();
        Player[] listeJoueurs = new Player[NUMBER_PLAYERS];
        int i = 0;
        for (String playerId : setJoueurs) {
            listeJoueurs[i] = new VoidBot(Integer.parseInt(playerId)); 
            //On instancie des VoidBot mais ce seront les joueurs en réseau qui décideront des actions
            //On appelera jamais les méthodes des bots de la liste, ce sont juste des flags avec un id = à celui du joueur
            i++;
        }

        MonopolyEngine host = new NetworkMonopolyEngine(hostFacade, listeJoueurs, monopoly);
        host.play();
        System.exit(0);

    }

    // ------------------------- Overrides

    @Override
    protected boolean askGetOutOfJail(int playerID, PerfectBoard plateauComplet) {
        hostFacade.sendGameCommandToPlayer(monopoly, "" + playerID, new GameCommand("askGetOutOfJail",
                createBody(new CaseNeutre(" "), playerID),
                ToolBox.perfectBoardToMap(plateauComplet, playerID)));

        GameCommand reponse = hostFacade.receiveGameCommand(monopoly);

        return reponse.name().equals("yesOut");
    }

    @Override
    protected boolean askRemoveInstantlySquat(int playerID, CasePropriete caseSquatee, PerfectBoard plateauComplet) {
        hostFacade.sendGameCommandToPlayer(monopoly, "" + playerID,
                new GameCommand("askRemoveInstantlySquat",
                        createBody(caseSquatee, playerID),
                        ToolBox.perfectBoardToMap(plateauComplet, playerID)));

        GameCommand reponse = hostFacade.receiveGameCommand(monopoly);

        return reponse.name().equals("YesGetRid");
    }

    @Override
    protected boolean askBuyProperty(int playerID, CaseAchetable caseAchetable, PerfectBoard plateauComplet) {
        hostFacade.sendGameCommandToPlayer(
                monopoly, "" + playerID, new GameCommand("askBuyProperty",
                        createBody(caseAchetable, playerID),
                        ToolBox.perfectBoardToMap(plateauComplet, playerID)));

        GameCommand reponse = hostFacade.receiveGameCommand(monopoly);

        return reponse.name().equals("YesBuy");
    }

    @Override
    protected void thinkAndDo(int playerID, PerfectBoard plateauComplet) throws IsBankruptException {
        hostFacade.sendGameCommandToPlayer(
                monopoly, "" + playerID,
                new GameCommand("thinkAndAnswer",
                        createBody(new CaseNeutre(" "), playerID),
                        ToolBox.perfectBoardToMap(plateauComplet, playerID)));

        GameCommand reponseComplexe = hostFacade.receiveGameCommand(monopoly);

        String achatVenteHypothequePrisons = reponseComplexe.name();
        String[] decoupageReponseComplexe = achatVenteHypothequePrisons.split("_");

        ///
        Case[] hypothequesEnCase = stringToArrayCase(decoupageReponseComplexe[2], plateauComplet);
        CaseAchetable[] hypotheques = new CaseAchetable[hypothequesEnCase.length];
        for (int i = 0; i<hypothequesEnCase.length; i++){
            hypotheques[i] = (CaseAchetable) hypothequesEnCase[i];
        }

        Case[] jailsEnCase = stringToArrayCase(decoupageReponseComplexe[3], plateauComplet);
        CasePropriete[] jails = new CasePropriete[jailsEnCase.length];
        for (int i = 0; i<jailsEnCase.length; i++){
            jails[i] = (CasePropriete) jailsEnCase[i];
        }
        ///

        plateauComplet.getPlayerByID(playerID)
                .thinkAndDo(stringToMapTypeInt(decoupageReponseComplexe[0]),
                        stringToMapTypeInt(decoupageReponseComplexe[1]),
                        hypotheques,
                        jails,
                        plateauComplet);
    }

    // ----------- Méthodes utilitaires
    private Map<TypePropriete, Integer> stringToMapTypeInt(String chaineCodee) {
        Map<TypePropriete, Integer> mapDesMaisons = new HashMap<>();
        if (chaineCodee.equals("N")){
            return mapDesMaisons;
        }

        for (String temp : chaineCodee.split(";")) {
            String[] arrayTypeInt = temp.split(",");
            mapDesMaisons.put(TypePropriete.valueOf(arrayTypeInt[0]), Integer.parseInt(arrayTypeInt[1]));
        }
        return mapDesMaisons;
    }

    private Case[] stringToArrayCase(String chaineCodee, PerfectBoard plateauComplet) {
        if (chaineCodee.equals("N")){
            return new Case[0];
        }
        String[] temp = chaineCodee.split(";");

        Case[] listeDeCase = new Case[temp.length];
        

        for (int i = 0; i < temp.length; i++) {
            listeDeCase[i] = plateauComplet.getCaseByName(temp[i]);
        }
        return listeDeCase;
    }


    private String createBody(Case caseEnQuestion, int playerID) {

        return caseEnQuestion.toString() + ";" +
                plateauComplet.getPlayerByID(playerID).getBankAccount() + ";"
                + plateauComplet.getPositionJoueur(plateauComplet.getPlayerByID(playerID));
    }

}