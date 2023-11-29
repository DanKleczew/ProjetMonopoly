package fr.pantheonsorbonne.miage.game.Monopoly.Players;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.PlayerFacade;
import fr.pantheonsorbonne.miage.game.Monopoly.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.ToolBox;
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
            String commandName = command.name();
            int positionJoueur = Integer.parseInt(command.body());
            Map<String, String> banqueDeDonneesEnStringString = command.params();
            PerfectBoard plateauEphemere = ToolBox.mapToPerfectBoard(banqueDeDonneesEnStringString);
            

            switch (commandName) {
                case "askBuyProperty":
                    CaseAchetable caseAVendre;
                    remotePlayerAdapter.askBuyProperty(caseAVendre, plateauEphemere);
                    break;
                case "askGetOutOfJail":
                    remotePlayerAdapter.askGetOutOfJail();
                    break;
                // case "playACard":
                //     System.out.println(
                //             "I have " + hand.stream().map(Card::toFancyString).collect(Collectors.joining(" ")));
                //     handlePlayACard(command);
                //     break;
                // case "gameOver":
                //     handleGameOverCommand(command);
                //     break;

            }
        }
    }

    
    private void askGetOutOfJail() {
        boolean res = delegate.askGetOutOfJail();
        playerFacade.sendGameCommandToPlayer(monopoly, "host", new GameCommand(res ? "YesOut" : "NoIn"));
    }

    
    private void askBuyProperty(CaseAchetable caseAVendre, PerfectBoard plateauEphemere) {

        boolean res = delegate.askBuyProperty(caseAVendre, plateauEphemere);
        playerFacade.sendGameCommandToPlayer(monopoly, "host", new GameCommand(res ? "YesBuy" : "NoBuy"));

    }

    public boolean askRemoveInstantlySquat(CasePropriete ProprieteSquatee, PerfectBoard plateauComplet) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'askRemoveInstantlySquat'");
    }
    
    private Map<TypePropriete, Integer> thinkAboutBuyingHouses(PerfectBoard plateauEphemere) {
        /*TODO : Les thinkAbout se feront tous à la suite à l'appel de la commande THINK du host
        Leurs renvois sont stockés et gérés par une méthode dans cette classe ci qui renverra au host
        Une commande (réponse) avec tous les choix pour chaque think.
        */
        return delegate.thinkAboutBuyingHouses(plateauEphemere);
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

    
    

}
