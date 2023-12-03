package fr.pantheonsorbonne.miage.game.Monopoly.Players;

import java.util.Map;

import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.PlayerFacade;
import fr.pantheonsorbonne.miage.game.Monopoly.ToolBox;
import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseAchetable;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.TypePropriete;
import fr.pantheonsorbonne.miage.model.Game;
import fr.pantheonsorbonne.miage.model.GameCommand;

public class RemotePlayerAdapter extends Player{
    Player delegate;
    private static int playerCount = 0;

    public RemotePlayerAdapter(Player joueur) {
        super(playerCount++);
        this.delegate = joueur;
    }

    static final PlayerFacade playerFacade = Facade.getFacade();
    static Game monopoly;

    public static void main(String[] args) throws IsBankruptException {

        Player dumb = new Dumb(0);
        RemotePlayerAdapter remotePlayerAdapter = new RemotePlayerAdapter(dumb);

        playerFacade.waitReady();
        playerFacade.createNewPlayer("" + remotePlayerAdapter.delegate.getID());
        monopoly = playerFacade.autoJoinGame("Monopoly");

        for (;;) {

            GameCommand command = playerFacade.receiveGameCommand(monopoly);
            String commandName = command.name();

            int positionJoueur = -1;
            CasePropriete caseConcernee = null;
            try {
                positionJoueur = Integer.parseInt(command.body());
            } 
            catch (NumberFormatException e){
                caseConcernee = ToolBox.StringToCasePropriete(command.body());
            }

            Map<String, String> banqueDeDonneesEnStringString = command.params();
            PerfectBoard plateauEphemere = ToolBox.mapToPerfectBoard(banqueDeDonneesEnStringString, remotePlayerAdapter);
            

            switch (commandName) {
                case "askBuyProperty":
                    CaseAchetable caseAVendre = (CaseAchetable) plateauEphemere.getCase(positionJoueur);
                    remotePlayerAdapter.askBuyProperty(caseAVendre, plateauEphemere);
                    break;
                case "askGetOutOfJail":
                    remotePlayerAdapter.askGetOutOfJail(plateauEphemere);
                    break;
                case "askRemoveInstantlySquat":
                    remotePlayerAdapter.askRemoveInstantlySquat(caseConcernee, plateauEphemere);
                    break;
                case "thinkAndAnswer":
                    remotePlayerAdapter.think(positionJoueur, plateauEphemere);
                    break;
                case "youLost":
                    System.out.println("I, player " + remotePlayerAdapter.delegate.getID() + ", lost.");
                case "youWin":
                    System.out.println("I won !");
                default:
                    System.exit(0);
                
            }
        }
    }

    
    private void think(int positionJoueur, PerfectBoard plateauEphemere) {
        // Map<TypePropriete, Integer> listeMaisonsAPlacer = this.thinkAboutBuyingHouses(plateauEphemere);
        // Map<TypePropriete, Integer> listeMaisonsARetirer = this.thinkAboutSellingHouses(plateauEphemere);
        // CaseAchetable[] listeCaseAHypothequer = this.thinkAboutHypothequeProprietes(plateauEphemere);
        // CasePropriete[] listeCaseATransformerEnPrison = this.thinkAboutCreatingJails(plateauEphemere);

        //TODO : Gros string bien délimité pour tout ca

        String boulot = "";
        playerFacade.sendGameCommandToPlayer(monopoly, "host", new GameCommand(boulot));
    }


    // --------------------- Méthodes Réaction

    public boolean askGetOutOfJail(PerfectBoard plateauEphemere) {
        boolean res = delegate.askGetOutOfJail(plateauEphemere);
        playerFacade.sendGameCommandToPlayer(monopoly, "host", new GameCommand(res ? "YesOut" : "NoIn"));

        return false;
    }

    
    public boolean askBuyProperty(CaseAchetable caseAVendre, PerfectBoard plateauEphemere) {

        boolean res = delegate.askBuyProperty(caseAVendre, plateauEphemere);
        playerFacade.sendGameCommandToPlayer(monopoly, "host", new GameCommand(res ? "YesBuy" : "NoBuy"));

        return false;
    }

    public boolean askRemoveInstantlySquat(CasePropriete proprieteSquatee, PerfectBoard plateauEphemere) {
        
        boolean res = delegate.askRemoveInstantlySquat(proprieteSquatee, plateauEphemere);
        playerFacade.sendGameCommandToPlayer(monopoly, "host", new GameCommand(res ? "YesGetRid" : "NoDoNot"));

        return false;
    }
    

    // ---------------------- Méthodes Réflexion

    protected Map<TypePropriete, Integer> thinkAboutBuyingHouses(PerfectBoard plateauEphemere) {
        return delegate.thinkAboutBuyingHouses(plateauEphemere);
    }

    
    protected Map<TypePropriete, Integer> thinkAboutSellingHouses(PerfectBoard plateauEphemere) {
        return delegate.thinkAboutSellingHouses(plateauEphemere);
    }

    
    protected CaseAchetable[] thinkAboutHypothequeProprietes(PerfectBoard plateauEphemere) {
        return delegate.thinkAboutHypothequeProprietes(plateauEphemere);
    }

    
    protected CasePropriete[] thinkAboutCreatingJails(PerfectBoard plateauEphemere) {
        return delegate.thinkAboutCreatingJails(plateauEphemere);
    }

    
    

}
