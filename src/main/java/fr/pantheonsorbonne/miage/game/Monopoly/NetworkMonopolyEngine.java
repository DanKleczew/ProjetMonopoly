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
import fr.pantheonsorbonne.miage.game.Monopoly.Players.PlayersManager;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.VoidBot;
import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;

public class NetworkMonopolyEngine extends MonopolyEngine {

    private static final int NUMBER_PLAYERS = 4;

    private final HostFacade hostFacade;
    private final Game monopoly;
    private static PlayersManager ensembleDesJoueurs;

    protected NetworkMonopolyEngine(HostFacade hostFacade, PlayersManager ensembleDesJoueurs, Game monopoly) {
        super(new PerfectBoard(ensembleDesJoueurs), ensembleDesJoueurs);
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
        ensembleDesJoueurs = new PlayersManager(listeJoueurs);
        MonopolyEngine host = new NetworkMonopolyEngine(hostFacade, ensembleDesJoueurs, monopoly);
        int winnerID = host.play();

        for (String ID : setJoueurs){
            if (Integer.parseInt(ID) == winnerID){
                hostFacade.sendGameCommandToPlayer(monopoly, "" + ID, new GameCommand("youWin", "", new HashMap<>()));
            }
            else {
                hostFacade.sendGameCommandToPlayer(monopoly, "" + ID, new GameCommand("youLost"));
            }
        }
        System.exit(0);

    }

    // ------------------------- Overrides

    @Override
    protected boolean askPlayerGetOutOfJail(int playerID, PerfectBoard plateauComplet, PlayersManager ensembleDesJoueurs) {
        hostFacade.sendGameCommandToPlayer(monopoly, "" + playerID, new GameCommand("askGetOutOfJail",
                createBody(new CaseNeutre(" "), playerID, ensembleDesJoueurs),
                ToolBox.perfectBoardToMap(plateauComplet, playerID)));

        GameCommand reponse = hostFacade.receiveGameCommand(monopoly);

        return reponse.name().equals("yesOut");
    }

    @Override
    protected boolean askPlayerRemoveInstantlySquat(int playerID, CasePropriete caseSquatee, PerfectBoard plateauComplet, PlayersManager ensembleDesJoueurs) {
        hostFacade.sendGameCommandToPlayer(monopoly, "" + playerID,
                new GameCommand("askRemoveInstantlySquat",
                        createBody(caseSquatee, playerID, ensembleDesJoueurs),
                        ToolBox.perfectBoardToMap(plateauComplet, playerID)));

        GameCommand reponse = hostFacade.receiveGameCommand(monopoly);

        return reponse.name().equals("YesGetRid");
    }

    @Override
    protected boolean askPlayerBuyProperty(int playerID, CaseAchetable caseAchetable, PerfectBoard plateauComplet, PlayersManager ensembleDesJoueurs) {
        hostFacade.sendGameCommandToPlayer(
                monopoly, "" + playerID, new GameCommand("askBuyProperty",
                        createBody(caseAchetable, playerID, ensembleDesJoueurs),
                        ToolBox.perfectBoardToMap(plateauComplet, playerID)));

        GameCommand reponse = hostFacade.receiveGameCommand(monopoly);

        return reponse.name().equals("YesBuy");
    }

    @Override
    protected void playerThinkAndDo(int playerID, PerfectBoard plateauComplet, PlayersManager ensembleDesJoueurs) throws IsBankruptException {
        hostFacade.sendGameCommandToPlayer(
                monopoly, "" + playerID,
                new GameCommand("thinkAndAnswer",
                        createBody(new CaseNeutre(" "), playerID, ensembleDesJoueurs),
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

        ensembleDesJoueurs.getPlayerByID(playerID)
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


    private String createBody(Case caseEnQuestion, int playerID, PlayersManager ensembleDesJoueurs) {

        return caseEnQuestion.toString() + ";" +
                ensembleDesJoueurs.getPlayerByID(playerID).getBankAccount() + ";"
                + plateauComplet.getPositionJoueur(ensembleDesJoueurs.getPlayerByID(playerID));
    }

}