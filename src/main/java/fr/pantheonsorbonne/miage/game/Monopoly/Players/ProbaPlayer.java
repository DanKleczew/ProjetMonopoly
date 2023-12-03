package fr.pantheonsorbonne.miage.game.Monopoly.Players;

public class ProbaPlayer {
    private double probaDes ;

    public ProbaPlayer(int resultatDes){
        int numerateur = 1;
        int denominateur = 36;
        for (int i = 2; i<8; i++){
            if (resultatDes == i ){
                this.probaDes = numerateur/denominateur;
            }
            numerateur ++ ;
        }

        for (int i = 8 ; i<13 ; i++){
            numerateur --;
            if (resultatDes == i){
                this.probaDes = numerateur/denominateur;
            }
        }
    }
    public double getProbaDes(){
        return this.probaDes;
    }

    public double calculProbaDeMourir(double ... proba){

        return 0.0;
    }

    
}
