package languageStay;

public class Sejour {

    private int annee;
    private Country pays;
    
    public Sejour(int annee, Country pays) {
        this.annee = annee;
        this.pays = pays;
    }

    public int getAnnee() {
        return annee;
    }

    public Country getPays() {
        return pays;
    }

    public boolean equals(int annee, Country pays){
        return this.annee == annee && this.pays == pays;
    }

    
}
