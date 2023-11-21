package fr.pantheonsorbonne.miage.game.Monopoly.Players;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.pantheonsorbonne.miage.game.Monopoly.PerfectBoard;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CaseAchetable;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.CasePropriete;
import fr.pantheonsorbonne.miage.game.Monopoly.Cases.TypePropriete;

public class Dumb extends Player {

    public Dumb(int ID) {
        super(ID);
    }

    @Override
    public boolean askGetOutOfJail() {
        return (this.getBankAccount() > 50);
    }
    

    @Override
    public boolean askBuyProperty(CaseAchetable proprieteLibre, PerfectBoard plateauComplet) {
        return (true);
    }

    @Override
    protected Map<TypePropriete, Integer> thinkAboutBuyingHouses(PerfectBoard plateauComplet) {
        Map<TypePropriete, Integer> listeDeSouhaits = new HashMap<>();
        List<CasePropriete> listeDeProprietesColorees = plateauComplet.getOwnedColoredProperties(this);
        for(CasePropriete propriete: listeDeProprietesColorees){
            int compt = 0;
            TypePropriete typeProp = propriete.getTypeOuCouleur();
            CasePropriete lastPropDeCetteCouleur = propriete;
            for(CasePropriete prop2 : listeDeProprietesColorees){
                if (prop2.getTypeOuCouleur() == typeProp){
                    compt++;
                    lastPropDeCetteCouleur = prop2;
                }
            }
            if (compt == typeProp.getNbProprieteDeCeType() && lastPropDeCetteCouleur.getNombreMaisons()<5){
                listeDeSouhaits.put(typeProp, 3);
            }
        }


        return listeDeSouhaits;
    }

    @Override
    protected Map<TypePropriete, Integer> thinkAboutSellingHouses() {
        return new HashMap<TypePropriete, Integer>();
    }

    @Override
    protected CaseAchetable[] thinkAboutHypothequeProprietes() {
        return new CaseAchetable[0];
    }

    @Override
    protected CasePropriete[] thinkAboutCreatingJails() {
        return new CasePropriete[0];
    }
    
}
