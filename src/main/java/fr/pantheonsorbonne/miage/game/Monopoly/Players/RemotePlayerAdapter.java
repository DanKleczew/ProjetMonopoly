package fr.pantheonsorbonne.miage.game.Monopoly.Players;

import java.util.Map;
import java.util.Random;

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

public class RemotePlayerAdapter{
    Player delegate;

    private RemotePlayerAdapter(Player joueur) {
        this.delegate = joueur;
    }

    static final PlayerFacade playerFacade = Facade.getFacade();
    static Game monopoly;

    public static void main(String[] args) throws IsBankruptException {
        Player JOUEUR = new Dumb(new Random().nextInt(1000));
      
        RemotePlayerAdapter remotePlayerAdapter = new RemotePlayerAdapter(JOUEUR);

        playerFacade.waitReady();
        playerFacade.createNewPlayer("" + remotePlayerAdapter.delegate.getID());
        monopoly = playerFacade.autoJoinGame("Monopoly");

        for (;;) {

            GameCommand command = playerFacade.receiveGameCommand(monopoly);
            String commandName = command.name();
            if (commandName.equals("youLost")){
                System.out.println("I Lost");
                System.exit(0);
            }
            else if(commandName.equals("youWin")){
                System.out.println("I won !");
                System.exit(0);
            }

            Map<String, String> banqueDeDonneesEnStringString = command.params();
            PerfectBoard plateauEphemere = ToolBox.mapToPerfectBoard(banqueDeDonneesEnStringString, remotePlayerAdapter.delegate);
            
            int balanceActuelle = Integer.parseInt(command.body().split(";")[1]);
            remotePlayerAdapter.delegate.setBankAccount(balanceActuelle);

            int positionActuelle = Integer.parseInt(command.body().split(";")[2]);
            plateauEphemere.setSpecificPosition(remotePlayerAdapter.delegate, positionActuelle);

            switch (commandName) {
                case "askBuyProperty":
                    CaseAchetable caseAVendre = (CaseAchetable) plateauEphemere.getCaseByName(command.body().split(";")[0]);
                    remotePlayerAdapter.askBotBuyProperty(caseAVendre, plateauEphemere);
                    break;
                case "askGetOutOfJail":
                    remotePlayerAdapter.askBotJail(plateauEphemere);
                    break;
                case "askRemoveInstantlySquat":
                    CasePropriete caseSquatee = (CasePropriete) plateauEphemere.getCaseByName(command.body().split(";")[0]);
                    remotePlayerAdapter.askBotRemoveSquat(caseSquatee, plateauEphemere);
                    break;
                case "thinkAndAnswer":
                    remotePlayerAdapter.thinkBotAndAnswer(plateauEphemere);
                    break;
            }
        }
        
    }

    // --------------------- Méthodes Réaction

    
    private void askBotJail(PerfectBoard plateauEphemere) {
        boolean res = delegate.askGetOutOfJail(plateauEphemere);
        playerFacade.sendGameCommandToPlayer(monopoly, "Host", new GameCommand(res ? "YesOut" : "NoIn"));
    }

    
    private void askBotBuyProperty(CaseAchetable caseAVendre, PerfectBoard plateauEphemere) {

        boolean res = delegate.askBuyProperty(caseAVendre, plateauEphemere);
        if (res)
            System.out.println("I've just bought " +caseAVendre.toString());
        playerFacade.sendGameCommandToPlayer(monopoly, "Host", new GameCommand(res ? "YesBuy" : "NoBuy"));
    }

    
    private void askBotRemoveSquat(CasePropriete proprieteSquatee, PerfectBoard plateauEphemere) {  
        boolean res = delegate.askRemoveInstantlySquat(proprieteSquatee, plateauEphemere);
        playerFacade.sendGameCommandToPlayer(monopoly, "Host", new GameCommand(res ? "YesGetRid" : "NoDoNot"));

    }
    

    // ---------------------- Méthodes Réflexion

    
    private Map<TypePropriete, Integer> thinkBotBuyHouses(PerfectBoard plateauEphemere) {
        return delegate.thinkAboutBuyingHouses(plateauEphemere);
    }

    private Map<TypePropriete, Integer> thinkBotSellHouses(PerfectBoard plateauEphemere) {
        return delegate.thinkAboutSellingHouses(plateauEphemere);
    }

    private CaseAchetable[] thinkBotHypotheques(PerfectBoard plateauEphemere) {
        return delegate.thinkAboutHypothequeProprietes(plateauEphemere);
    }

    private CasePropriete[] thinkBotJails(PerfectBoard plateauEphemere) {
        return delegate.thinkAboutCreatingJails(plateauEphemere);
    }


    // ------------------ Méthode de réflexion principale


    private void thinkBotAndAnswer(PerfectBoard plateauEphemere) {
        Map<TypePropriete, Integer> listeMaisonsAPlacer = this.thinkBotBuyHouses(plateauEphemere);
        Map<TypePropriete, Integer> listeMaisonsARetirer = this.thinkBotSellHouses(plateauEphemere);
        CaseAchetable[] listeCaseAHypothequer = this.thinkBotHypotheques(plateauEphemere);
        CasePropriete[] listeCaseATransformerEnPrison = this.thinkBotJails(plateauEphemere);

        
        StringBuilder builder = new StringBuilder();
        
        construction(builder, mapTypeIntegerToString(listeMaisonsAPlacer));
        builder.append("_");
        construction(builder, mapTypeIntegerToString(listeMaisonsARetirer));
        builder.append("_");
        construction(builder, arrayCaseToString(listeCaseAHypothequer));
        builder.append("_");
        construction(builder, arrayCaseToString(listeCaseATransformerEnPrison));

        playerFacade.sendGameCommandToPlayer(monopoly, "Host", new GameCommand(builder.toString()));
    }
    
    private StringBuilder construction(StringBuilder builder, String ajout){
        if (ajout.equals("")){
            builder.append("N");
        }
        else {
            builder.append(ajout);
        }
        return builder;
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
        if (builderMap.length()>0)
        builderMap.deleteCharAt(builderMap.length()-1); //Pour supprimer le ; de fin

        return builderMap.toString();
    }

    private String arrayCaseToString(Case[] listeCase){
        StringBuilder builderArray = new StringBuilder();
        for (Case propriete : listeCase){
            builderArray.append(propriete.toString());
            builderArray.append(";");
        }
        if (builderArray.length()>0)
        builderArray.deleteCharAt(builderArray.length()-1); //Pour supprimer le ; de fin

        return builderArray.toString();
    }

}
