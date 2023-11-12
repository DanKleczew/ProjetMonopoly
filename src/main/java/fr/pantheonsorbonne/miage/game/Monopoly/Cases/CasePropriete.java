package fr.pantheonsorbonne.miage.game.Monopoly.Cases;

import fr.pantheonsorbonne.miage.game.Monopoly.Players.Player;

public class CasePropriete extends CaseAchetable{
    private int[] echelleDeLoyer;
    private int nombreMaisons = 0;
    private boolean hotel = false;

    public CasePropriete(String name, int prixAchat, TypePropriete couleur) {
        super(name, prixAchat, couleur);
        this.echelleDeLoyer = couleur.getEchelleDeLoyer();

    }

    public CasePropriete(String name, int prixAchat, TypePropriete couleur, int[] echelleDeLoyer){
        super(name, prixAchat, couleur);
        this.echelleDeLoyer = echelleDeLoyer;
    }

    @Override
    protected void doCaseEffect(Player joueur) {
        if (this.isBuyable()){
            joueur.askBuyProperty();
        }
        else {
            pay(joueur);
        }
    }

    @Override
    protected void pay(Player joueur) {
        
        
    }

    public int[] getEchelleDeLoyer(){
        return echelleDeLoyer;
    }

    public int getNombreMaisons(){
        return this.nombreMaisons;
    }
    public boolean hasHotel(){
        return this.hotel;
    }

}
