package fr.pantheonsorbonne.miage.game.Monopoly;

import java.util.Set;

import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.HostFacade;
import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseAchetable;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.IsBankruptException;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;
import fr.pantheonsorbonne.miage.game.Monopoly.Players.VoidBot;
import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;

public class NetworkMonopolyEngine extends MonopolyEngine {

    private static final int NUMBER_PLAYERS = 3;

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
        }

        MonopolyEngine host = new NetworkMonopolyEngine(hostFacade, listeJoueurs, monopoly);
        host.play();
        System.exit(0);

    }

    @Override
    protected boolean askGetOutOfJail(int playerID, int playerPosition, PerfectBoard plateauComplet) {
        hostFacade.sendGameCommandToPlayer
            (monopoly, ""+playerID, new GameCommand("askGetOutOfJail",""+playerPosition, ToolBox.perfectBoardToMap(plateauComplet, playerID)));

        GameCommand reponse = hostFacade.receiveGameCommand(monopoly);

        return reponse.name().equals("yesOut");
    }

    @Override
    protected boolean askRemoveInstantlySquat(int playerID, CasePropriete caseSquatee, PerfectBoard plateauComplet) {
        hostFacade.sendGameCommandToPlayer
            (monopoly, ""+playerID, new GameCommand("askRemoveInstantlySquat", caseSquatee.toString()));
        
            GameCommand reponse = hostFacade.receiveGameCommand(monopoly);

            return reponse.name().equals("YesGetRid");
    }

    @Override
    protected boolean askBuyProperty(int playerID, CaseAchetable caseAchetable, PerfectBoard plateauComplet) {
        hostFacade.sendGameCommandToPlayer(
                monopoly, ""+playerID, new GameCommand("askBuyProperty", caseAchetable.toString(),
                ToolBox.perfectBoardToMap(plateauComplet, playerID)));

        GameCommand reponse = hostFacade.receiveGameCommand(monopoly);

        return reponse.name().equals("YesBuy");
    }

    @Override
    protected void thinkAndDo(int playerID, PerfectBoard plateauComplet) throws IsBankruptException {
        hostFacade.sendGameCommandToPlayer(
            monopoly, ""+playerID, new GameCommand("thinkAndAnswer", ""+plateauComplet.getPositionJoueur(plateauComplet.getPlayerByID(playerID)), 
            ToolBox.perfectBoardToMap(plateauComplet, playerID)));

        // GameCommand reponseComplexe = hostFacade.receiveGameCommand(monopoly);

        //TODO : Décomposer la réponseComplexe et agir en conséquence
    }

}