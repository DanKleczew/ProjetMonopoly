package fr.pantheonsorbonne.miage.game.Monopoly.Players;

public class IsBankruptException extends Exception{

    private Player joueurPerdant;
    private Player joueurGagnant;

    public IsBankruptException(Player joueurPerdant){
        this.joueurPerdant = joueurPerdant;
    }

    public void setGagnant(Player joueurGagnant){
        this.joueurGagnant = joueurGagnant;
    }
    
    public Player getPerdant(){
        return joueurPerdant;
    }

    public Player getGagnant(){
        return joueurGagnant;
    }
}
