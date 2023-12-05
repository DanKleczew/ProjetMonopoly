package fr.pantheonsorbonne.miage.game.Monopoly.Players;

import java.util.Map;

import fr.pantheonsorbonne.miage.Facade;
import fr.pantheonsorbonne.miage.PlayerFacade;
import fr.pantheonsorbonne.miage.game.Monopoly.ToolBox;
import fr.pantheonsorbonne.miage.game.Monopoly.Boards.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.Case;
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

            Map<String, String> banqueDeDonneesEnStringString = command.params();
            PerfectBoard plateauEphemere = ToolBox.mapToPerfectBoard(banqueDeDonneesEnStringString, remotePlayerAdapter);
            
            int balanceActuelle = Integer.parseInt(command.body().split(";")[1]);
            remotePlayerAdapter.delegate.setBankAccount(balanceActuelle);

            int positionActuelle = Integer.parseInt(command.body().split(";")[2]);
            plateauEphemere.setSpecificPosition(remotePlayerAdapter.delegate, positionActuelle);

            switch (commandName) {
                case "askBuyProperty":
                    CaseAchetable caseAVendre = (CaseAchetable) plateauEphemere.getCaseByName(command.body().split(";")[0]);
                    remotePlayerAdapter.askBuyProperty(caseAVendre, plateauEphemere);
                    break;
                case "askGetOutOfJail":
                    remotePlayerAdapter.askGetOutOfJail(plateauEphemere);
                    break;
                case "askRemoveInstantlySquat":
                    CasePropriete caseSquatee = (CasePropriete) plateauEphemere.getCaseByName(command.body().split(";")[0]);
                    remotePlayerAdapter.askRemoveInstantlySquat(caseSquatee, plateauEphemere);
                    break;
                case "thinkAndAnswer":
                    remotePlayerAdapter.think(plateauEphemere);
                    break;
                case "youLost":
                    System.out.println("I, player " + remotePlayerAdapter.delegate.getID() + ", lost.");
                case "youWin":
                    System.out.println("I won ! " + remotePlayerAdapter.delegate.getID());
                default:
                    System.exit(0);
            }
        }
    }

    // --------------------- Méthodes Réaction

    @Override
    public boolean askGetOutOfJail(PerfectBoard plateauEphemere) {
        boolean res = delegate.askGetOutOfJail(plateauEphemere);
        playerFacade.sendGameCommandToPlayer(monopoly, "host", new GameCommand(res ? "YesOut" : "NoIn"));

        return false;
    }

    @Override
    public boolean askBuyProperty(CaseAchetable caseAVendre, PerfectBoard plateauEphemere) {

        boolean res = delegate.askBuyProperty(caseAVendre, plateauEphemere);
        playerFacade.sendGameCommandToPlayer(monopoly, "host", new GameCommand(res ? "YesBuy" : "NoBuy"));

        return false;
    }

    @Override
    public boolean askRemoveInstantlySquat(CasePropriete proprieteSquatee, PerfectBoard plateauEphemere) {
        
        boolean res = delegate.askRemoveInstantlySquat(proprieteSquatee, plateauEphemere);
        playerFacade.sendGameCommandToPlayer(monopoly, "host", new GameCommand(res ? "YesGetRid" : "NoDoNot"));

        return false;
    }
    

    // ---------------------- Méthodes Réflexion

    @Override
    protected Map<TypePropriete, Integer> thinkAboutBuyingHouses(PerfectBoard plateauEphemere) {
        return delegate.thinkAboutBuyingHouses(plateauEphemere);
    }

    @Override
    protected Map<TypePropriete, Integer> thinkAboutSellingHouses(PerfectBoard plateauEphemere) {
        return delegate.thinkAboutSellingHouses(plateauEphemere);
    }

    @Override
    protected CaseAchetable[] thinkAboutHypothequeProprietes(PerfectBoard plateauEphemere) {
        return delegate.thinkAboutHypothequeProprietes(plateauEphemere);
    }

    @Override
    protected CasePropriete[] thinkAboutCreatingJails(PerfectBoard plateauEphemere) {
        return delegate.thinkAboutCreatingJails(plateauEphemere);
    }

    // ------------------ Méthode de réflexion principale
    private void think(PerfectBoard plateauEphemere) {
        Map<TypePropriete, Integer> listeMaisonsAPlacer = this.thinkAboutBuyingHouses(plateauEphemere);
        Map<TypePropriete, Integer> listeMaisonsARetirer = this.thinkAboutSellingHouses(plateauEphemere);
        CaseAchetable[] listeCaseAHypothequer = this.thinkAboutHypothequeProprietes(plateauEphemere);
        CasePropriete[] listeCaseATransformerEnPrison = this.thinkAboutCreatingJails(plateauEphemere);

        //Faisable en une ligne mais plus clair ainsi
        StringBuilder builder = new StringBuilder();
        builder.append(mapTypeIntegerToString(listeMaisonsAPlacer));
        builder.append("|");
        builder.append(mapTypeIntegerToString(listeMaisonsARetirer));
        builder.append("|");
        builder.append(arrayCaseToString(listeCaseAHypothequer));
        builder.append("|");
        builder.append(arrayCaseToString(listeCaseATransformerEnPrison));

        playerFacade.sendGameCommandToPlayer(monopoly, "host", new GameCommand(builder.toString()));
    }
    
     // -------------------- Méthodes de transformation en String

    private String mapTypeIntegerToString(Map<TypePropriete, Integer> mapDesMaisons){
        StringBuilder builderMap = new StringBuilder();
        for (TypePropriete couleur : mapDesMaisons.keySet()){
            builderMap.append(couleur.toString());
            builderMap.append(",");
            builderMap.append(mapDesMaisons.get(couleur).toString());
            builderMap.append(";");
        }
        builderMap.deleteCharAt(builderMap.length()-1); //Pour supprimer le ; de fin

        return builderMap.toString();
    }

    private String arrayCaseToString(Case[] listeCase){
        StringBuilder builderArray = new StringBuilder();
        for (Case propriete : listeCase){
            builderArray.append(propriete.toString());
            builderArray.append(";");
        }
        builderArray.deleteCharAt(builderArray.length()-1); //Pour supprimer le ; de fin

        return builderArray.toString();
    }

}
