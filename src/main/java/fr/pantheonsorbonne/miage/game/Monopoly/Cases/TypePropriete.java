package fr.pantheonsorbonne.miage.game.Monopoly.Cases;

public enum TypePropriete {
    MARRON(2), CYAN(3), ROSE(3),
    ORANGE(3), ROUGE(3), JAUNE(3),
    VERT(3), BLEU(2), GARE(4), COMPAGNIE(2);


    public final int nombreProprietesDeCeType;

    TypePropriete(int nbProprietes){
        this.nombreProprietesDeCeType = nbProprietes;
    }
}
