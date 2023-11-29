package fr.pantheonsorbonne.miage.game.Monopoly.Players;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.PlayerFacade;
import fr.pantheonsorbonne.miage.game.Monopoly.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseAchetable;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.TypePropriete;
import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;

public class RemotePlayerAdapter{
    Player delegate;
    // private static int playerCount = 0;

    public RemotePlayerAdapter(Player joueur) {
        this.delegate = joueur;
    }

    static final PlayerFacade playerFacade = Facade.getFacade();
    static Game monopoly;

    public static void main(String[] args) {

        Player dumb = new Dumb(0);
        RemotePlayerAdapter remotePlayerAdapter = new RemotePlayerAdapter(dumb);

        playerFacade.waitReady();
        playerFacade.createNewPlayer("" + remotePlayerAdapter.delegate.getID());
        monopoly = playerFacade.autoJoinGame("Monopoly");

        for (;;) {

            GameCommand command = playerFacade.receiveGameCommand(monopoly);
            String commandName = command.name().split("|")[0];
            String banqueDeDonneesEnString = command.name().split("|")[1];
            int positionJoueur = Integer.parseInt(command.name().split("|")[2]);

            switch (commandName) {
                case "askBuyProperty":
                    askBuyProperty(plateauEnString, positionJoueur);
                    break;
                case "playACard":
                    System.out.println(
                            "I have " + hand.stream().map(Card::toFancyString).collect(Collectors.joining(" ")));
                    handlePlayACard(command);
                    break;
                case "gameOver":
                    handleGameOverCommand(command);
                    break;

            }
        }
    }

    @Override
    public boolean askGetOutOfJail() {

    }

    
    public boolean askBuyProperty(String plateauEnString, int positionJoueur) {
        boolean res = delegate.askBuyProperty(proprieteLibre, plateauComplet);
        playerFacade.sendGameCommandToPlayer(monopoly, "host", new GameCommand(res ? "YesBuy" : "NoBuy"));

        return res;
    }

    @Override
    protected Map<TypePropriete, Integer> thinkAboutBuyingHouses(PerfectBoard plateauComplet) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'thinkAboutBuyingHouses'");
    }

    @Override
    protected Map<TypePropriete, Integer> thinkAboutSellingHouses() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'thinkAboutSellingHouses'");
    }

    @Override
    protected CaseAchetable[] thinkAboutHypothequeProprietes(PerfectBoard plateauComplet) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'thinkAboutHypothequeProprietes'");
    }

    @Override
    protected void thinkAboutCreatingJails() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'thinkAboutCreatingJails'");
    }

    @Override
    public boolean askRemoveInstantlySquat(CasePropriete ProprieteSquatee, PerfectBoard plateauComplet) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'askRemoveInstantlySquat'");
    }

}
