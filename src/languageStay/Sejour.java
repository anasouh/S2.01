package languageStay;

/**
 * <strong>Permet de créer un objet Séjour </strong>
 * @author Desmee Nathan
 * @author Ouhdda Anas
 * @author Belguebli Rayane
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
    * @return int l'année
    */
    public int getAnnee() {
        return annee;
    }

    /**
    * Retourne le pays du séjour
    * @return Country le pays 
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
