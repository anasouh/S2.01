package languageStay;
import java.util.ArrayList;

/**
 * <strong>Permet de créer un objet Platform </strong>
 * @author Desmee Nathan
 * @author Ouhdda Anas
 * @author Belguebli Rayane
 */

public class Plateform {

    private ArrayList<Teenager> promo = new ArrayList<>();

    /**
     * Supprime de la platform des teenagers pour qu'il y en reste juste le nombre donné en paramètre
     * @param i nombre max de teenager 
     */
    public void supprimer(int i){
        int c = 0;
        for(Teenager t : promo){
            t.purgeInvalidRequierement();
        }
        while(c < i && promo.size() > 0){
            int minCri = minimumCritere(promo);
            for(Teenager etudiant : promo){
                if(etudiant.getNbCriterion() == minCri){
                    c++;
                    promo.remove(etudiant);
                }
            }
        }
    }

    /**
     * Supprime de la platform des teenagers pour qu'il y en reste juste le nombre donné en paramètre
     * @param i nombre max de teenager 
     * @param pays le pays du teenager
     */
    public void supprimer(int i, Country pays){
        int c = 0;
        for(Teenager t : promo){
            t.purgeInvalidRequierement();
        }
        while(c < i && promo.size() > 0){
            int minCri = minimumCritere(promo);
            for(Teenager etudiant : promo){
                if(etudiant.getNbCriterion() == minCri && etudiant.getCountry() == pays){
                    c++;
                    promo.remove(etudiant);
                }
            }
        }
    }

    /**
     * Renvoie le nombre de critère minimum 
     * @param etudiants liste des étudiants 
     * @return le nombre minimal de critère
     */
    public int minimumCritere(ArrayList<Teenager> etudiants){
        int min = etudiants.get(0).getNbCriterion();
        for(Teenager etudiant : etudiants){
            if(min > etudiant.getNbCriterion()){
                min = etudiant.getNbCriterion();
            }
        }
        return min;
    }
}
