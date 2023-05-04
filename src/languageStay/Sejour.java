package languageStay;

/**
 * <strong>Permet de créer un objet Séjour </strong>
 * @author Desmee Nathan, Ouhdda Anas, Belguebli Rayane
 */

public class Sejour {

    private int annee;
    private Country pays;
    
    /**
     * Crée un Sejour complètement spécifiée
     * @param annee année du séjour
     * @param pays pays dans lequel c'est passé le séjour
     */
    public Sejour(int annee, Country pays) {
        this.annee = annee;
        this.pays = pays;
    }

    /**
    * Retourne l'année du séjour
    */
    public int getAnnee() {
        return annee;
    }

    /**
    * Retourne le pays du séjour
    */
    public Country getPays() {
        return pays;
    }


    /**
    * Retourne retounr true si le séjour est le même que celui donné en paramètre
    */
    public boolean equals(int annee, Country pays){
        return this.annee == annee && this.pays == pays;
    }

    
}
