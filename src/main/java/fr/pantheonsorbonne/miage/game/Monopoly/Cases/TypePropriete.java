package fr.pantheonsorbonne.miage.game.Monopoly.Cases;

public enum TypePropriete {
    MARRON(2, new int[] { 2, 10, 30, 90, 160, 250 }),
    CYAN(3, new int[] { 6, 30, 90, 270, 400, 550 }),
    ROSE(3, new int[] { 10, 50, 150, 450, 625, 750 }),
    ORANGE(3, new int[] { 14, 70, 200, 550, 750, 950 }),
    ROUGE(3, new int[] { 18, 90, 250, 700, 875, 1050 }),
    JAUNE(3, new int[] { 22, 110, 330, 800, 975, 1150 }),
    VERT(3, new int[] { 26, 130, 390, 900, 1100, 1275 }),
    BLEU(2, new int[] { 35, 175, 500, 1100, 1300, 1500 }),
    GARE(4),
    COMPAGNIE(2);

    private final int nombreProprietesDeCeType;
    private final int[] echelleDeLoyer;

    TypePropriete(int nbProprietes) {
        this.nombreProprietesDeCeType = nbProprietes;
        this.echelleDeLoyer = null;
    }

    TypePropriete(int nbProprietes, int[] echelleDeLoyer) {
        this.nombreProprietesDeCeType = nbProprietes;
        this.echelleDeLoyer = echelleDeLoyer;
    }

    public int[] getEchelleDeLoyer() {
        return echelleDeLoyer;
    }

    public int getNbProprieteDeCeType(){
        return nombreProprietesDeCeType;
    }
}
